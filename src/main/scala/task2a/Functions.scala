package task2a

object Functions:
  val positiveVal: Int => String =
    case x if x >= 0 => "positive"
    case _           => "negative"

  def positiveDef(x: Int): String = x match
    case x if x >= 0 => "positive"
    case _           => "negative"

  val negVal: (String => Boolean) => String => Boolean = p => !p(_)

  def negDef(p: String => Boolean): String => Boolean = !p(_)

  def neg[A](p: A => Boolean): A => Boolean = !p(_)
