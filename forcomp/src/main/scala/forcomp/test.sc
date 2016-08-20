def isSafe(col:Int, queens:List[Int]) : Boolean = {
  val rows = queens.length
  val queensWithRow = (rows - 1 to 0 by -1) zip queens
  queensWithRow.forall({
    case (r,c) => col != c && math.abs(col - c) != rows - r
  })
}

def placeQueens(n:Int) : Set[List[Int]] = {
  if (n == 0)
    Set(List())
  else
    for(
      queens <- placeQueens(n - 1);
      col <- 0 to n
      if( isSafe(col, queens))
    ) yield col :: queens
}

def queens(n:Int) : Set[List[Int]] = {

  placeQueens(n)
}
queens(3)
List(11)