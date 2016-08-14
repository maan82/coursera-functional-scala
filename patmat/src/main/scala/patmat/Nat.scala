package patmat

/**
  * Created by ravi on 13/08/2016.
  */
abstract class Nat {

  def isZero : Boolean
  def predecessor : Nat
  def successor : Nat
  def + (that:Nat) :Nat
  def - (that:Nat) :Nat

}

class Zero extends Nat{
  override def isZero: Boolean = true

  override def successor: Nat = ???

  override def +(that: Nat): Nat = ???

  override def -(that: Nat): Nat = ???

  override def predecessor: Nat = ???
}

class Succ(n:Nat) extends Nat{
  override def isZero: Boolean = false

  override def successor: Nat = ???

  override def +(that: Nat): Nat = ???

  override def -(that: Nat): Nat = ???

  override def predecessor: Nat = ???
}