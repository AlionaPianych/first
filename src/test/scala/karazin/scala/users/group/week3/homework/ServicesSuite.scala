package karazin.scala.users.group.week3.homework

object Utils {
  def checkList[V](list: List[V])(f: V => Unit) =
    list.foreach(f)
}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.UUID
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.runtime.Nothing$
import munit.Clue.generate

import scala.util.{Failure, Success}

/*
  Write test for all service in karazin.scala.users.group.week3.homework.services
  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */
import model._
import services._
import Utils._

class ServicesSuite extends munit.FunSuite:

  test("getPosts()") {

    val userId = UUID.randomUUID()

    getPosts(userId) onComplete{
      case Failure(exception) => fail("getPosts() failed with error")
      case Success(posts)     => checkList[Post](posts){ post =>
        assertEquals(post.userId, userId)
      }
    }
  }

  test("getComments()") {

    val postId = UUID.randomUUID()

    getComments(postId) onComplete{
      case Failure(exception)    => fail("getComments() failed with error")
      case Success(comments)     => checkList[Comment](comments){ comment =>
        assertEquals(comment.postId, postId)
      }
    }
  }

  test("getLikes()") {

    val postId = UUID.randomUUID()

    getLikes(postId) onComplete{
      case Failure(exception) => fail("getLikes() failed with error")
      case Success(likes)     => checkList[Like](likes){ like =>
        assertEquals(like.postId, postId)
      }
    }
  }

  test("getShares()") {

    val postId = UUID.randomUUID()

    getShares(postId) onComplete{
      case Failure(exception)  => fail("getShares() failed with error")
      case Success(shares)     => checkList[Share](shares){ share =>
        assertEquals(share.postId, postId)
      }
    }
  }

  test("getUserProfile()") {

    getUserProfile() onComplete{
      case Failure(exception)       => fail("getUserProfile() failed with error")
      case Success(userProfile)     => assert(userProfile.userId.isInstanceOf[UUID] )
    }
  }
  