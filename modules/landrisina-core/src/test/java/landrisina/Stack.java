/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package landrisina;

import java.util.LinkedList;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Simple class to ensure correct behavior of landrisina.
 */
public class Stack {
  private final LinkedList<Object> data;

  public Stack() {
    this.data = new LinkedList<>();
  }

  public void push(final @Nullable Object element) {
    if (element == null) {
      throw new NullPointerException("element");
    }
    this.data.push(element);
  }

  public @Nullable Object pop() {
    if (isEmpty()) {
      throw new IllegalStateException("stack is empty");
    }
    return this.data.pop();
  }

  public @Nullable Object peek() {
    if (isEmpty()) {
      throw new IllegalStateException("stack is empty");
    }
    return this.data.peek();
  }

  public boolean isEmpty() {
    return this.data.isEmpty();
  }
}
