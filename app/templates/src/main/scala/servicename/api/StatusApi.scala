package <%= packageName %>.api

import akka.http.scaladsl.server.Directives._
import <%= packageName %>.api.directives.CustomDirectives
import <%= packageName %>.domain.response.Status
import <%= packageName %>.domain.marshalling.JsonSerializers

trait StatusApi extends JsonSerializers
  with CustomDirectives {

  val statusRoutes = {
    path("status") {
      get {
        respondWithNoCacheHeaders {
          complete {
            Status("OK")
          }
        }
      }
    }
  }
}
