package task4

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
