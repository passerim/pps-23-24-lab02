import Main.*
import Main.Optionals.*
import Main.Shapes.*
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.Test

import scala.annotation.tailrec

object Main:
  // Task 1, svolto da solo
  println("Hello, Scala")

  // Task 2a, svolto da solo
  val positiveVal: Int => String =
    case x if x >= 0 => "positive"
    case _           => "negative"

  def positiveDef(x: Int): String = x match
    case x if x >= 0 => "positive"
    case _           => "negative"

  val negVal: (String => Boolean) => String => Boolean = p => !p(_)

  def negDef(p: String => Boolean): String => Boolean = !p(_)

  def neg[A](p: A => Boolean): A => Boolean = !p(_)

  // task 2b punto 4, svolto da solo
  val p1: Int => Int => Int => Boolean = x => y => z => x <= y && y == z

  val p2: (Int, Int, Int) => Boolean = (x, y, z) => x <= y && y == z

  def p3(x: Int)(y: Int)(z: Int): Boolean = x <= y && y == z

  def p4(x: Int, y: Int, z: Int): Boolean = x <= y && y == z

  // task 2b punto 5, svolto da solo
  def compose(f: Int => Int, g: Int => Int): Int => Int = value => f(g(value))

  def genericCompose[A, B, C](f: B => C, g: A => B): A => C = value => f(g(value))

  // Task 3, svolto da solo
  @tailrec
  def gcd(a: Int, b: Int): Int = (a, b) match
    case (a, b) if b > 0 => gcd(b, a % b)
    case _               => a

  // Task 4, svolto da solo
  object Shapes:
    enum Shape:
      case Rectangle(width: Double, height: Double)
      case Circle(radius: Double)
      case Square(side: Double)

    object Shape:
      def perimeter(shape: Shape): Double = shape match
        case Rectangle(width, height) => 2 * (width + height)
        case Circle(radius)           => 2 * radius * Math.PI
        case Square(side)             => 4 * side

      def scale(shape: Shape, alpha: Double): Shape = shape match
        case Rectangle(width, height) => Rectangle(alpha * width, alpha * height)
        case Circle(radius)           => Circle(alpha * radius)
        case Square(side)             => Square(alpha * side)

  // Task 5, svolto da solo
  object Optionals:
    enum Optional[A]:
      case Maybe(value: A)
      case Empty()

    object Optional:
      def isEmpty[A](optional: Optional[A]): Boolean = optional match
        case Empty() => true
        case _       => false

      def orElse[A, B >: A](optional: Optional[A], default: B): B = optional match
        case Maybe(value) => value
        case Empty()      => default

      def map[A, B](optional: Optional[A], f: A => B): Optional[B] = optional match
        case Maybe(value) => Maybe(f(value))
        case Empty()      => Empty()

      def filter[A](optional: Optional[A], f: A => Boolean): Optional[A] = optional match
        case Maybe(value) if f(value) => Maybe(value)
        case _                        => Empty()

