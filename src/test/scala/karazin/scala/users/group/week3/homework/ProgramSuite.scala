package karazin.scala.users.group.week3.homework

import karazin.scala.users.group.week3.homework.program


import java.util.UUID
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

import model._
import services._
import program._
import Utils._

/*
  Write test for all programs in karazin.scala.users.group.week3.homework.program
  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ProgramSuite extends munit.FunSuite:



  test("getPostView-checkPost") {

    val postId = Post(UUID.randomUUID(), UUID.randomUUID())

    getPostView(postId) onComplete{
      case Failure(exception) => fail("`getPostView()` failed with error")
      case Success(posts)     => checkList[PostView](List(posts)){ postView =>
        assertEquals(postView.post, postId)
      }
    }
  }

  test("getPostView-checkComments") {

    val postId = Post(UUID.randomUUID(), UUID.randomUUID())

    getPostView(postId) onComplete{
      case Failure(exception) => fail("`getPostView()` failed with error")
      case Success(posts)     => checkList[Comment](posts.comments){ comment =>
        assertEquals(postId.postId, comment.postId)
      }
    }
  }

  test("getPostView-checkLikes") {

    val postId = Post(UUID.randomUUID(), UUID.randomUUID())

    getPostView(postId) onComplete{
      case Failure(exception) => fail("getPostView()")
      case Success(posts)     => checkList[Like](posts.likes){ like =>
        assertEquals(postId.postId, like.postId)
      }
    }
  }

  test("getPostView-checkShares") {

    val postId = Post(UUID.randomUUID(), UUID.randomUUID())

    getPostView(postId) onComplete{
      case Failure(exception) => fail("getPostView() shares failed with error")
      case Success(posts)     => checkList[Share](posts.shares){ share =>
        assertEquals(postId.postId, share.postId)
      }
    }
  }