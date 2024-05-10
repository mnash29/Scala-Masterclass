package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
  for row <- 0 to 10 do
    for col <- 0 to row do
    print(s"${pascal(col, row)} ")
  println()

  /**
   * Exercise 1
   */
  def pascal(col: Int, row: Int): Int =
    if (col > row || col < 0) throw new NoSuchElementException()
    else if (col == row || col == 0) 1
    else pascal(col - 1, row - 1) + pascal(col, row - 1)

  /**
   * Exercise 2
   */
  private var numOpenParen = 0

  @tailrec
  def balance(chars: List[Char]): Boolean =
    if (chars.isEmpty)
      numOpenParen == 0
    else
  val _: Unit =
    if (chars.head == '(') numOpenParen += 1
    else if (chars.head == ')') numOpenParen -= 1
  if (numOpenParen >= 0) balance(chars.tail)
  else false



  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (money == 0) 1
    else if (coins.nonEmpty && money > 0)
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    else 0

