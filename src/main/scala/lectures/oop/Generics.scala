package lectures.oop

/*
  head => first element of list
  tail => remainder of the list
  isEmpty => Boolean
  add(int) => new list with element added
  toString => String repr
 */
sealed abstract class GenericCollection[+A] {
  def head: A

  def tail: GenericCollection[A]

  def isEmpty: Boolean

  def add[B >: A](n: B): GenericCollection[B]
}

object EmptyGenericList extends GenericCollection[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: GenericCollection[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](n: B): GenericCollection[B] = new GenericCollectionList(n, this)

  override def toString: String = ""
}

class GenericCollectionList[+A](h: A, t: GenericCollection[A]) extends GenericCollection[A] {
  override def head: A = h

  override def tail: GenericCollection[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](n: B): GenericCollection[B] = new GenericCollectionList(n, this)

  override def toString: String = s"$h ${t.toString}"
}

object Generics extends App {

  var list = new GenericCollectionList(1, EmptyGenericList)
  println(list.add('a').add('b').add(2).toString)

  var newList = new GenericCollectionList(1, new GenericCollectionList('a', new GenericCollectionList('b', new GenericCollectionList(2, EmptyGenericList))))
  println(newList.toString)
}

