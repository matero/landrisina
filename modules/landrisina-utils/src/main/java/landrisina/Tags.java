/*****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ****************************************************************/
package landrisina;

import java.util.Arrays;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;

/**
 * 
 */
public final class Tags implements Iterable<Tag>
{
  static final Tags NONE = new Tags(ImmutableSet.of());

  private final ImmutableSet<Tag> values;

  Tags(final ImmutableSet<Tag> tags) {
    this.values = tags;
  }

  boolean contains(final Tag tag) {
    return this.values.contains(tag);
  }

  @Override public UnmodifiableIterator<Tag> iterator() {
    return this.values.iterator();
  }

  public Tags and(final Tag... tags) {
    return and(new Tags(ImmutableSet.copyOf(tags)));
  }

  public Tags and(final Tags other) {
    if (other.values.isEmpty()) {
      return this;
    }
    if (this.values.isEmpty()) {
      return other;
    }
    if (this.values.equals(other.values)) {
      return this;
    }
    if (this.values.containsAll(other.values)) {
      return this;
    }
    if (other.values.containsAll(this.values)) {
      return other;
    }
    final ImmutableSet.Builder<Tag> combinedValues = ImmutableSet.builder();
    combinedValues.addAll(this.values);
    combinedValues.addAll(other.values);
    return new Tags(combinedValues.build());
  }

  @Override public String toString() {
    return "Tags" + Arrays.toString(this.values.toArray());
  }

  @Override public int hashCode() {
    return values.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj instanceof Tags) {
      Tags other = (Tags) obj;
      return this.values.equals(other.values);
    }
    return false;
  }

}
