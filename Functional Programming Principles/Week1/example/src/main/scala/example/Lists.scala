package example

import scala.annotation.tailrec
import scala.math.abs

object Lists:

  /**
   * This method computes the sum of all elements in the list xs. There are
   * multiple techniques that can be used for implementing this method, and
   * you will learn during the class.
   *
   * For this example assignment you can use the following methods in class
   * `List`:
   *
   *  - `xs.isEmpty: Boolean` returns `true` if the list `xs` is empty
   *  - `xs.head: Int` returns the head element of the list `xs`. If the list
   *    is empty an exception is thrown
   *  - `xs.tail: List[Int]` returns the tail of the list `xs`, i.e. the the
   *    list `xs` without its `head` element
   *
   *  ''Hint:'' instead of writing a `for` or `while` loop, think of a recursive
   *  solution.
   *
   * @param xs A list of natural numbers
   * @return The sum of all elements in `xs`
   */
  def sum(xs: List[Int]): Int = {
    @tailrec
    def sumAll(l: List[Int], accumulator: Int): Int = {
      if (l.tail.isEmpty) l.head + accumulator
      else sumAll(l.tail, l.head + accumulator)
    }

    sumAll(xs,0)
  }

  /**
   * This method returns the largest element in a list of integers. If the
   * list `xs` is empty it throws a `java.util.NoSuchElementException`.
   *
   * You can use the same methods of the class `List` as mentioned above.
   *
   * ''Hint:'' Again, think of a recursive solution instead of using looping
   * constructs. You might need to define an auxiliary method.
   *
   * @param xs A list of natural numbers
   * @return The largest element in `xs`
   * @throws java.util.NoSuchElementException if `xs` is an empty list
   */
  def max(xs: List[Int]): Int = {
    if (xs.isEmpty) throw new NoSuchElementException("List is empty")

    @tailrec
    def findMax(l: List[Int], maxVal: Int): Int = {
      if (l.isEmpty) maxVal
      else if (l.head > maxVal) findMax(l.tail, l.head)
      else findMax(l.tail, maxVal)
    }

    findMax(xs, 0)
  }

val tolerance = 0.0001

def isCloseEnough(x: Double, y: Double) =
  abs((x - y)) < tolerance

def fixedPoint(f: Double => Double)(firstGuess: Double): Double =
  def iterate(guess: Double): Double =
    val next = f(guess)
    if isCloseEnough(guess, next) then next
    else iterate(next)

  iterate(firstGuess)

def averageDamp(f: Double => Double)(x: Double): Double =
  (x + f(x)) / 2

def sqrt(x: Double) = fixedPoint(averageDamp(y => x / y))(1.0)

@main def test =
  println(sqrt(1.0e-50))
  