class MainTest:
  // Task 2a
  def testPositive(f: Int => String): Unit =
    val value = 5
    assertEquals("positive", f(value))
    assertEquals("positive", f(0))
    assertEquals("negative", f(-value))

  @Test def testPositiveVal(): Unit =
    testPositive(positiveVal)

  @Test def testPositiveDef(): Unit =
    testPositive(positiveDef)

  def testNeg(f: (String => Boolean) => String => Boolean): Unit =
    val empty: String => Boolean = _ == ""
    val notEmpty                 = f(empty)
    assertTrue(notEmpty("foo"))
    assertFalse(notEmpty(""))
    assertTrue(notEmpty("foo") && !notEmpty(""))

  @Test def testNegVal(): Unit =
    testNeg(negVal)

  @Test def testNegDef(): Unit =
    testNeg(negDef)

  @Test def testGenericNegString(): Unit =
    testNeg(neg)

  @Test def testGenericNegInt(): Unit =
    val divByTwo: Int => Boolean = _ % 2 == 0
    val notDivByTwo              = neg(divByTwo)
    assertTrue(notDivByTwo(1))
    assertFalse(notDivByTwo(4))
    assertTrue(notDivByTwo(3) && !notDivByTwo(6))

  // Task 2b punto 4
  @Test def testP1(): Unit =
    val x = 1
    val y = 2
    val z = y
    assertTrue(p1(x)(y)(z))
    assertFalse(p1(x)(y)(z + 1))

  @Test def testP2(): Unit =
    val x = 1
    val y = 2
    val z = y
    assertTrue(p2(x, y, z))
    assertFalse(p2(x, y, z + 1))

  @Test def testP3(): Unit =
    val x = 1
    val y = 2
    val z = y
    assertTrue(p3(x)(y)(z))
    assertFalse(p3(x)(y)(z + 1))

  @Test def testP4(): Unit =
    val x = 1
    val y = 2
    val z = y
    assertTrue(p4(x, y, z))
    assertFalse(p4(x, y, z + 1))

  // Task 2b punto 5
  @Test def testCompose(): Unit =
    val value       = 5
    val composition = compose(_ - 1, _ * 2)(value)
    assertEquals(value * 2 - 1, composition)

  @Test def testGenericCompose(): Unit =
    val value            = 5
    val f: Int => String = _ + "."
    val g: Int => Int    = _ * 2
    val composition      = genericCompose(f, g)(value)
    assertEquals(s"${value * 2}.", composition)

  // Task 3
  @Test def testGCD(): Unit =
    assertEquals(4, gcd(12, 8))

  @Test def testAgainGCD(): Unit =
    assertEquals(7, gcd(14, 7))

  @Test def testGCDLowerFirst(): Unit =
    assertEquals(4, gcd(8, 12))

  // Task 4
  @Test def testRectanglePerimeter(): Unit =
    val shape          = Shape.Rectangle(1.0, 2.0)
    val shapePerimeter = 6.0
    val delta          = 0.0
    assertEquals(shapePerimeter, Shape.perimeter(shape), delta)

  @Test def testCirclePerimeter(): Unit =
    val shape          = Shape.Circle(1.0)
    val shapePerimeter = 2 * Math.PI
    val delta          = 0.0
    assertEquals(shapePerimeter, Shape.perimeter(shape), delta)

  @Test def testSquarePerimeter(): Unit =
    val shape          = Shape.Square(1.0)
    val shapePerimeter = 4.0
    val delta          = 0.0
    assertEquals(shapePerimeter, Shape.perimeter(shape), delta)

  @Test def testRectangleScaling(): Unit =
    val alpha         = 2.0
    val originalShape = Shape.Rectangle(1.0, 2.0)
    val scaledShape   = Shape.Rectangle(2.0, 4.0)
    assertEquals(scaledShape, Shape.scale(originalShape, alpha))

  @Test def testCircleScaling(): Unit =
    val alpha         = 2.0
    val originalShape = Shape.Circle(1.0)
    val scaledShape   = Shape.Circle(2.0)
    assertEquals(scaledShape, Shape.scale(originalShape, alpha))

  @Test def testSquareScaling(): Unit =
    val alpha         = 2.0
    val originalShape = Shape.Square(1.0)
    val scaledShape   = Shape.Square(2.0)
    assertEquals(scaledShape, Shape.scale(originalShape, alpha))

  // Task 5
  @Test def mapShouldReturnEmptyWhenEmpty(): Unit =
    val empty: Optional[Int] = Optional.Empty()
    val result               = Optional.map(empty, _ + 1)
    assertTrue(Optional.isEmpty(result))

  @Test def mapShouldReturnTransformedValueWhenNonEmpty(): Unit =
    val nonEmpty = Optional.Maybe(0)
    val result   = Optional.map(nonEmpty, _ + 1)
    assertEquals(1, Optional.orElse(result, 1))

  @Test def filterShouldReturnEmptyWhenEmpty(): Unit =
    val empty: Optional[Int] = Optional.Empty()
    val result               = Optional.filter(empty, _ > 2)
    assertTrue(Optional.isEmpty(result))

  @Test def mapShouldReturnValueWhenPredicateIsTrue(): Unit =
    val nonEmpty = Optional.Maybe(5)
    val result   = Optional.filter(nonEmpty, _ > 2)
    assertEquals(Optional.orElse(nonEmpty, 0), Optional.orElse(result, 1))

  @Test def mapShouldReturnEmptyWhenPredicateIsFalse(): Unit =
    val nonEmpty = Optional.Maybe(5)
    val result   = Optional.filter(nonEmpty, _ > 8)
    assertTrue(Optional.isEmpty(result))
