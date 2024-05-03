package lectures.oop

object Exceptions extends App {
/*
  1. Crash program with OutOfMemoryError
  2. Crash with StackOverflowError
  3. PocketCalculator
    - add(x, y)
    - subtract(x, y)
    - multiple(x, y)
    - divide(x, y)

    Throw custom exception for each
      - OverflowException if add(x, y) exceeds Int.MAX_VALUE
      - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
      - MatchCalculationException for division by zero
 */
  val calculator = new PocketCalculator
  val sum = calculator.add(2, 1)
  println(sum)

  val quotient = calculator.divide(2, 0)
}

class PocketCalculator {
  def add(x: Int, y: Int): Int = {
    val sum = x + y
    if (x > 0 && y > 0 && sum < 0) throw new OverflowException
    else if (x < 0 && y < 0 && sum > 0) throw new UnderflowException
    else sum
  }

  def divide(x: Int, y: Int): Int = {
    if (y <= 0) throw new MathCalculationException
    else x / y
  }
}

class OverflowException extends Exception
class UnderflowException extends Exception
class MathCalculationException extends Exception
