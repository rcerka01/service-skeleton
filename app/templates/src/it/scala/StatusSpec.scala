import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.ContentTypes.`application/json`
import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.testkit.ScalatestRouteTest
import <%= packageName %>.<%= capitalisedName %>Service.routes
import <%= packageName %>.domain.marshalling.JsonSerializers
import <%= packageName %>.domain.responses.Status
import org.scalatest.matchers.must.Matchers.{be, convertToAnyMustWrapper}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StatusSpec extends AnyWordSpec
  with Matchers
  with ScalatestRouteTest
  with JsonSerializers
  with SprayJsonSupport {

  "The service" should {
    "respond with a 200 at /status" in {
      Get("/status") ~> routes ~> check {
        status mustBe OK
        contentType mustBe `application/json`
        responseAs[Status] mustBe Status("OK")
        header("Cache-Control").get.value mustBe "no-cache, no-store, must-revalidate"
      }
    }
  }
}
