package funsets

class PainterFuntional {

  type Point = (Int, Int, Char) => Char

  def contains(set:Point, x:Int, y:Int, color:Char) : Char = set(x, y, color)

  def point(x:Int, y:Int, color:Char) : Point = {
    def p(xInner:Int, yInner:Int, color:Char) : Char = {
      if(x == xInner && y == yInner)
        color
      else
        new Nothing()
    }
    p
  }

  def addPoint(pointSet:Point, x:Int, y:Int, color:Char) : Point = {
    def a(x:Int, y:Int, color:Char) : Char = {
      if(contains(pointSet, x, y, color) == new Nothing() ) {
        pointSet(x,y, color) != new Nothing() || point(x, y, color) != new Nothing()
      } else {
        ' '
      } //|| newPoint(x, y)
    }
    a
  }

  def pointExist(pointSet:Point, x:Int, y:Int): Boolean = {
    contains(pointSet, x, y)
  }





}
