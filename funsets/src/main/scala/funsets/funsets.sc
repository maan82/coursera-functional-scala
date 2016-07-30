import scala.annotation.tailrec

def sum(f: Int => Int):(Int, Int) => Int = {
  def sumF(a: Int, b :Int):Int = {
    if(a > b) 0
    else f(a) + sum(f)(a+1, b)
  }
  sumF
}

def sumInt(x: Int) : Int = x
def sumSqr(x: Int) : Int =  x * x;
def loop() : Boolean = loop

sum(sumInt)(1, 4)
sum(sumSqr)(1, 3)


def product(f:Int => Int )(a: Int, b:Int) : Int = {
  if (a > b) {
    1
  } else {
    f(a) * product(f)(a + 1, b)
  }
}

def fact(x: Int) : Int = {
  product(x => x)(1, x)
}

product(x => x * x)(1, 3)
fact(5)

def mapReduce(map:Int => Int, combine:(Int, Int) => Int, initial: Int)(a: Int, b: Int) : Int = {
  if (a > b) initial
  else combine(map(a), mapReduce(map, combine, initial)(a+1, b))
}

def productF(a: Int, b: Int): Int = {
  mapReduce(x => x , (a , b) => a * b, 1)(a, b)
}

mapReduce(x => x , (a , b) => a * b, 1)(1, 3)

class Rational(x: Int, y:Int){
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  def numer() = { x / gcd(x, y) }
  def denom() = { y / gcd(x, y) }

  def add(r: Rational) : Rational = {
    new Rational(numer * r.denom + r.numer * denom,
      denom * r.denom)
  }

  def +(r: Rational) : Rational = {
    add(r)
  }

  override def toString() : String = {
    numer + "/" + denom
  }

}

new Rational(1,20) add new Rational(1,20)

productF(1, 4)