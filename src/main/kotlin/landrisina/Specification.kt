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

class SpecificationDescription(val title: String) {
  class Story(val title: String) {
    fun Given(title: String, initCode: () -> Unit) = GivenExpr(title, initCode)
  }

  class SetUp(val title: String)

  fun Info(vararg texts: String): Unit {}

  fun Tagged(vararg tags: Tag): Unit {}

  fun It(story: String, block: SpecificationDescription.Story.() -> Unit): SpecificationDescription.Story {
    val result = SpecificationDescription.Story(story)
    result.block()
    return result
  }

  fun Before(title: String = "", block: SpecificationDescription.SetUp.() -> Unit): SpecificationDescription.SetUp {
    val result = SpecificationDescription.SetUp(title)
    result.block()
    return result
  }
}

abstract class Specification(title: String = "",
                             val info: String = "",
                             val tagged: Set<Tag> = emptySet(),
                             described: SpecificationDescription.() -> Unit) {
  private val spec: SpecificationDescription
  init {
    spec = SpecificationDescription(title)
    spec.described()
  }

  constructor(title: String = "",
              info: String = "",
              tagged: Tag,
              describedAs: SpecificationDescription.() -> Unit) : this(title, info, setOf(tagged), describedAs)

  public fun test_to_run_spec() {
    val tags = if (tagged.isEmpty()) "without tags" else "tagged ${tagged.joinToString(separator=","){it.name}}"
    println("running spec ${spec.title}, $tags")
    println("info '$info'")
  }
}
