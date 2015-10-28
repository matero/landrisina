package landrisina

import java.util.*


internal class ClassNameIfNull(val name: String?) {
  fun get(thisRef: Any?, property: PropertyMetadata): String = name?:thisRef!!.javaClass.simpleName
}

abstract class Tag(title: String? = null) {
  val name: String by ClassNameIfNull(title)

  operator fun plus(other: Tag): Set<Tag> = setOf(this, other)
}

inline operator fun Set<Tag>.plus(t:Tag): Set<Tag> {
  val result = HashSet(this)
  result.add(t)
  return result
}

object pending:Tag("pending")
object integration:Tag("integration")
object unit:Tag()

class SpecificationDescription(title: String) {
  class Story(title: String) {
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
                             info: String = "",
                             tagged: Set<Tag> = emptySet(),
                             describedAs: SpecificationDescription.() -> Unit) {
  private val spec: SpecificationDescription
  init {
      spec = SpecificationDescription(title)
      spec.describedAs()
  }

  constructor(title: String = "",
              info: String = "",
              tagged: Tag,
              describedAs: SpecificationDescription.() -> Unit) : this(title, info, setOf(tagged), describedAs)
}

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

class Topping(val name: String)

class Pizza {
  private val toppings = LinkedList<Topping>()

  fun addTopping(t: Topping) {
    toppings.add(t)
  }

  fun topping(at: Int): Topping = toppings[at]

  val toppingsCount: Int
    get() = toppings.size()

  fun removeTopping(topping: Topping) {
    toppings.remove(topping)
  }
}

object Pizzas : Specification(title="a pizza",
                              info="""or multiline
                                      strings, is
                                      up to you""",
                              tagged=(pending + unit),
                              describedAs = {
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
