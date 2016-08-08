package funsets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PainterSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s_3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(contains(s2, 2), "Singleton")
      assert(contains(s3, 3), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("union contains all elements of nested sets") {
    new TestSets {
      val s = union(union(union(s1, s2), s3), singletonSet(4))
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(contains(s, 3), "Union 3")
      assert(contains(s, 4), "Union 3")
    }
  }

  test("intersect contains none if no common elements of both set") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect 1")
      assert(!contains(s, 2), "Intersect 2")
      assert(!contains(s, 3), "Intersect 3")
    }
  }

  test("intersect contains when common elements") {
    new TestSets {
      val s = intersect(s3, s_3)
      val d = intersect(intersect(s3, s_3), s3)
      assert(contains(s, 3), "Intersect 3")
      assert(contains(d, 3), "Intersect 3")
    }
  }

  test("combine union and intersect") {
    new TestSets {
      val s = intersect(union(union(s1, s2), s3), union(union(s1, s3), singletonSet(5)))
      assert(contains(s, 1), "Contains 1")
      assert(contains(s, 3), "Contains 3")
      assert(!contains(s, 2), "Contains 3")
      assert(!contains(s, 5), "Contains 5")
    }
  }

  test("diff") {
    new TestSets {
      val s = diff(union(union(s1, s2), s3), union(union(s1, s3), singletonSet(5)))
      assert(!contains(s, 1), "!Contains 1")
      assert(contains(s, 2), "Contains 2")
      assert(!contains(s, 3), "!Contains 3")
      assert(!contains(s, 5), "!Contains 5")
    }
  }

  test("filter") {
    new TestSets {
      val s = union(union(union(s1, s2), s3), union(union(s1, s3), singletonSet(5)))
      var f = filter(s, a => a == 1)
      assert(contains(f, 1), "Contains 1")
      assert(!contains(f, 2), "!Contains 2")
      assert(!contains(f, 3), "!Contains 3")
      assert(!contains(f, 5), "!Contains 5")
    }
  }

  test("filter edge") {
    new TestSets {
      val s = union(union(union(s1, s2), s3), union(union(s1, singletonSet(4)), singletonSet(5)))
      var f = filter(s, a => a % 2 == 1)
      assert(contains(f, 1), "Contains 1")
      assert(!contains(f, 2), "!Contains 2")
      assert(contains(f, 3), "Contains 3")
      assert(!contains(f, 4), "!Contains 4")
      assert(contains(f, 5), "Contains 5")
    }
  }

  test("forall") {
    new TestSets {
      val s = intersect(union(union(s1, s2), s3), union(union(s1, s3), singletonSet(5000)))
      assert(forall(s, a => a < 4), "less than 4")
      assert(!forall(s, a => a < 2), "less than 2")

    }
  }

  test("forall negative") {
    new TestSets {
      val s = intersect(union(union(s1, s2), singletonSet(-500)), union(union(s1, singletonSet(-500)), singletonSet(-5000)))
      assert(forall(s, a => a < 4), "less than 4")
      assert(forall(s, a => a > -600), "less than 2")
      assert(!forall(s, a => a > -100), "greater than -100")

    }
  }

  test("exists") {
    new TestSets {
      val s = union(union(s1, s2), union(s3, singletonSet(1001)))

      assert(exists(s, (a => a < 4)))
      assert(exists(s, (a => a > 2)))
      assert(!exists(s, (a => a < -10)))
    }
  }
    test("map") {
    new TestSets {
      val s = union(union(s1, s2), singletonSet(-500))
      assert(contains(map(s, a => a * a), 1), "square 1")
      assert(contains(map(s, a => a * a), 4), "square 2")
      assert(contains(map(s, a => a + a), -1000), "plus -500")

    }
  }

}
