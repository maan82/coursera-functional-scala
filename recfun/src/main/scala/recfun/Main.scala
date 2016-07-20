package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || r == 0 || c == r) {
      1
    } else {
      pascal(c, r - 1) + pascal(c - 1, r - 1)
    }
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def loop(remainingChars: List[Char], acc: Int): Boolean = {
      if (remainingChars.isEmpty && acc == 0) {
        return true
      }
      if (acc < 0 || (remainingChars.isEmpty && acc != 0)) {
        return false
      }
      if (remainingChars.head == '(') {
        return loop(remainingChars.tail, acc + 1)
      } else if (remainingChars.head == ')') {
        return loop(remainingChars.tail, acc - 1)
      } else {
        return loop(remainingChars.tail, acc)
      }
    }

    loop(chars, 0)
  }


  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1
    } else if (money > 0 && !coins.isEmpty) {
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    } else {
      0
    }
  }
}
