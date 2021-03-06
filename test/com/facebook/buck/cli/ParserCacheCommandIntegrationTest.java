/*
 * Copyright 2017-present Facebook, Inc.
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

package com.facebook.buck.cli;

import static com.facebook.buck.util.string.MoreStrings.linesToText;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assume.assumeTrue;

import com.facebook.buck.apple.AppleNativeIntegrationTestUtils;
import com.facebook.buck.apple.toolchain.ApplePlatform;
import com.facebook.buck.core.exceptions.HumanReadableException;
import com.facebook.buck.testutil.ProcessResult;
import com.facebook.buck.testutil.TemporaryPaths;
import com.facebook.buck.testutil.integration.ProjectWorkspace;
import com.facebook.buck.testutil.integration.TestContext;
import com.facebook.buck.testutil.integration.TestDataHelper;
import com.facebook.buck.util.NamedTemporaryFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParserCacheCommandIntegrationTest {

  @Rule public TemporaryPaths tmp = new TemporaryPaths();
  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testSaveAndLoad() throws IOException {
    assumeTrue(AppleNativeIntegrationTestUtils.isApplePlatformAvailable(ApplePlatform.MACOSX));

    ProjectWorkspace workspace =
        TestDataHelper.createProjectWorkspaceForScenarioWithoutDefaultCell(
            this, "parser_with_cell", tmp);
    workspace.setUp();

    // Warm the parser cache.
    TestContext context = new TestContext();
    ProcessResult runBuckResult =
        workspace.runBuckdCommand(context, "query", "deps(//Apps:TestAppsLibrary)");
    runBuckResult.assertSuccess();
    assertThat(
        runBuckResult.getStdout(),
        Matchers.containsString(
            linesToText(
                "//Apps:TestAppsLibrary",
                "//Libraries/Dep1:Dep1_1",
                "//Libraries/Dep1:Dep1_2",
                "bar//Dep2:Dep2")));

    // Save the parser cache to a file.
    NamedTemporaryFile tempFile = new NamedTemporaryFile("parser_data", null);
    runBuckResult =
        workspace.runBuckdCommand(context, "parser-cache", "--save", tempFile.get().toString());
    runBuckResult.assertSuccess();

    // Write an empty content to Apps/BUCK.
    Path path = tmp.getRoot().resolve("Apps/BUCK");
    byte[] data = {};
    Files.write(path, data);

    context = new TestContext();
    // Load the parser cache to a new buckd context.
    runBuckResult =
        workspace.runBuckdCommand(context, "parser-cache", "--load", tempFile.get().toString());
    runBuckResult.assertSuccess();

    // Perform the query again. If we didn't load the parser cache, this call would fail because
    // Apps/BUCK is empty.
    runBuckResult = workspace.runBuckdCommand(context, "query", "deps(//Apps:TestAppsLibrary)");
    runBuckResult.assertSuccess();
    assertThat(
        runBuckResult.getStdout(),
        Matchers.containsString(
            linesToText(
                "//Apps:TestAppsLibrary",
                "//Libraries/Dep1:Dep1_1",
                "//Libraries/Dep1:Dep1_2",
                "bar//Dep2:Dep2")));
  }

  @Test
  public void testInvalidate() throws IOException {
    ProjectWorkspace workspace =
        TestDataHelper.createProjectWorkspaceForScenario(this, "parser_with_cell", tmp);
    workspace.setUp();

    // Warm the parser cache.
    TestContext context = new TestContext();
    ProcessResult runBuckResult =
        workspace.runBuckdCommand(context, "query", "deps(//Apps:TestAppsLibrary)");
    runBuckResult.assertSuccess();
    assertThat(
        runBuckResult.getStdout(),
        Matchers.containsString(
            linesToText(
                "//Apps:TestAppsLibrary",
                "//Libraries/Dep1:Dep1_1",
                "//Libraries/Dep1:Dep1_2",
                "bar//Dep2:Dep2")));

    // Save the parser cache to a file.
    NamedTemporaryFile tempFile = new NamedTemporaryFile("parser_data", null);
    runBuckResult =
        workspace.runBuckdCommand(context, "parser-cache", "--save", tempFile.get().toString());
    runBuckResult.assertSuccess();

    // Write an empty content to Apps/BUCK.
    Path path = tmp.getRoot().resolve("Apps/BUCK");
    byte[] data = {};
    Files.write(path, data);

    // Write an empty content to Apps/BUCK.
    Path invalidationJsonPath = tmp.getRoot().resolve("invalidation-data.json");
    String jsonData = "[{\"path\":\"Apps/BUCK\",\"status\":\"M\"}]";
    Files.write(invalidationJsonPath, jsonData.getBytes(StandardCharsets.UTF_8));

    context = new TestContext();
    // Load the parser cache to a new buckd context.
    runBuckResult =
        workspace.runBuckdCommand(
            context,
            "parser-cache",
            "--load",
            tempFile.get().toString(),
            "--changes",
            invalidationJsonPath.toString());
    runBuckResult.assertSuccess();

    // Perform the query again.
    try {
      workspace.runBuckdCommand(context, "query", "deps(//Apps:TestAppsLibrary)");
    } catch (HumanReadableException e) {
      assertThat(
          e.getMessage(), Matchers.containsString("//Apps:TestAppsLibrary could not be found"));
    }
  }

  @Test
  public void testInvalidData() throws IOException {
    Map<String, String> invalidData = new HashMap();
    invalidData.put("foo", "bar");

    NamedTemporaryFile tempFile = new NamedTemporaryFile("invalid_parser_data", null);
    try (FileOutputStream fos = new FileOutputStream(tempFile.get().toString());
        ZipOutputStream zipos = new ZipOutputStream(fos)) {
      zipos.putNextEntry(new ZipEntry("parser_data"));
      try (ObjectOutputStream oos = new ObjectOutputStream(zipos)) {
        oos.writeObject(invalidData);
      }
    }

    ProjectWorkspace workspace =
        TestDataHelper.createProjectWorkspaceForScenario(this, "parser_with_cell", tmp);
    workspace.setUp();

    // Load the invalid parser cache data.
    thrown.expect(InvalidClassException.class);
    thrown.expectMessage("Can't deserialize this class");
    workspace.runBuckCommand("parser-cache", "--load", tempFile.get().toString());
  }
}
