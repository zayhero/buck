/*
 * Copyright 2016-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.parser;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.facebook.buck.core.cell.Cell;
import com.facebook.buck.core.exceptions.HumanReadableException;
import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.model.Flavor;
import com.facebook.buck.core.model.Flavored;
import com.facebook.buck.core.model.UnflavoredBuildTarget;
import com.facebook.buck.core.model.targetgraph.DescriptionWithTargetGraph;
import com.facebook.buck.core.model.targetgraph.TargetNode;
import com.facebook.buck.core.model.targetgraph.impl.TargetNodeFactory;
import com.facebook.buck.core.rules.knowntypes.KnownBuildRuleTypes;
import com.facebook.buck.core.rules.type.BuildRuleType;
import com.facebook.buck.event.PerfEventId;
import com.facebook.buck.event.SimplePerfEvent;
import com.facebook.buck.json.JsonObjectHashing;
import com.facebook.buck.log.Logger;
import com.facebook.buck.model.BuildFileTree;
import com.facebook.buck.parser.api.ProjectBuildFileParser;
import com.facebook.buck.parser.exceptions.NoSuchBuildTargetException;
import com.facebook.buck.parser.function.BuckPyFunction;
import com.facebook.buck.rules.coercer.ConstructorArgMarshaller;
import com.facebook.buck.rules.coercer.ParamInfoException;
import com.facebook.buck.rules.keys.config.RuleKeyConfiguration;
import com.facebook.buck.rules.visibility.VisibilityPattern;
import com.facebook.buck.rules.visibility.VisibilityPatternFactory;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableSet;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;

/**
 * Creates {@link TargetNode} instances from raw data coming in form the {@link
 * ProjectBuildFileParser}.
 */
