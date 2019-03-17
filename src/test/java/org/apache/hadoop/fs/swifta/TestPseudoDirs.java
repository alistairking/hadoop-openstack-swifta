/*
 * Copyright (c) [2018]-present, Walmart Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.hadoop.fs.swifta;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;

import static org.apache.hadoop.fs.swifta.util.SwiftTestUtils.*;

/**
 * Test handling of pseudo-directory support
 */
public class TestPseudoDirs extends SwiftFileSystemBaseTest {

  private Path[] testFiles;

  /**
   * Setup creates dirs under test/hadoop
   *
   * @throws Exception
   */
  @Override
  public void setUp() throws Exception {
    super.setUp();
    // delete the test directory
    Path test = path("/test");
    fs.delete(test, true);
  }

  @Override
  public void tearDown() throws Exception {
    //cleanupInTeardown(fs, "/test");
  }

  /**
   * Create some files with a pseudo-directory hierarchy
   * 
   * @throws IOException on an IO problem
   */
  private void createTestFiles() throws IOException {
    testFiles =
        new Path[] {
                path("/test/hadoop/a.txt"),
                path("/test/hadoop/b.txt"),
                path("/test/hadoop/c/1.txt"),
                path("/test/hadoop/c/2.txt"),
        };

    for (Path path : testFiles) {
      touch(fs, path);
    }
  }

  @Test(timeout = SWIFT_TEST_TIMEOUT)
  public void testContainerList() throws Exception {
    createTestFiles();
    // TODO: list "/" and make sure we see "test"
  }

}
