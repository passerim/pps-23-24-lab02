package task4

import org.junit.Assert.assertEquals
import org.junit.Test
import task4.Shapes.Shape

class ShapeTest:
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