public class DefaultParserTargetNodeFactory
    implements ParserTargetNodeFactory<Map<String, Object>> {

  private static final Logger LOG = Logger.get(DefaultParserTargetNodeFactory.class);

  private final ConstructorArgMarshaller marshaller;
  private final PackageBoundaryChecker packageBoundaryChecker;
  private final TargetNodeListener<TargetNode<?, ?>> nodeListener;
  private final TargetNodeFactory targetNodeFactory;
  private final VisibilityPatternFactory visibilityPatternFactory;
  private final RuleKeyConfiguration ruleKeyConfiguration;

  private DefaultParserTargetNodeFactory(
      ConstructorArgMarshaller marshaller,
      PackageBoundaryChecker packageBoundaryChecker,
      TargetNodeListener<TargetNode<?, ?>> nodeListener,
      TargetNodeFactory targetNodeFactory,
      VisibilityPatternFactory visibilityPatternFactory,
      RuleKeyConfiguration ruleKeyConfiguration) {
    this.marshaller = marshaller;
    this.packageBoundaryChecker = packageBoundaryChecker;
    this.nodeListener = nodeListener;
    this.targetNodeFactory = targetNodeFactory;
    this.visibilityPatternFactory = visibilityPatternFactory;
    this.ruleKeyConfiguration = ruleKeyConfiguration;
  }

  public static ParserTargetNodeFactory<Map<String, Object>> createForParser(
      ConstructorArgMarshaller marshaller,
      LoadingCache<Cell, BuildFileTree> buildFileTrees,
      TargetNodeListener<TargetNode<?, ?>> nodeListener,
      TargetNodeFactory targetNodeFactory,
      VisibilityPatternFactory visibilityPatternFactory,
      RuleKeyConfiguration ruleKeyConfiguration) {
    return new DefaultParserTargetNodeFactory(
        marshaller,
        new ThrowingPackageBoundaryChecker(buildFileTrees),
        nodeListener,
        targetNodeFactory,
        visibilityPatternFactory,
        ruleKeyConfiguration);
  }

  public static ParserTargetNodeFactory<Map<String, Object>> createForDistributedBuild(
      ConstructorArgMarshaller marshaller,
      TargetNodeFactory targetNodeFactory,
      VisibilityPatternFactory visibilityPatternFactory,
      RuleKeyConfiguration ruleKeyConfiguration) {
    return new DefaultParserTargetNodeFactory(
        marshaller,
        new NoopPackageBoundaryChecker(),
        (buildFile, node) -> {
          // No-op.
        },
        targetNodeFactory,
        visibilityPatternFactory,
        ruleKeyConfiguration);
  }

  @Override
  public TargetNode<?, ?> createTargetNode(
      Cell cell,
      KnownBuildRuleTypes knownBuildRuleTypes,
      Path buildFile,
      BuildTarget target,
      Map<String, Object> rawNode,
      Function<PerfEventId, SimplePerfEvent.Scope> perfEventScope) {
    BuildRuleType buildRuleType = parseBuildRuleTypeFromRawRule(knownBuildRuleTypes, rawNode);

    // Because of the way that the parser works, we know this can never return null.
    DescriptionWithTargetGraph<?> description = knownBuildRuleTypes.getDescription(buildRuleType);

    verifyUnflavoredBuildTarget(cell, buildRuleType, buildFile, target, description, rawNode);

    Preconditions.checkState(cell.equals(cell.getCell(target)));
    Object constructorArg;
    try {
      ImmutableSet.Builder<BuildTarget> declaredDeps = ImmutableSet.builder();
      ImmutableSet<VisibilityPattern> visibilityPatterns;
      ImmutableSet<VisibilityPattern> withinViewPatterns;
      try (SimplePerfEvent.Scope scope =
          perfEventScope.apply(PerfEventId.of("MarshalledConstructorArg"))) {
        constructorArg =
            marshaller.populate(
                cell.getCellPathResolver(),
                cell.getFilesystem(),
                target,
                description.getConstructorArgType(),
                declaredDeps,
                rawNode);
        visibilityPatterns =
            visibilityPatternFactory.createFromStringList(
                cell.getCellPathResolver(), "visibility", rawNode.get("visibility"), target);
        withinViewPatterns =
            visibilityPatternFactory.createFromStringList(
                cell.getCellPathResolver(), "within_view", rawNode.get("within_view"), target);
      }

      return createTargetNodeFromObject(
          cell,
          buildFile,
          target,
          description,
          constructorArg,
          rawNode,
          declaredDeps.build(),
          visibilityPatterns,
          withinViewPatterns,
          perfEventScope);
    } catch (NoSuchBuildTargetException e) {
      throw new HumanReadableException(e);
    } catch (ParamInfoException e) {
      throw new HumanReadableException(e, "%s: %s", target, e.getMessage());
    } catch (IOException e) {
      throw new HumanReadableException(e.getMessage(), e);
    }
  }

  private static void verifyUnflavoredBuildTarget(
      Cell cell,
      BuildRuleType buildRuleType,
      Path buildFile,
      BuildTarget target,
      DescriptionWithTargetGraph<?> description,
      Map<String, Object> rawNode) {
    UnflavoredBuildTarget unflavoredBuildTarget = target.getUnflavoredBuildTarget();
    if (target.isFlavored()) {
      if (description instanceof Flavored) {
        if (!((Flavored) description).hasFlavors(ImmutableSet.copyOf(target.getFlavors()))) {
          throw UnexpectedFlavorException.createWithSuggestions(
              (Flavored) description, cell, target);
        }
      } else {
        LOG.warn(
            "Target %s (type %s) must implement the Flavored interface "
                + "before we can check if it supports flavors: %s",
            unflavoredBuildTarget, buildRuleType, target.getFlavors());
        ImmutableSet<String> invalidFlavorsStr =
            target
                .getFlavors()
                .stream()
                .map(Flavor::toString)
                .collect(ImmutableSet.toImmutableSet());
        String invalidFlavorsDisplayStr = String.join(", ", invalidFlavorsStr);
        throw new HumanReadableException(
            "The following flavor(s) are not supported on target %s:\n"
                + "%s.\n\n"
                + "Please try to remove them when referencing this target.",
            unflavoredBuildTarget, invalidFlavorsDisplayStr);
      }
    }

    UnflavoredBuildTarget unflavoredBuildTargetFromRawData =
        RawNodeParsePipeline.parseBuildTargetFromRawRule(
            cell.getRoot(), cell.getCanonicalName(), rawNode, buildFile);
    if (!unflavoredBuildTarget.equals(unflavoredBuildTargetFromRawData)) {
      throw new IllegalStateException(
          String.format(
              "Inconsistent internal state, target from data: %s, expected: %s, raw data: %s",
              unflavoredBuildTargetFromRawData,
              unflavoredBuildTarget,
              Joiner.on(',').withKeyValueSeparator("->").join(rawNode)));
    }
  }

  private TargetNode<?, ?> createTargetNodeFromObject(
      Cell cell,
      Path buildFile,
      BuildTarget target,
      DescriptionWithTargetGraph<?> description,
      Object constructorArg,
      Map<String, Object> rawNode,
      ImmutableSet<BuildTarget> declaredDeps,
      ImmutableSet<VisibilityPattern> visibilityPatterns,
      ImmutableSet<VisibilityPattern> withinViewPatterns,
      Function<PerfEventId, SimplePerfEvent.Scope> perfEventScope)
      throws IOException {
    try (SimplePerfEvent.Scope scope = perfEventScope.apply(PerfEventId.of("CreatedTargetNode"))) {
      TargetNode<?, ?> node =
          targetNodeFactory.createFromObject(
              hashRawNode(rawNode),
              description,
              constructorArg,
              cell.getFilesystem(),
              target,
              declaredDeps,
              visibilityPatterns,
              withinViewPatterns,
              cell.getCellPathResolver());
      packageBoundaryChecker.enforceBuckPackageBoundaries(cell, target, node.getInputs());
      nodeListener.onCreate(buildFile, node);
      return node;
    }
  }

  private HashCode hashRawNode(Map<String, Object> rawNode) {
    Hasher hasher = Hashing.sha1().newHasher();
    hasher.putString(ruleKeyConfiguration.getCoreKey(), UTF_8);
    JsonObjectHashing.hashJsonObject(hasher, rawNode);
    return hasher.hash();
  }

  private static BuildRuleType parseBuildRuleTypeFromRawRule(
      KnownBuildRuleTypes knownBuildRuleTypes, Map<String, Object> map) {
    String type = (String) Preconditions.checkNotNull(map.get(BuckPyFunction.TYPE_PROPERTY_NAME));
    return knownBuildRuleTypes.getBuildRuleType(type);
  }
}
