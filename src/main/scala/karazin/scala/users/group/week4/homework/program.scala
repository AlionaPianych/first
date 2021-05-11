/*Task:
  • fix the code to make it compilable
  • use at least 2 execution contexts:
    • one for a `for comprehension`
    • at least one for `getComments`, `getLikes`, `getShares`
  • write tests
*/
package karazin.scala.users.group.week4.homework

import java.util.UUID
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import scala.util.Success
import scala.util.Failure
import karazin.scala.users.group.week4.homework.model._
import karazin.scala.users.group.week4.homework.services._

import java.util.concurrent.Executors


object program:
  val sigleThreadPoolContext: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor)

  val fixedThreadPoolContext: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(12))

  given ExecutionContext = ExecutionContext.global



  def getPostsViews()(using ec: ExecutionContext): Future[List[PostView]] = {


    val getProfileService  = getUserProfile(using fixedThreadPoolContext)

    for
      profile ← getProfileService
      posts ← getPosts(profile.userId)(using fixedThreadPoolContext)
      postViews ← Future.foldLeft(posts map{ post ⇒ getPostView(post) })(List[PostView]()){ (acc, item) => item :: acc }
    yield postViews
  }

  def getPostView(post: Post)(using ec: ExecutionContext): Future[PostView] =
    val getCommentsService  = getComments(post.postId)(using sigleThreadPoolContext)
    val getLikesService     = getLikes(post.postId)(using fixedThreadPoolContext)
    val getSharesService    = getShares(post.postId)(using fixedThreadPoolContext)

    for
      comments  ← getCommentsService
      likes     ← getLikesService
      shares    ← getSharesService
    yield PostView(post, comments, likes, shares)