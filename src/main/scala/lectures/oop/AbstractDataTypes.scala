package lectures.oop

object AbstractDataTypes extends App {
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

  class MyList extends Collection {
    var list: Array[Int] = Array()
    override def head: Int = list.head

    override def tail: Collection = {
      var nextList = new MyList
      nextList.list = this.list.tail
      nextList
    }

    override def isEmpty: Boolean = list.isEmpty

    override def add(n: Int): Collection = {
      var newList = new MyList
      newList.list = Array.concat(this.list, Array(n))
      newList
    }

    override def toString: String = list.mkString("MyList(", ", ", ")")
  }

  var first = new MyList
  println(first.isEmpty)
  println(first.add(1).add(2).add(3).isEmpty)
  var tailed = first.add(1).add(2).add(3).tail
  println(tailed.add(1).toString)
}

