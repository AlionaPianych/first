package karazin.scala.users.group.week4.homework

import java.util.UUID
import scala.concurrent.Future
import scala.util.Success
import scala.util.Failure
import scala.concurrent.ExecutionContext

import scala.concurrent.Await
import scala.concurrent.duration._

import scala.language.postfixOps

import karazin.scala.users.group.week4.homework.model._


object services:

 def getUserProfile(using ec: ExecutionContext): Future[UserProfile] =
   Future {
     UserProfile(UUID.randomUUID())
   }

 def getPosts(userId: UUID)(using ec: ExecutionContext): Future[List[Post]] =
   Future {
     Post(userId, postId = UUID.randomUUID()) :: Nil
   }

 def getComments(postId: UUID)(using ec: ExecutionContext): Future[List[Comment]] =
   Future {
      // Emulating time consumed operation
     Thread.sleep(5000)
     Comment(userId = UUID.randomUUID(), postId) :: Nil
   }

 def getLikes(postId: UUID)(using ec: ExecutionContext): Future[List[Like]] =
   Future {
      // Emulating time consumed operation
     Thread.sleep(2000)
     Like(userId = UUID.randomUUID(), postId) :: Nil
   }

 def getShares(postId: UUID)(using ec: ExecutionContext): Future[List[Share]] =
   Future {
      // Emulating time consumed operation
     Thread.sleep(500)
     Share(userId = UUID.randomUUID(), postId) :: Nil
   }
 