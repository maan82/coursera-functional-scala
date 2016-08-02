package funsets

class Canvas(w:Int , h:Int) {
  def width = w
  def height = h
  var space = Array.ofDim[Char](width, height)

  def addPoint(x: Int, y:Int): Unit = {
    space(x)(y) = 10;
  }

  def addLine(x1:Int, y1:Int, x2:Int, y2:Int): Unit = {
    require(x1 != x2 && y1 != y2, "Only vertical or horizontal line can be drawn")
    addPoint(x1, y1)
    if (x1 == x2) {
      addLine(x1 + 1, y1, x2, y2)
    } else {
      addLine(x1, y1 +1, x2, y2)
    }
  }


}

/**
  * Created by ravi on 02/08/2016.
  */
object Painter {



  def main(args: Array[String]) {

  }


}
