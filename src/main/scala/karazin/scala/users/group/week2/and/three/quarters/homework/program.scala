package karazin.scala.users.group.week2.and.three.quarters.homework

// Do not forget to import custom implementation
import karazin.scala.users.group.week2.and.three.quarters.homework.adt._
import karazin.scala.users.group.week2.and.three.quarters.homework.model._
import karazin.scala.users.group.week2.and.three.quarters.homework.services._

object program:

  /*
   Print all view for all user's posts if they exists
  */
  def printPostsViews(): ErrorOr[List[PostView]] = {
    for
      postViews     ← getPostsViews()
    yield {
      ErrorOr(postViews).foreach(print)
      postViews
    }
  }

  /*
   Getting view for all user's posts if they exists
  */
  def getPostsViews(): ErrorOr[List[PostView]] = {

    for
      profile ← getUserProfile()
      posts ← getPosts(profile.userId)
      postsView ← ErrorOr(posts map{ post ⇒ getPostView(post) })
    yield {
      postsView.foldLeft(List[PostView]()){ (acc, item) =>
        item match
          case ErrorOr.Some(v)         => v +: acc
          case _                       => acc
      }
    }
  }

  def getPostView(post: Post): ErrorOr[PostView] = {
    for
      comments ← getComments(post.postId)
      likes ← getLikes(post.postId)
      shares ← getShares(post.postId)
    yield PostView(post, comments, likes, shares)
  }

  /*
   Provide desugared version of the previous two methods
  */
  def getPostsViewDesugared() = getUserProfile() flatMap { profile ⇒
    getPosts(profile.userId)
  } map { posts ⇒
    posts map { post ⇒
      getComments(post.postId) flatMap { comments ⇒
        getLikes(post.postId) flatMap { likes ⇒
          getShares(post.postId) map { shares ⇒
            PostView(post, comments, likes, shares)
          }
        }
      }
    }
  }