package karazin.scala.users.group.week1.homework

import scala.util.control.NonFatal

object adt:

  enum ErrorOr[+V]:

    case Some(x: V) extends ErrorOr[V]// Если любой тип кроме Throwable 

    case SomeError(x: Throwable) extends ErrorOr[V]// если  какае-то ошибка 

    def flatMap[Q](f: V ⇒ ErrorOr[Q]): ErrorOr[Q] = {
      this match
        case ErrorOr.SomeError(q) ⇒ ErrorOr.SomeError(q)//если ошибка сразу то ее и возвращаем 
        case ErrorOr.Some(v) ⇒ try {
          f(v)
        } catch {
          case NonFatal(e) ⇒ ErrorOr.SomeError(e)
        }
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
      if v == null then ErrorOr.SomeError(throw new Exception(s"${v}")) else ErrorOr.Some(v) 