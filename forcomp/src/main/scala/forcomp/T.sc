def isSafe(col:Int, queens:List[Int]) : Boolean = {
  val rows = queens.length
  val queensWithRow = (rows - 1 to 0 by -1) zip queens
  queensWithRow forall {
    case (r,c) => col != c && math.abs(col - c) != rows - r
  }
}
def queens(n: Int) = {
  def placeQueens(k: Int): Set[List[Int]] = {
    if (k == 0) Set(List())
    else
      for  {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      }  yield col :: queens
  }
  placeQueens(n)
}
//queens(4)

//for (i <- 1 to 10; y <- i to 10 if i == y ) yield i + y

def comb(list:List[Int]) :Set[List[Int]] = {
  if(list.isEmpty)
    Set(List())
  else {
    val combs = comb(list.tail)
    (for (c <- combs) yield list.head :: c) ++ combs
  }

}

comb(List(1,2,3,4)).toList.sortBy(l => l.length)

