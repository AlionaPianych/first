package karazin.scala.users.group.week3.homework

import java.util.UUID
import scala.concurrent.Future
import scala.util.Success
import scala.util.Failure

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

import scala.language.postfixOps

import karazin.scala.users.group.week3.homework.model._

import model._
import services._


object program:

  def getPostView(post: Post): Future[PostView] =

    val getCommentsService  = getComments(post.postId)
    val getLikesService     = getLikes(post.postId)
    val getSharesService    = getShares(post.postId)

    for
    comments  ← getCommentsService
    likes     ← getLikesService
    shares    ← getSharesService
      yield PostView(post, comments, likes, shares)