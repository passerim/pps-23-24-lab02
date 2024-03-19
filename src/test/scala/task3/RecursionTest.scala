package task3

import org.junit.Assert.assertEquals
import org.junit.Test
import task3.Recursion.gcd

class RecursionTest:
  @Test def testGCD(): Unit =
    assertEquals(4, gcd(12, 8))

  @Test def testAgainGCD(): Unit =
    assertEquals(7, gcd(14, 7))

  @Test def testGCDLowerFirst(): Unit =
    assertEquals(4, gcd(8, 12))
