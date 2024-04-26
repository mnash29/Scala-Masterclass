package playground

import scala.annotation.tailrec

object Playground {

  def main(args: Array[String]): Unit = {
    /*
    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
    2. Factorial function (n) => n * factorial(n-1)
    3. Fibonacci function (n) => f(n - 1) + f(n - 2)
    4. Prime numbers
     */

    def greeting(name: String, age: Int): Unit = println(s"Hello, my name is $name and I am $age years old.")
    greeting("Matthew", 38)

    def factorial(n: Int): BigInt = {
      if (n <= 0) 1
      else n * factorial(n - 1)
    }
    println(factorial(4))

    def fibonacci(n: Int): Int = {
      @tailrec
      def accumulate(x: Int, last: Int, next: Int): Int = {
        if (x >= n) last
        else accumulate(x + 1, last + next, last)
      }

      if (n <= 2) 1
      else accumulate(2, 1, 1)
    }
    println(fibonacci(8))

    def isPrime(n: Int): Boolean = {
      @tailrec
      def isPrimeUntil(t: Int): Boolean = {
        if (t <= 1) true
        else n % t != 0 && isPrimeUntil(t - 1)
      }

      isPrimeUntil(n / 2)
    }
    println(isPrime(2003))

    // S-interpolators
    val name = "David"
    val age = 12
    val greet = s"Hello, my name is $name, and I am $age years old"
    val otherGreeting = s"$greet and I will be turning ${age + 1} tomorrow."
    println(otherGreeting)

    // F-interpolators
    val speed = 1.2233445f
    val myth = f"$name%s can eat $speed%.2f burgers per minute"
    println(myth)
  }
}
