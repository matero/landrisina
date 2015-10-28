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

class GivenExpr(val title: String, val initCode: () -> Unit) {
  fun And(title: String, initCode: () -> Unit) = GivenExpr(title, initCode)
  fun When(title: String, initCode: () -> Unit) = WhenExpr(title, initCode)
  fun Expect(title: String, validation: () -> Boolean) = ThenExpr(title, validation)
}

class WhenExpr(val title: String, val action: () -> Unit) {
  fun Then(title: String, validation: () -> Boolean) = ThenExpr(title, validation)
}

class ThenExpr(val title: String, val validation: () -> Boolean) {
  fun And(title: String, validation: () -> Boolean) = ThenExpr(title, validation)
}