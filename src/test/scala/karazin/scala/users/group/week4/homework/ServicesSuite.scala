package karazin.scala.users.group.week4.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/*
  Write test for all service in karazin.scala.users.group.week4.homework.services
  Make sure you control custom execution contexts in tests using `before` and `after` logic

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

import karazin.scala.users.group.week3.homework.Utils.checkList

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.runtime.Nothing$
import munit.Clue.generate
import model._
import program._
import services._

import scala.concurrent.Future.never.onComplete
import scala.util.{Failure, Success}

/*
  Write test for all service in karazin.scala.users.group.week4.homework.services
  Make sure you control custom execution contexts in tests using before and after logic
  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

class ServicesSuite extends munit.FunSuite :

  test("getPosts()") {

    val userId = UUID.randomUUID()

    val getPostsService = getPosts(userId)(using sigleThreadPoolContext)
    getPostsService map { posts =>
      checkList[Post](posts) { post =>
        assertEquals(post.userId, userId)
      }
    } recover {
      case error => fail("getPosts() failed with error")
    }
  }


  test("getComments()") {

    val postId = UUID.randomUUID()

    val getCommentsService = getComments(postId)(using sigleThreadPoolContext)
    getCommentsService map { comments =>
      checkList[Comment](comments) { comment =>
        assertEquals(comment.postId, postId)
      }
    } recover{
      case error => fail("getComments() failed with error")
    }
  }

  test("getLikes()") {

    val postId = UUID.randomUUID()
    val getLikesService = getLikes(postId)(using fixedThreadPoolContext)
    getLikesService map { likes =>
      checkList[Like](likes) { like =>
        assertEquals(like.postId, postId)
      }
    } recover{
      case error => fail("getLikes() failed with error")
    }
  }

  test("getShares()") {

    val postId = UUID.randomUUID()

    val getSharesService = getShares(postId)(using fixedThreadPoolContext)
    getSharesService map { shares =>
      checkList[Share](shares) { share =>
        assertEquals(share.postId, postId)
      }
    } recover {
      case error => fail("getShares() failed with error")
    }
  }

  test("getUserProfile()") {

    val getUserProfileService = getUserProfile(using fixedThreadPoolContext)

    getUserProfileService map { userProfile =>
      assert(userProfile.userId.isInstanceOf[UUID])
    } recover {
      case error => fail("getUserProfile() failed with error")
    }
  }
  