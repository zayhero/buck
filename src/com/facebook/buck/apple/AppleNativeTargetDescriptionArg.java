/*
 * Copyright 2014-present Facebook, Inc.
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

package com.facebook.buck.apple;

import com.facebook.buck.core.exceptions.HumanReadableException;
import com.facebook.buck.core.sourcepath.SourcePath;
import com.facebook.buck.cxx.CxxLibraryDescription;
import com.facebook.buck.swift.SwiftCommonArg;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import java.util.Optional;
import org.immutables.value.Value;

/** Arguments common to Apple targets. */
public interface AppleNativeTargetDescriptionArg
    extends CxxLibraryDescription.CommonArg, SwiftCommonArg {
  @Value.NaturalOrder
  ImmutableSortedMap<String, ImmutableMap<String, String>> getConfigs();

  Optional<String> getHeaderPathPrefix();

  Optional<SourcePath> getUmbrellaHeader();

  @Value.Default
  default boolean isModular() {
    return false;
  }

  @Value.Check
  default void checkModularUsage() {
    if (isModular() && getBridgingHeader().isPresent()) {
      throw new HumanReadableException(
          "Cannot be modular=True and have a bridging_header in the same rule");
    }
  }
}
