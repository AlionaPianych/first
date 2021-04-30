package karazin.scala.users.group.week2.and.three.quarters.homework

import scala.util.control.NonFatal

import scala.util.{Try,Success,Failure}
/* 
  Custom implementation of Option (Maybe monad in Haskell)
  Implemented via Scala 3 way for Algebraic Data Types (ADT)
  
  Optional task:
  In addition to 
  ```
    ErrorOr.fromTry(
      Try {
        bla-bla-bla
      }
    )
  ```
  Make 
  ```
    Try {
      bla-bla-bla
    }.toErrorOr
  ```
  also available.
  NB: Don't forget about type parameters.

  
  Resources:
  * https://en.wikipedia.org/wiki/Algebraic_data_type
  * https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
*/

object adt:

  enum ErrorOr[+V]:

    case Some(x: V) extends ErrorOr[V]

    case SomeError(x: Throwable) extends ErrorOr[V]

    def flatMap[Q](f: V ⇒ ErrorOr[Q]): ErrorOr[Q] = {
      this match
        case ErrorOr.SomeError(q) ⇒ ErrorOr.SomeError(q)
        case ErrorOr.Some(v) ⇒ ErrorOr.fromTry(Try{f(v)}).flatten
    }

    def map[Q](f: V ⇒ Q): ErrorOr[Q] =
      this match
        case ErrorOr.SomeError(t) ⇒ ErrorOr.SomeError(t)
        case ErrorOr.Some(v) ⇒ Try {(f(v))}.toErrorOr


    def withFilter(p: V => Boolean): ErrorOr[V] = ???

    /*
      The method is used for getting rid of internal box
      Provide a type parameter, an argument and a result type
    */
    def flatten[U](implicit ev: V <:< ErrorOr[U]): ErrorOr[U] =
      this match
        case ErrorOr.Some(v) ⇒ ev(v)
        case ErrorOr.SomeError(e) ⇒ ErrorOr.SomeError(e)


    /*
      The method is used for applying side effects without returning any result
      Provide a type parameter, an argument and a result type
    */
    def foreach[U](f: V => U): Unit =
      this match
        case ErrorOr.Some(v) ⇒ f(v)
        case ErrorOr.SomeError(e) ⇒ ErrorOr.SomeError(e)


  object ErrorOr:
    
    /*
      Provide a type parameter, an argument and a result type
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
    */
    def apply[V](v: V): ErrorOr[V] =
      if v == null then ErrorOr.SomeError(throw new Exception("Some exeption...")) else ErrorOr.Some(v)

    /*
      The method is used for creating ErroOr instance from Try
      Provide a type parameter, an argument and a result type
    */
    def fromTry[T](t: Try[T]): ErrorOr[T] =
      t match {
        case Success(r) => ErrorOr.Some(r)
        case Failure(r) ⇒ ErrorOr.SomeError(r)
      }



  extension [T](t: Try[T]) def toErrorOr: ErrorOr[T] = ErrorOr.fromTry(t)