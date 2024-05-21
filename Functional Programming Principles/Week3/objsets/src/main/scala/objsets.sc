import scala.annotation.tailrec

abstract class IntSet:
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
  def union(s: IntSet): IntSet

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet:
  def contains(x: Int): Boolean =
    if x < elem then left.contains(x)
    else if x > elem then right.contains(x)
    else true

  def incl(x: Int): IntSet =
    if x < elem then NonEmpty(elem, left.incl(x), right)
    else if x > elem then NonEmpty(elem, left, right.incl(x))
    else this

  def union(s: IntSet): IntSet =
    left.union(right).union(s).incl(elem)

end NonEmpty

class Empty() extends IntSet:
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = NonEmpty(x, Empty(), Empty())
  def union(s: IntSet): IntSet = s

@tailrec
def nth[T](xs: List[T], n: Int): T =
  if xs.isEmpty then throw new IndexOutOfBoundsException()
  else if n == 0 then xs.head
  else nth(xs.tail, n - 1)

abstract class Nat:
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat
  def +(that: Nat): Nat
  def -(that: Nat): Nat
end Nat

class Succ(n: Nat) extends Nat:
  def isZero: Boolean = false

  def predecessor: Nat = n

  def successor: Nat = Succ(this)

  def +(that: Nat): Nat = Succ(n + that)

  def -(that: Nat): Nat = if that.isZero then this else n - that.predecessor

  override def toString: String = s"Succ($n)"
end Succ

object Zero extends Nat:
  def isZero: Boolean = true

  def predecessor: Nat = throw new NoSuchElementException("non-natural number < 0")

  def successor: Nat = new Succ(this)

  def +(that: Nat): Nat = that

  def -(that: Nat): Nat = if that.isZero then this else throw new NoSuchElementException("non-natural number < 0")

  override def toString: String = "Zero"
end Zero

val one = Succ(Zero)
val two = Succ(Succ(Zero))
one + two
two - one
one - two