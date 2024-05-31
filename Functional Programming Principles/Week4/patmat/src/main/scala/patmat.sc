//trait Expr
//case class Number(n: Int) extends Expr
//case class Sum(e1: Expr, e2: Expr) extends Expr
//case class Var(x: String) extends Expr
//case class Prod(e1: Expr, e2: Expr) extends Expr

// Alternative to the above case classes
enum Expr:
  case Number(n: Int)
  case Sum(e1: Expr, e2: Expr)
  case Var(x: String)
  case Prod(e1: Expr, e2: Expr)

import Expr._

def eval(e: Expr): Int = e match
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
  case _ => 0

val expr = Sum(Number(1), Number(1))
eval(expr)

object PatMat:
  def show(e: Expr): String = e match
    case Number(n) => n.toString
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Var(x) => x
    case Prod(e1, e2) => s"${showP(e1)} * ${showP(e2)}"

  def showP(e: Expr): String = e match
    case e: Sum => s"(${show(e)})"
    case _ => show(e)

PatMat.show(expr)
PatMat.show(Prod(expr, Var("x")))

enum Direction(val dx: Int, val dy: Int):
  case Right extends Direction(1, 0)
  case Up extends Direction(0, 1)
  case Left extends Direction(-1, 0)
  case Down extends Direction(0, -1)

  def leftTurn = Direction.values((ordinal + 1) % 4)
  def rightTurn = Direction.values(
    if ordinal != 0 then (ordinal - 1) % 4 else Direction.values.length - 1
  )
end Direction

val r = Direction.Right
val u = r.leftTurn
val w = r.rightTurn
