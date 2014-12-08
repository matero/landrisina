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

/**
 * Checks that tags behaves as expected
 */
public class TagTest
{
  public void test_when_traduced_to_string_it_should_prepend_tag_name_with_hash() {
    assert "#tag".equals(new Tag("tag").toString()) : "it should be translated as '#tag'";
  }

  public void test_tags_are_equivalent_when_they_have_the_same_value() {
    Tag aTag = new Tag("tag");
    Tag sameTag = new Tag("tag");
    Tag differentTag = new Tag("differentTag");

    assert aTag.equals(sameTag) : "#tag should be equal to #tag";
    assert !aTag.equals(differentTag) : "#tag should no be equal to #differentTag";
  }

  public void test_should_create_tag_only_the_first_time_it_is_accessed() {
    Tag unit = Tag.of("unit");
    assert unit == Tag.of("unit") : "the tag " + unit + " should not be created more than one time.";
    assert Tag.of("test") == Tag.of("test") : "the tag #test should not be created more than one time.";
  }
}
