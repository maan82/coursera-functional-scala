package messages

class Message(sender:String, receiver:String,text:String){
  def getSender() = sender

  override def toString: String = "sender : " + getSender() +
    " receiver : " + receiver +
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

object Main{
  def main(args: Array[String]) {
    val message1: Message = new Message("a", "b", "1 from a to b")
    val message2: Message = new Message("a", "b", "2 from a to b")
    val message3: Message = new Message("a", "b", "3 from a to b")
    val message4: Message = new Message("4", "b", "4 from a to b")

    val messageSet: NonEmptyMessageSet = new NonEmptyMessageSet(message3, new NonEmptyMessageSet(message2, new NonEmptyMessageSet(message1, new EmptyMessageSet)))

    println( messageSet.filter((p:Message) => { p.getSender() == "a" }))
  }
}