class Rational(x: Int, y: Int):
  require(y > 0, "Denominator must be positive.")

  def numer = x / gcd
  def denom = y / gcd

  def +(r: Rational) =
    Rational(numer * r.denom + r.numer * denom, r.denom * denom)

  def -(r: Rational) =
    this + r.neg

  def *(r: Rational) =
    Rational(numer * r.numer, denom * r.denom)

  def neg =
    Rational(-numer, denom)

  private def gcd: Int =
    def reduce(a: Int, b: Int): Int =
      if b == 0 then a else reduce(b, a % b)

    reduce(this.x.abs, this.y)

  override def toString: String = s"${this.numer}/${this.denom}"

val x = Rational(1, 2)
val y = Rational(5, 8)
val z = Rational(3, 2)

println(x - y - z)
