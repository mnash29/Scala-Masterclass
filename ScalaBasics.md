# Basics

### Define immutable values

```scala
// val name: type = assignment
val x: Int = 42

// type can also be inferred by the compiler
val aString = "Hello world!"
val aBoolean: Boolean = false
val aChar: Char = 'a'
val aShort: Short = 1234
val aLong: Long = 12345678
val aFloat: Float = 2.0f
val aDouble: Double = 3.14156

// vals are immutable
x = 2 // error
```

### Define mutable variables

```scala
var aVariable: Int = 4
aVariable += 1 // no error but has side effects
```

### Define expressions

```scala
// instructions (DO) vs expressions (VALUE)

// if expression similar to python syntax
val aCondition = true
val aConditionedValue = if(aCondition) 5 else 3
println(aConditionedValue) // 5

// NEVER WRITE IMPERATIVE WHILE LOOPS
var i = 0
while (i < 10) {
  println(i)
  i += 1
}

// EVERYTHING in scala is an expression!
val aWeirdValue: Unit = (aVariable = 3) // Unit === void
println(aWeirdValue) // ()

// side effects: println(), while, reassigning

// code blocks
var aCodeBlock = { // the value inferred is String because of the expression
  var y = 2
  var z = y + 1
  if (x > 2) "hello" else "goodbye" // expression
}

z += 1 // error, 'z' is not visible outside of the code blocks scope 
```

### Define parameterized functions very similar to python syntax

```scala
// def name(arg1: type, arg2: type): return type = {}
def main(args: Array[String]): Unit = {
  println("Rock the JVM!")
}

// WHEN LOOPS ARE NEEDED USE RECURSION
// return types of recursive functions are required
// because it uses the return type of the final function
// call to determine the type which is this case is the 
// function itself.
def aRepeatedFunction(aString: String, n: Int): String = {
  if (n == 1) aString
  else aString + aRepeatedFunction(aString, n - 1)
}

// A function with side effects
def aFunctionWithSideEffects(aString: String): Unit = println(aString)

// Defining auxiliary function inside code block
def isPrime(n: Int, i: Int = 2): Boolean = {
  @tailrec
  def isPrimeUntil(t: Int): Boolean = {
    if (t <= 1) true
    else n % t != 0 && isPrimeUntil(t - 1)
  }

  isPrimeUntil(n / 2)
}
println(isPrime(2003)) // true
```

### Define parameterless functions

```scala
def aParameterlessFunction(): Int = 42
println(aParameterlessFunction()) // 42
println(aParameterlessFunction) // Warning, but still outputs 42
```

### Stack and tail recursion

```scala
// Stack recursion
def factorial(n: Int): Int = {
  println(s"Starting factorial calculation: $n")
  if (n <= 1) 1
  else n * factorial(n - 1)
  println(s"Computed factorial of: $n")
}
// Each recursive call adds an item to the JVM stack and 
// can result in a stack overflow error
factorial(5000)

// Tail recursion occurs when the recursive call
// is the last expression and the `@tailrec` annotation
// is required
def factorialTailRecursive(n: Int): Int = {
  @tailrec
  def factHelper(x: int, accumulator: Int): Int =
    if (x <= 1) accumulator
    else factHelper(x - 1, x * accumulator)

  factHelper((n, 1))
}

// If a tail recursive function requires x recursive
// calls, then x accumulator values
// should be used. e.g. fibonacci
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
```

### Call by value, Call by name

```scala
def callByValue(x: Long): Unit = {
  println(s"by value: $x")
  println(s"by value: $x")
}

def callByName(x: => Long): Unit = {
  println(s"by name: $x")
  println(s"by name: $x")
}

// System.nanoTime() is calculated at function call
// and used as is during function execution
callByValue(System.nanoTime())
// System.nanoTime() is passed as a function named
// `x` and evaluated at each `println()` expression
callByName(System.nanoTime())
```

### Default and named variables

```scala
// Default value for `acc`
@tailrec
def trFact(n: Int, acc: Int = 1): Int = {
  if (n <= 1) acc
  else trFact(n - 1, n * acc)
}
trFact(10)

// Calling function with named variables
def savePicture(fmt: String = "jpg", 
                w: Int = 1920, 
                h: Int = 1080): Unit = println("saving picture")

savePicture(fmt="webp", w=3840, h=2160)
```

### Smart operations on Strings

```scala
val str: String = "Hello, I am learning Scala and rocking the JVM!"

// Scala has access to all Java utilities
println(str.charAt(2))
println(str.substring(7, 11)) // substring(inclusive, exclusive)
println(str.split(" ").toList)
println(str.startsWith("Hello"))
println(str.replace("Hello", "Hello World"))
println(str.toLowerCase(), str.toUpperCase())
println(str.length)

// Scala specific utilities
val aNumberString = "45"
val aNumber = aNumberString.toInt
println('3' +: aNumberString :+ "6") // 3456
println(str.reverse)
println(str.take(2)) // He

// S-interpolators
val name = "David"
val age = 12
val greeting = s"Hello, my name is $name, and I am $age years old"
val otherGreeting = s"$greeting and I will be turning ${age + 1} tomorrow."

// F-interpolators
val speed = 1.2233445f
val myth = f"$name%s can eat $speed%2.2f burgers per minute"

// raw-interpolator
println(raw"This is a \n newline") // This is a \n newline
val escaped = "This is a \n newline"
println(raw"$escaped") // This is a
                       //  new line
```