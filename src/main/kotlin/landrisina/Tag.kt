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

/**
 * Marks [Specification specifications] / [Story stories] allowing to (un)select them
 * based on which tags it has.
 *
 * When a tag is defined it use its simple class name as tag name.
 * Sample definitions:
 * ```kotlin
 * object SlowTest : Tag() // declares the tag "SlowTest"
 * object fast: Tag() // declares the tag "fast"
 * ```
 */
abstract class Tag() {
  val name: String by OwnerClassName()

  operator fun plus(other: Tag): Set<Tag> = setOf(this, other)
}

// default Landrisina tags

/** Denotes [Specification specifications] / [Story stories] which has its implementation pending or incomplete. */
object pending:Tag()
/** Denotes [Specification specifications] / [Story stories] that should be considered integration tests. */
object integration:Tag()
/** Denotes [Specification specifications] / [Story stories] that should be considered unit tests. */
object unit:Tag()