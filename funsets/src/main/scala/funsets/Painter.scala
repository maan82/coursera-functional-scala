package funsets


class Command(commandline:String) {
  def line : Array[String] = commandline.split(" ")

  def getCommand = line(0)

  def is
}
class Canvas(w:Int , h:Int) {
  def width = w
  def height = h
  var space = Array.ofDim[Char](width + 2, height + 2)

  setBlank()
  setBoundry()

  def setBlank(): Unit = {
    for (i <- 0 to space.length - 1)
      for(j <- 0 to space(i).length - 1) {
        space(i)(j) = ' '
      }
  }

  def setBoundry(): Unit = {
    addLine(0, 0, w + 1, 0, '-')
    addLine(0, 1, 0, h, '|')
    addLine(0, h+1, w +1, h+1, '-')
    addLine(w + 1, 1, w + 1, h, '|')
  }

  def addPoint(x: Int, y:Int, value:Char): Unit = {
    require(x <= w + 1 && y <= h + 1, "Invalid point coordinate")
    space(y)(x) = value;
  }

  def addLine(x1:Int, y1:Int, x2:Int, y2:Int, value:Char): Unit = {
    require(x1 == x2 || y1 == y2, "Only vertical or horizontal line can be drawn x1 : "+x1+" y1 : "+y1+" x2 : "+x2+" y2 : "+y2 )
    addPoint(x1, y1, value)
    if(x1 == x2 && y1 == y2) {

    } else {
      if (x1 == x2) {
        addLine(x1, y1 + 1, x2, y2, value)
      } else {
        addLine(x1 + 1, y1, x2, y2, value)
      }
    }
  }

  def addRectangle(x1:Int, y1:Int, x2:Int, y2:Int) : Unit = {
    addLine(x1, y1, x2, y1, 'x')
    addLine(x1, y1, x1, y2, 'x')
    addLine(x1, y2, x2, y2, 'x')
    addLine(x2, y1, x2, y2, 'x')
  }

  def fill(x:Int, y:Int, color:Char) : Unit = {
    if(space(y)(x) == ' ') {
      addPoint(x, y, color)
      fill(x + 1 , y, color)
      fill(x - 1 , y, color)
      fill(x , y + 1, color)
      fill(x , y - 1, color)
    }
  }

  def printCanvas(): Unit = {
    for (i <- 0 to width + 1)
      for(j <- 0 to height + 1) {
        print(space(i)(j))
        if (j == height + 1) {
          println()
        }
      }

  }


}

/**
  * Created by ravi on 02/08/2016.
  */
object Painter {



  def main(args: Array[String]) {
    val canvas: Canvas = new Canvas(10, 10)
    while (true) {
      val command = io.StdIn.readLine()
      canvas.printCanvas()
      canvas.addRectangle(2, 2, 5, 5)
      canvas.printCanvas()
      canvas.addLine(1, 7, 10, 7, 'x')
      canvas.printCanvas()
      canvas.fill(6, 6, 'c')
      canvas.printCanvas()
    }
  }


}
