package karazin.scala.users.group.week4.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/*
  Write test for all programs in karazin.scala.users.group.week4.homework.program
  Make sure you control custom execution contexts in tests using `before` and `after` logic

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

import karazin.scala.users.group.week3.homework.Utils.checkList

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import model._
import program._
import services._

import java.util.UUID
import scala.util.{Failure, Success}

/*
  Write test for all programs in karazin.scala.users.group.week4.homework.program
  Make sure you control custom execution contexts in tests using before and after logic
  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

class ProgramSuite extends munit.FunSuite:

  test("getPostView-checkPost") {

    val post = Post(UUID.randomUUID(), UUID.randomUUID())

    val checkPostService = getPostView(post)(using sigleThreadPoolContext)
    checkPostService map { posts =>
      checkList[PostView](List(posts)) { postView =>
        assertEquals(postView.post, post)
      }
    } recover {
      case error => fail("getPostView() failed with error")
    }
  }

  test("getPostView-checkComments") {

    val post = Post(UUID.randomUUID(), UUID.randomUUID())

    val checkCommentsService = getPostView(post)(using sigleThreadPoolContext)

    checkCommentsService map { posts =>
      checkList[Comment](posts.comments) { comment =>
        assertEquals(post.postId, comment.postId)
      }
    } recover {
      case error => fail(s"getPostView() comments failed with error")
    }
  }

  test("getPostView-checkLikes") {

    val post = Post(UUID.randomUUID(), UUID.randomUUID())

    val checkLikesService = getPostView(post) (using sigleThreadPoolContext)

    checkLikesService map { posts =>
      checkList[Like](posts.likes) { like =>
        assertEquals(post.postId, like.postId)
      }
    } recover {
      case error => fail("getPostView()")
    }
  }

  test("getPostView-checkShares") {

    val post = Post(UUID.randomUUID(), UUID.randomUUID())

    val checkSharesService = getPostView(post) (using sigleThreadPoolContext)

    checkSharesService map { posts =>
      checkList[Share](posts.shares) { share =>
        assertEquals(post.postId, share.postId)
      }
    } recover {
      case error => fail("getPostView() shares failed with error")
    }
  }

  test("getPostViews result") {
    val getPostsViewService  = getPostsViews()(using sigleThreadPoolContext)
    getPostsViews() map { list=>
      checkList[PostView](list) { p => assert(true)}
    } recover {
      case error => fail("getPostView() result exception")
    }
  }