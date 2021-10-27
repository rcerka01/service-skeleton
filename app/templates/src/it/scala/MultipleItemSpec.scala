import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.ContentTypes.`application/json`
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import <%= packageName %>.<%= capitalisedName %>Service.routes
import <%= packageName %>.domain.Item
import <%= packageName %>.domain.marshalling.JsonSerializers
import <%= packageName %>.domain.responses.{ErrorResponse, Response}
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MultipleItemSpec extends AnyWordSpec
  with Matchers
  with ScalatestRouteTest
  with JsonSerializers
  with SprayJsonSupport {

  "The service" should {
    "return a response with items" in {
      Get("/items") ~> Route.seal(routes) ~> check {
        status mustBe StatusCodes.OK
        contentType mustBe `application/json`
        responseAs[Response[Item]].total must be > 0
        header("Cache-Control").get.value mustBe "public, max-age=300, s-maxage=300, stale-while-revalidate=7200, stale-if-error=7200"
      }
    }

    "return a response with the requested limit" in {
      Get("/items?limit=1") ~> Route.seal(routes) ~> check {
        status mustBe  StatusCodes.OK
        responseAs[Response[Item]].total mustBe 1
      }
    }

    "return a 400 Bad Request when a limit greater than the maximum is requested" in {
      Get("/items?limit=101") ~> Route.seal(routes) ~> check {
        status mustBe StatusCodes.BadRequest
        responseAs[ErrorResponse].error.message mustBe "Page limit must be between 0 and 100"
      }
    }
  }
}
