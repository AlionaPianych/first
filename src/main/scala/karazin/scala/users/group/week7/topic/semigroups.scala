package karazin.scala.users.group.week7.topic

object semigroups:
  
  trait Semigroup[T]:
    extension (left: T) def combine(right: T): T

  given Semigroup[Int] with
    extension (left: Int) def combine(right: Int): Int = left + right

  given Semigroup[String] with
    extension (left: String) def combine(right: String): String = left concat right
// TODO: Add summon for all internal elements!
  given [T]: Semigroup[List[T]] with
    extension (left: List[T]) def combine(right: List[T]): List[T] = left ::: right

  object Semigroup:
    def apply[T](using monoid: Semigroup[T]) = monoid

