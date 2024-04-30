package lectures.oop

import scala.language.postfixOps

object OOBasics extends App {

  private val bernard = new Writer("Bernard", "Langer", 1957, 57)
  println((bernard + "The Bear").fullName())

  private var tolkien = new Writer("J. R. R.", "Tolkien", 1884, 98)
  private val fellowship = new Novel("Fellowship of the Ring", 1938, tolkien)
  println(s"Tolkien age: ${fellowship.authorAge()}")

  tolkien = +tolkien
  println(s"Tolkien age next year: ${tolkien.age}")
  tolkien isWritingScala

  bernard(15)

  println(fellowship.isWrittenBy(bernard))
  private val fellowshipV2 = fellowship.copy(2024)
  println(fellowship, fellowshipV2)

//  val counter = new ImmutableCounter()
//  counter.increment.increment(2).current()
//  counter.increment(3).decrement(1).decrement.current()
}

  /*
    1. overload the + operator concatenates two strings => new person
    2. add an age to Person class with default 0
       add unary + operator increments age => new person
    3. add a learns method in Person class => mary learns scala
       add a learnsScala method, calls learns method with "Scala"
       Use in postfix notation
    4. overload apply method to receives an Int => "Mary watched Inception <Int> times"
  */
class Writer(firstName: String, surname: String, val year: Int, var age: Int) {
    def +(nickname: String) = new Writer(s"${this.firstName} ($nickname)", this.surname, this.year, this.age)
    def unary_+ : Writer = new Writer(this.firstName, this.surname, this.year, this.age + 1)
    def fullName(): String = s"$firstName $surname"
    def isWritingScala: Unit = writing("Fundamentals of Scala")
    def writing(title: String): Unit = println(s"${this.fullName()} is writing a book titled $title")
    def apply(count: Int) = println(s"${this.fullName()} has written $count books.")
}

class Novel(name: String, release: Int, author: Writer) {
    def authorAge(): Int = this.author.age

    def isWrittenBy(author: Writer): Boolean = this.author.fullName() == author.fullName()

    def copy(year: Int): Novel = new Novel(this.name, year, this.author)
}

class ImmutableCounter(tick: Int = 0) {

    def current(): Unit = println(tick)

  def increment: ImmutableCounter = {
    println("Incrementing") // Some logging function
    new ImmutableCounter(tick + 1)
  }

  def decrement: ImmutableCounter = {
    println("Decrementing")
    new ImmutableCounter(tick - 1)
  }

  def increment(amount: Int): ImmutableCounter = {
    if (amount <= 0) this
    else increment.increment(amount - 1)
  }

  def decrement(amount: Int): ImmutableCounter = {
    if (amount <= 0) this
    else decrement.decrement(amount - 1)
  }

}