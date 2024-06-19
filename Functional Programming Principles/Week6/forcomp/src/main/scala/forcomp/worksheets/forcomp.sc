// Week 5 Exercises - List operations
val l1 = List(1,2,3,4)
val l2 = List(5,6,7,8)
val l1CatL2 = l2 ++ l1
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

extension [T](xs: List[T])
  def splitAt(n: Int) = (xs.take(n), xs.drop(n))

// Generic merge sort with comparator function `f`
def mergeSort[T](xs: List[T])(f: (T, T) => Boolean): List[T] =
  val n = xs.length / 2
  if (n == 0) then xs
  else
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xt, y :: yt) =>
        if f(x, y) then x :: merge(xt, ys)
        else y :: merge(xs, yt)
    val (left, right) = xs.splitAt(n)
    merge(mergeSort(left)(f), mergeSort(right)(f))

mergeSort(l1CatL2)((x, y) => x < y)

def scaleList(xs: List[Double], factor: Double): List[Double] = xs match
  case Nil => xs
  case y :: ys => y * factor :: scaleList(ys, factor)

extension[T](xs: List[T])
  def map[U](f: T => U): List[U] = xs match
    case Nil => Nil
    case y :: ys => f(y) :: ys.map(f)

def scaleListExt(xs: List[Int], factor: Double): List[Double] =
  xs.map(x => x * factor)

scaleListExt(l2, 2.0)

def squareList(l: List[Int]): List[Int] =
  l.map(x => x * x)

squareList(l2)