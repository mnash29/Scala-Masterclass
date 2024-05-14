package lectures.functional

object FunctionTypes extends App {
 /*
     1.  a function which takes 2 strings and concatenates them
     2.  transform the MyPredicate and MyTransformer into function types
     3.  define a function which takes an int and returns another function which takes an int and returns an int
         - what's the type of this function
         - how to do it
  */

  // Function1 as single abstract method
  val concatenate: (String, String) => String = (v1: String, v2: String) => v1 + v2

  println(concatenate("Hello ", "World"))

  val adder: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Int => Int = new Function[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }

  val add = adder(3)
  println(add(4))
  println(adder(3)(4))

  val predicate: Int => Boolean = new Function1[Int, Boolean] {
    override def apply(v1: Int): Boolean = v1 % 2 == 0
  }

  val tranformer: Int => Int = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }
}
