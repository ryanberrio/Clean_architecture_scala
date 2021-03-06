package com.example

//#user-routes-spec
//#test-top
/*
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.co.UserRoutes
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.ExecutionContext.Implicits.global

//#set-up
class UserRoutesSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest
  with UserRoutes {
  import adapters.presenters.UserRegistryActor
  import akka.actor.ActorRef
  import akka.http.scaladsl.server.Route
  //#test-top

  // Here we need to implement all the abstract members of UserRoutes.
  // We use the real UserRegistryActor to test it while we hit the Routes, 
  // but we could "mock" it by implementing it in-place or by using a TestProbe() 
  val userRegistryActor: ActorRef = system.actorOf(UserRegistryActor.props, "userRegistry")

  lazy val routes:Route = userRoutes

  //#set-up

  //#actual-test
  "UserRoutes" should {
    "return no users if no present (GET /users)" in {
      import akka.http.scaladsl.model.HttpRequest
      // note that there's no need for the host part in the uri:
      val request = HttpRequest(uri = "/users")

      request ~> routes ~> check {
        import akka.http.javadsl.model.{ContentTypes, StatusCodes}
        status should ===(StatusCodes.OK)

        // we expect the response to be json:
        contentType should ===(ContentTypes.`application/json`)

        // and no entries should be in the list:
        entityAs[String] should ===("""{"users":[]}""")
      }


    }
    //#actual-test

    //#testing-post
    "be able to add users (POST /users)" in {


      val user = User("Kapi", 42, "jp")
      val userEntity = Marshal(user).to[MessageEntity].futureValue // futureValue is from ScalaFutures

      // using the RequestBuilding DSL:
      val request = Post("/users").withEntity(userEntity)

      request ~> routes ~> check {
        status should ===(StatusCodes.Created)

        // we expect the response to be json:
        contentType should ===(ContentTypes.`application/json`)

        // and we know what message we're expecting back:
        entityAs[String] should ===("""{"description":"User Kapi created."}""")
      }
    }
    //#testing-post

    "be able to remove users (DELETE /users)" in {
      // user the RequestBuilding DSL provided by ScalatestRouteSpec:
      val request = Delete(uri = "/users/Kapi")

      request ~> routes ~> check {
        status should ===(StatusCodes.OK)

        // we expect the response to be json:
        contentType should ===(ContentTypes.`application/json`)

        // and no entries should be in the list:
        entityAs[String] should ===("""{"description":"User Kapi deleted."}""")
      }
    }
    //#actual-test
  }
  //#actual-test

  //#set-up
}
//#set-up
//#user-routes-spec
*/