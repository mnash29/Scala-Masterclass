package lectures.oop

/*
  Exercise 1:
    1. Generic version of Collection list

  Exercise 2:
    1.  Generic trait MyPredicate[-T] with a little method test(T) => Boolean
    2.  Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3.  MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(transformer from A to MyList[B]) => MyList[B]

        class EvenPredicate extends MyPredicate[Int]
        class StringToIntTransformer extends MyTransformer[String, Int]

        [1,2,3].map(n * 2) = [2,4,6]
        [1,2,3,4].filter(n % 2) = [2,4]
        [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
 */
sealed abstract class GenericCollection[+A] {
  def head: A

  def tail: GenericCollection[A]

  def isEmpty: Boolean

  def add[B >: A](n: B): GenericCollection[B]
  def printElements: String
  override def toString: String = s"Collection($printElements)"

  def map[B](transformer: A => B): GenericCollection[B]
  def filter(predicate: A => Boolean): GenericCollection[A]
  def flatMap[B](transformer: A => GenericCollection[B]): GenericCollection[B]

  def ++[B >: A](list: GenericCollection[B]): GenericCollection[B]
}

object EmptyGenericList extends GenericCollection[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: GenericCollection[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](n: B): GenericCollection[B] = new GenericCollectionList(n, this)

  override def printElements: String = ""

  override def map[B](transformer: Nothing => B): GenericCollection[B] = EmptyGenericList

  override def filter(predicate: Nothing => Boolean): GenericCollection[Nothing] = EmptyGenericList

  override def flatMap[B](transformer: Nothing => GenericCollection[B]): GenericCollection[B] = EmptyGenericList

  override def ++[B >: Nothing](list: GenericCollection[B]): GenericCollection[B] = list
}

class GenericCollectionList[+A](h: A, t: GenericCollection[A]) extends GenericCollection[A] {
  override def head: A = h

  override def tail: GenericCollection[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](n: B): GenericCollection[B] = new GenericCollectionList(n, this)

  override def printElements: String = {
    if (t.isEmpty) s"$h"
    else s"$h, ${t.printElements}"
  }

  override def map[B](transformer: A => B): GenericCollection[B] = {
    new GenericCollectionList[B](transformer(h), t.map(transformer))
  }

  override def filter(predicate: A => Boolean): GenericCollection[A] = {
    if (predicate(h)) new GenericCollectionList(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /*
  [1, 2].flatMap(n => [n, n+1])
  = [1, 2] ++ [2].flatMap(n => [n, n+1])
  = [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n+1])
  = [1, 2] ++ [2, 3] ++ Empty
  = [1, 2, 2, 3]
   */
  override def flatMap[B](transformer: A => GenericCollection[B]): GenericCollection[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  override def ++[B >: A](list: GenericCollection[B]): GenericCollection[B] = new GenericCollectionList(h, t ++ list)
}

/*
  Removed and replaced with Function1 (A, B) => R abstract methods
 */
//trait Predicate[-T] {
//  def test(element: T): Boolean
//}
//
//trait Transformer[-A, B] {
//  def transform(element: A): B
//}

object Generics extends App {

  var list = new GenericCollectionList(1, EmptyGenericList)
  println(list.add('a').add('b').add(2).toString)

  var doubleIntegers = new GenericCollectionList(1, new GenericCollectionList(2, new GenericCollectionList(3, EmptyGenericList)))
  println(doubleIntegers.map((element: Int) => element * 2))

  println(doubleIntegers.filter((element: Int) => element % 2 == 0))

  println(list ++ doubleIntegers)

  println(doubleIntegers.flatMap((element: Int) => new GenericCollectionList(element, new GenericCollectionList(element + 1, EmptyGenericList))))
}

