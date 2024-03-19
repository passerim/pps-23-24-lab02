package task2a

import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.Test
import task2a.Functions.*

class FunctionsTest:
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
