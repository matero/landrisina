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

import com.google.common.collect.ImmutableSet;

/**
 * Checks correct behavior of {@link Taggeable} definitions.
 * <p>
 * Tests defned to be runned with maven surefre plugin, using the POJO provider.
 * </p>
 */
public class TaggeableTest extends Taggeable
{
  static final Tag PENDING = Tag.of("pending");
  static final Tag FUNCTIONAL = Tag.of("functional");
  static final Tag UNIT = Tag.of("unit");
  static final Tag ACCEPTANCE = Tag.of("acceptance");

  static final Tags UNUSED_TAGS = new Tags(ImmutableSet.of(PENDING, FUNCTIONAL));
  static final Tags USED_TAGS = new Tags(ImmutableSet.of(UNIT, ACCEPTANCE));

  public TaggeableTest() {
    super(USED_TAGS);
  }

  public void test_when_a_tag_is_used_then_it_should_be_recognized() {
    for (Tag tag : USED_TAGS) {
      assert is(tag) : "it should recognize " + tag;
    }
  }

  public void test_when_a_tag_is_used_then_its_name_should_be_recognized() {
    for (Tag tag : USED_TAGS) {
      assert is(tag.value) : "it should recognize the tag name " + tag.value;
    }
  }

  public void test_when_a_tag_isnt_used_then_it_should_not_be_recognized() {
    for (Tag tag : UNUSED_TAGS) {
      assert !is(tag) : "it should not recognize " + tag;
    }
  }

  public void test_when_a_tag_isnt_used_then_its_name_should_not_be_recognized() {
    for (Tag tag : UNUSED_TAGS) {
      assert !is(tag.value) : "it should not recognize tag name " + tag.value;
    }
  }
}