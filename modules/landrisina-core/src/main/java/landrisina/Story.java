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

import java.util.function.Consumer;
import java.util.function.Supplier;

import landrisina.lang.Given;
import landrisina.lang.NarrativeBuilder;
import landrisina.lang.Then;
import landrisina.lang.ThenValue;
import landrisina.lang.When;
import landrisina.lang.WhenValue;

/**
 * 
 */
public interface Story
{
  /* predefined tags */
  Tag pending = tag("pending");
  Tag functional = tag("functional");
  Tag unit = tag("unit");
  Tag acceptance = tag("acceptance");

  static Tag tag(final String value) {
    return Tag.of(value);
  }

  /**
   * @param value
   */
  default void title(String value) {
    System.out.println("title -> value");
  }

  /**
   * @param lines
   */
  default void description(String... lines) {
    System.out.println("description -> " + java.util.Arrays.toString(lines));
  }

  default NarrativeBuilder narrative(final String description) {
    return null;
  }

  default void scenario(String scenario,
                        Tags tags,
                        Given given,
                        When when,
                        Then then) {
    /* nothing to do */
  }

  default <T> void scenario(String scenario,
                            Tags tags,
                            Given given,
                            WhenValue<T> when,
                            Then then) {
    /* nothing to do */
  }

  default <T> void scenario(String scenario,
                            Tags tags,
                            Given given,
                            WhenValue<T> when,
                            ThenValue<T> then) {
    /* nothing to do */
  }

  default Tags tagged(final Tag... values) {
    return null;
  }

  default Tags tagged(final Iterable<Tag> values) {
    return null;
  }

  default Given given(String context) {
    return given(context, Given.NO_SETUP);
  }

  default Given given(String context,
                      Runnable setup) {
    return null;
  }

  default When when(final String somethingHappens,
                    final Runnable action) {
    return null;
  }

  default <T> WhenValue<T> when(final String somethingHappens,
                                final Supplier<T> action) {
    return null;
  }

  default Then then(final String expectation,
                    final Runnable validation) {
    return null;
  }

  default <T> ThenValue<T> then(final String expectation,
                                final Consumer<T> validation) {
    return null;
  }

  default Throwable hasThrown() {
    return null;
  }
}