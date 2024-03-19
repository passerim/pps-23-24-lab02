package task2b

import org.junit.Assert.{assertFalse, assertTrue}
import org.junit.Test
import task2b.Currying.*

class CurryingTest:
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
