import scala.annotation.tailrec

def abs(x: Double) : Double = if (x < 0) -x else x
abs(-2)
abs(2)

def sqrt(x: Double) : Double = {
  sqrtIter(x, 1)
}

def isGoodEnough(x: Double, guess: Double) : Boolean = {
  abs(guess * guess - x) < 0.001
}

def improve(x: Double, guess: Double) : Double = {
  ( x / guess + guess ) / 2
}

def sqrtIter(x: Double, guess: Double) : Double = {
  if(isGoodEnough(x, guess)) guess
  else sqrtIter(x, improve(x, guess))
}

sqrt(4)
sqrt(9)
sqrt(1e10)
sqrt(1e-10)

def factorial(x: Int) : Int = {
  if (x == 0) {
    1
  } else {
    x * factorial(x - 1)
  }
}

@tailrec
def factorialTailRecursive(x: Int, carry: Int) : Int = {
  if (x == 0) {
    1 * carry
  } else {
    factorialTailRecursive(x - 1,  x * carry)
  }
}


factorial(5)
factorial(10)

factorialTailRecursive(5, 1) == factorial(5)

factorialTailRecursive(2, 1) == factorial(2)


def sum(f: Int => Int):(Int, Int) => Int = {
  def sumF(a: Int, b :Int):Int = {
    if(a > b) 0
    else f(a) + sum(f)(a+1, b)
  }
  sumF
}

sum(x=>x)(1, 3)



