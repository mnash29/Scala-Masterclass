# Functional Programming in Scala

### What is a Function

`Function` is a group of  traits where the number (Function1) corresponds to the number of 
inputs to the Function and can be written as `Function2[A, B, R] === (A,B) => R` where `A`
and `B` are the inputs and `R` is the returned value.

> NOTE: All Scala Functions are Objects

```scala
val double: Int => Int = new Function1[Int, Int] {
  override def apply(element: Int): Int = element * 2
}

val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
  override def apply(v1: Int, v2: Int): Int = v1 + v2
}

println(double(2)) // 4
```