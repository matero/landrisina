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
 *
 */
public class TagsTest
{
  static final Tag PENDING = Tag.of("pending");
  static final Tag FUNCTIONAL = Tag.of("functional");
  static final Tag UNIT = Tag.of("unit");
  static final Tag ACCEPTANCE = Tag.of("acceptance");

  public void test_NONE_does_not_have_any_tag() {
    assert !Tags.NONE.iterator().hasNext() : "NONE should be empty";
  }

  public void test_when_combined_with_an_empty_tags_definition_Tags_then_it_should_return_itself() {
    final Tags tags = new Tags(ImmutableSet.of(UNIT));
    assert tags.and(Tags.NONE) == tags : "it should returns itself";
  }

  public void test_when_combined_with_itself_then_it_should_return_itself() {
    final Tags tags = new Tags(ImmutableSet.of(UNIT));
    assert tags.and(tags) == tags : "it should returns itself";
  }

  public void test_when_combined_with_a_subset_of_itself_then_it_should_return_itself() {
    final Tags tags = new Tags(ImmutableSet.of(UNIT, PENDING));
    assert tags.and(new Tags(ImmutableSet.of(UNIT))) == tags : "it should returns itself";
  }

  public void test_when_combined_with_a_superset_of_itself_then_it_should_return_the_other_tags() {
    final Tags tags = new Tags(ImmutableSet.of(UNIT, PENDING));
    assert new Tags(ImmutableSet.of(UNIT)).and(tags) == tags : "it should returns the superset";
  }

  public void test_when_combined_with_a_different_tags_definition_then_it_should_return_the_combination_of_both() {
    final Tags tags = new Tags(ImmutableSet.of(UNIT, PENDING));
    final Tags different = new Tags(ImmutableSet.of(UNIT, ACCEPTANCE));
    assert different.and(tags).equals(new Tags(ImmutableSet.of(UNIT, PENDING, ACCEPTANCE))) : "it should returns the combination of both";
  }

  public void test_toString_should_show_tags_sequence() {
    final String tags = new Tags(ImmutableSet.of(UNIT, Tag.of("extra"))).toString();
    assert ("Tags[#unit, #extra]".equals(tags) || "Tags[#extra, #unit]".equals(tags)) : "it should show that #unit and #extra tags are defined";
    assert "Tags[]".equals(Tags.NONE.toString()) : "it should show that no tags are defined";
    assert "Tags[#pending]".equals(new Tags(ImmutableSet.of(PENDING)).toString()) : "it should show that pending tag is defined";
  }
}
