package lectures.oop

/*
  head => first element of list
  tail => remainder of the list
  isEmpty => Boolean
  add(int) => new list with element added
  toString => String repr
 */
sealed abstract class Collection {
  def head: Int

  def tail: Collection

  def isEmpty: Boolean

  def add(n: Int): Collection
}

object EmptyCollection extends Collection {
  override def head: Int = throw new NoSuchElementException

  override def tail: Collection = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(n: Int): Collection = new CollectionList(n, EmptyCollection)

  override def toString: String = ""
}

class CollectionList(h: Int, t: Collection) extends Collection {
  override def head: Int = h

  override def tail: Collection = t

  override def isEmpty: Boolean = false

  override def add(n: Int): Collection = new CollectionList(n, this)

  override def toString: String = s"$h ${t.toString}"
}

object AbstractDataTypes extends App {

  var list = new CollectionList(1, EmptyCollection)
  println(list.tail.toString)
}

