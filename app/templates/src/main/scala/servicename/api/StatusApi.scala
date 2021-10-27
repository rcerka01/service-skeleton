package <%= packageName %>.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import <%= packageName %>.api.directives.EnabledDirectives
import <%= packageName %>.domain.marshalling.JsonSerializers
import <%= packageName %>.domain.responses.Status

trait StatusApi extends EnabledDirectives with JsonSerializers with SprayJsonSupport {

  val statusRoutes: Route = {
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
