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


package org.apache.hadoop.fs.swifta.snative;

import java.io.IOException;
import org.apache.hadoop.fs.BufferedFSInputStream;
import org.apache.hadoop.fs.FSInputStream;
import org.apache.hadoop.fs.swifta.exceptions.SwiftConnectionClosedException;

/**
 * Add stricter compliance with the evolving FS specifications.
 */
public class StrictBufferedFsInputStream extends BufferedFSInputStream {

  public StrictBufferedFsInputStream(FSInputStream in, int size) {
    super(in, size);
  }

  @Override
  public void seek(long pos) throws IOException {
    if (pos < 0) {
      throw new IOException("Negative position");
    }
    if (in == null) {
      throw new SwiftConnectionClosedException("Stream closed");
    }
    super.seek(pos);
  }
}
