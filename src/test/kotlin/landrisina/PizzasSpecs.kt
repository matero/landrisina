/*
Copyright 2015 matero@gmail.com.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package landrisina

class PizzaSpecs : Specification(
    title = "a pizza",
    info =
"""simple or multiline
strings, is up to you""",
    tagged = pending + unit + integration,
    described = {
  var pizza: Pizza

  It("should allow the addition of toppings") {
    Given("a new pizza") {
      pizza = Pizza()
    }.
    When("a topping is added") {
      pizza.addTopping(Topping("green olives"))
    }.
    Then("the topping count should be incremented") {
      pizza.toppingsCount == 1
    }.
    And("the topping should be what was added") {
      pizza.topping(0) == Topping("green olives")
    }
  }

  It("Should start with no toppings") {
    Given("a new pizza") {
      pizza = Pizza()
    }.
    Expect("the topping count should be zero") {
      pizza.toppingsCount == 0
    }
  }

  It("Should allow removal of toppings") {
    Given("a pizza with one topping") {
      pizza = Pizza()
      pizza.addTopping(Topping("green olives"))
    }.
    When("the topping is removed") {
      pizza.removeTopping(Topping("green olives"))
    }.
    Then("the topping count should be zero") {
      pizza.toppingsCount == 0
    }
  }
})