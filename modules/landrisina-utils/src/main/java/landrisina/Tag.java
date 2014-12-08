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

import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 *
 */
public final class Tag
{
  private static class TagCacheLoader extends CacheLoader<String, Tag>
  {
    @Override public final Tag load(final String value) {
      return new Tag(value);
    }
  }

  private static final LoadingCache<String, Tag> VALUES = CacheBuilder.newBuilder().build(new TagCacheLoader());

  final String value;

  Tag(final String value) {
    this.value = value;
  }

  @Override public int hashCode() {
    return this.value.hashCode();
  }

  @Override public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Tag) {
      final Tag other = (Tag) obj;
      return this.value.equals(other.value);
    }
    return false;
  }

  @Override public String toString() {
    return "#" + this.value;
  }

  public static Tag of(final String value) {
    try {
      return Tag.VALUES.get(value);
    } catch (final ExecutionException e) {
      throw new IllegalStateException("could not create the tag '" + value + "'.", e);
    }
  }
}