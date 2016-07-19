package recfun

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PascalSuite extends FunSuite {
  import Main.pascal
    test("pascal: col=0,row=2") {
      assert(pascal(0,2) === 1)
  }

    test("pascal: col=1,row=2") {
      assert(pascal(1,2) === 2)
  }

    test("pascal: col=1,row=3") {
      assert(pascal(1,3) === 3)
  }

  test("pascal: col=2,row=4") {
    assert(pascal(2, 4) === 6)
  }

  test("pascal: property based testing") {
    for (c <- 0 to 10) {
      for (r <- 0 to c) {
        if(c == 0 || r == 0 || c == r) {
          assert(pascal(c, r) === 1)
        }
      }
    }
  }

  test("pascal: full row") {
    for(i <- 0 to 7) {
      assert(pascal(i, 7) ===  List(1,7,21,35,35,21,7,1)(i), "i :"+ i)
    }
  }

}
