package karazin.scala.users.group.week1.homework

/* 
  Custom implementation of Option (Maybe monad in Haskell)
  Implemented via Scala 3 way for Algebraic Data Types (ADT)
  
  Resources:
  * https://en.wikipedia.org/wiki/Algebraic_data_type
  * https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
*/
import scala.util.control.NonFatal

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



  object ErrorOr:
    
    def apply[V](v: V): ErrorOr[V] =
      if v == null then ErrorOr.SomeError(throw new Exception("Some exeption...")) else ErrorOr.Some(v) 


      
  