package task3

import scala.annotation.tailrec

object Recursion:
  @tailrec
  def gcd(a: Int, b: Int): Int = (a, b) match
    case (a, b) if b > 0 => gcd(b, a % b)
    case _               => a
