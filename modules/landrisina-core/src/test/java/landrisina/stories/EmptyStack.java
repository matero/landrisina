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
package landrisina.stories;

import landrisina.Stack;

/**
 * Specifies the behaviour of empty stacks
 */
class EmptyStack implements landrisina.Story
{
  Stack stack;

  public EmptyStack() {
      title("will this title instead of class simple name interpretation");
      description("each string here",
        "is a line to be displayed as",
        "specification description",
        "after specification title");
  }
  
  public void test_scenario_is_correctly_defined() {
    scenario("null is pushed onto empty stack", tagged(pending),
        given("an empty stack", () -> {
          stack = new Stack();
        }),
        when("null is pushed", () -> {
          stack.push(null);
        }),
        then("an exception should be thrown", () -> {
          assert (hasThrown() instanceof NullPointerException);
        })
        .and("then the stack should still be empty", () -> {
          assert stack.isEmpty();
        }));
  }
}