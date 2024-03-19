package task2b

object Composition:
  def compose(f: Int => Int, g: Int => Int): Int => Int = value => f(g(value))

  def genericCompose[A, B, C](f: B => C, g: A => B): A => C = value => f(g(value))
