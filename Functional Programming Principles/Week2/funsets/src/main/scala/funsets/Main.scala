package funsets

object Main extends App:
  import FunSets.*
  println(contains(singletonSet(1), 2))
  val s1 = singletonSet(1)
  val s2 = singletonSet(2)
  val us = union(s1, s2)
  val ds = diff(s1, s2)
  printSet(ds)

  val ms = map(us, (x => x * 2))
  printSet(ms)