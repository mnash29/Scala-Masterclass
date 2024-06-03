// Week 5 Exercises - List operations
val l1 = List(1,2,3,4)
val l2 = List(5,6,7,8)
val l1CatL2 = l1 ++ l2
val sameL1CatL2 = l1 ::: l2

def removeAt[T](n: Int, xs: List[T]): List[T] = xs match
  case Nil => Nil
  case ::(head, next) =>
    if n == 0 then next else head :: removeAt(n - 1, next)

removeAt(2, l1CatL2)

def flatten(xs: Any): List[Any] = xs match
  case head :: tail => flatten(head) ::: flatten(tail)
  case Nil => Nil
  case _ => xs :: Nil

val l3 = flatten(List(List(1,1), 2, List(3, List(5,8))))
