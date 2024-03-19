package task2b

import org.junit.Assert.assertEquals
import org.junit.Test
import task2b.Composition.{compose, genericCompose}

class CompositionTest:
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
