package messages

class Message(text:String){

  override def toString: String =
    " text : " + text
}

abstract class MessageSet {
  def filter(p:Message => Boolean) : MessageSet = ???
}

class EmptyMessageSet extends MessageSet {
  override def filter(p:Message => Boolean) : MessageSet = new EmptyMessageSet
}

class NonEmptyMessageSet(elem: Message, next:MessageSet) extends MessageSet {
  override def filter(p:Message => Boolean) : MessageSet = {
    if(p(elem)) {
      new NonEmptyMessageSet(elem, next.filter(p))
    } else {
      next.filter(p)
    }
  }


  override def toString = elem.toString + next.toString
}

abstract class MessageTree {
  def filter(p:Message => Boolean) : MessageTree
}

class EmptyMessageTree extends MessageTree () {
  override def toString = ""
  override def filter(p:Message => Boolean) : MessageTree = new EmptyMessageTree
}

class NonEmptyMessageTree(elem:Message, left:MessageTree, right:MessageTree) extends MessageTree {

  override def toString =  left.toString + " / " + elem.toString + " \\ " + right.toString

  override def filter(p: (Message) => Boolean): MessageTree = {
    if(p(elem)) {
      new NonEmptyMessageTree(elem, left.filter(p), right.filter(p))
    } else {
      left.filter(p)
    }
  }

}

object Main{
  def main(args: Array[String]) {
    val message1: Message = new Message("1")
    val message2: Message = new Message("2")
    val message3: Message = new Message("3")
    val message4: Message = new Message("4")

    val messageSet: NonEmptyMessageSet = new NonEmptyMessageSet(message3, new NonEmptyMessageSet(message2, new NonEmptyMessageSet(message1, new EmptyMessageSet)))

    //println( messageSet.filter((p:Message) => { p.getSender() == "a" }))
    println(new NonEmptyMessageTree(message1,  new NonEmptyMessageTree(message2, new EmptyMessageTree, new EmptyMessageTree), new NonEmptyMessageTree(message3, new EmptyMessageTree, new EmptyMessageTree)))
  }
}