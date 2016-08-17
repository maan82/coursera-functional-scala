package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("weight of a larger tree t2") {
    new TestTrees {
      assert(weight(t2) === 9)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("chars of tree t1") {
    new TestTrees {
      assert(chars(t1) === List('a','b'))
    }
  }

  test("chars of tree leaf only") {
    new TestTrees {
      assert(chars(Leaf('a', 10)) === List('a'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("times") {
    assert(times(string2Chars("hello, wol")).sortBy(_._1).sortBy(_._2) === List(('h', 1),('e', 1),('l', 3),('o', 2),(',', 1),(' ', 1),('w', 1)).sortBy(_._1).sortBy(_._2))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("makeOrderedLeafList for some frequency table big") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 5), ('x', 4), ('o', 1), ('r', 3))) === List(Leaf('o',1), Leaf('t',2), Leaf('r',3), Leaf('x',4), Leaf('e',5)))
  }

  test("makeOrderedLeafList for some frequency table empty") {
    assert(makeOrderedLeafList(List()) === List())
  }

  test("makeOrderedLeafList for some frequency table one") {
    assert(makeOrderedLeafList(List(('t', 2))) === List(Leaf('t',2)))
  }

  test("singleton") {
    assert(singleton(List(new Leaf('t', 2))) === true)
  }

  test("singleton empty") {
    assert(singleton(List()) === false)
  }

  test("singleton non empty") {
    assert(singleton(List(new Leaf('t', 2), new Leaf('s', 2))) === false)
  }



  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and encode a very short text should be identity big") {
    new TestTrees {
      assert(decode(t2, encode(t2)("dab".toList)) === "dab".toList)
    }
  }

  test("decode and quickencode a very short text should be identity big") {
    new TestTrees {
      assert(decode(t2, quickEncode(t2)("dab".toList)) === "dab".toList)
    }
  }

}
