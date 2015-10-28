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

// user defined tags
object user : Tag()
object special : Tag()

class TagTest {
  fun test_it_is_named_as_its_simple_class_name() {
    for ((tag, expectedName) in mapOf(unit to "unit",
                                      integration to "integration",
                                      pending to "pending",
                                      user to "user",
                                      special to "special")) {
      assert(tag.name == expectedName)
    }
  }
}