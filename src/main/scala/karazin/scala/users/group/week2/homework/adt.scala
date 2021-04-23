package karazin.scala.users.group.week2.homework

import scala.util.{Failure, Success}
import scala.util.control.NonFatal

/* 
  Custom implementation of Option (Maybe monad in Haskell)
  Implemented via Scala 3 way for Algebraic Data Types (ADT)
  
  Resources:
  * https://en.wikipedia.org/wiki/Algebraic_data_type
  * https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
*/

object adt:
  
  enum ErrorOr[+V]:

    case Some(x: V) extends ErrorOr[V]
  
    case SomeError(x: Throwable) extends ErrorOr[V]
  
    def flatMap[Q](f: V ⇒ ErrorOr[Q]): ErrorOr[Q] = 
      this match
        case ErrorOr.SomeError(q) ⇒ ErrorOr.SomeError(q)
        case ErrorOr.Some(v) ⇒ try {
          f(v)
        } catch {
          case NonFatal(e) ⇒ ErrorOr.SomeError(e)
        }
    
  
    def map[Q](f: V ⇒ Q): ErrorOr[Q] =
      this match
        case ErrorOr.SomeError(t) ⇒ ErrorOr.SomeError(t)
        case ErrorOr.Some(v) ⇒ try {
          ErrorOr.Some(f(v))
        } catch {
          case NonFatal(e) ⇒ ErrorOr.SomeError(e)
        }
  
  
    def withFilter(p: V => Boolean): ErrorOr[V] = ???
  
    /* 
      The method is used for getting rid of internal box
      Provide a type parameter, an argument and a result type
    */
    def flatten[U](implicit ev: V <:< ErrorOr[U]): ErrorOr[U] =
      this match
        case ErrorOr.Some(v) ⇒ try {
          ev(v)
        } catch {
          case e: Exception ⇒ ErrorOr.SomeError(e)
        }
        case ErrorOr.SomeError(e) ⇒ ErrorOr.SomeError(e)
    def foreach[U](f: V => U): Unit =
      this match
        case ErrorOr.Some(v) ⇒ try {
          ErrorOr.Some(f(v))
        } catch {
        case e: Exception ⇒ ErrorOr.SomeError(e)
        }
        case ErrorOr.SomeError(e) ⇒ throw e
  
  
    // Companion object to define constructor
  object ErrorOr:
      /* 
        Provide a type parameter, an argument and a result type
        
        Make sure that in case of failing the method with exception
        no exception is thrown but the case for an error is returned
      */ 
    def apply[V](v: V): ErrorOr[V] = 
      if v == null then ErrorOr.SomeError(throw new Exception(s"${v}")) else ErrorOr.Some(v)
  