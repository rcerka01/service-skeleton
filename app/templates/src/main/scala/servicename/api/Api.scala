package <%= packageName %>.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import <%= packageName %>.api.handlers.{<%= capitalisedName %>ExceptionHandler, <%= capitalisedName %>RejectionHandler}
import <%= packageName %>.<%= capitalisedName %>Context
import <%= packageName %>.datasource.ItemDataSource

trait Api extends StatusApi
   with ItemApi
   with <%= capitalisedName %>RejectionHandler
   with <%= capitalisedName %>ExceptionHandler {

  override def itemDataSource: ItemDataSource = <%= capitalisedName %>Context.itemDataSource

  lazy val routes: Route = {
    logRequestResult("<%= serviceName %>") {
      Route.seal {
        statusRoutes ~ itemRoutes
      }
    }
  }
}
