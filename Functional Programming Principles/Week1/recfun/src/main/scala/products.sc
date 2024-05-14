def product(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 1 else f(a) * product(f)(a + 1, b)

product(x => x * x)(1, 5)

def factorial(a: Int) = product(x => x)(1, a)

factorial(4)

def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
  def recur(a: Int): Int =
    if (a > b) then zero
    else combine(f(a), recur(a + 1))
  recur(a)

def sum(f: Int => Int) = mapReduce(f, (x, y) => x + y, 0)
sum(factorial)(1, 5)

def prod(f: Int => Int) = mapReduce(f, (x, y) => x * y, 1)
prod(identity)(1, 6)

def f(a: String)(b: Int)(c: Boolean): String =
  "(" + a + ", " + b + ", " + c + ")"

var partialApplication1 = f("Scala")

var partialApplication2 = partialApplication1(42)
partialApplication2(true)
