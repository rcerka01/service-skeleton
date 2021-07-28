package <%= packageName %>.api

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer
import <%= packageName %>.<%= capitalisedName %>Context
import <%= packageName %>.api.error.<%= capitalisedName %>ExceptionHandler
import <%= packageName %>.api.rejection.<%= capitalisedName %>RejectionHandler
import <%= packageName %>.datasource.ItemDataSource

import scala.concurrent.ExecutionContextExecutor

trait Api extends StatusApi
  with ItemApi
  with <%= capitalisedName %>RejectionHandler
  with <%= capitalisedName %>ExceptionHandler {

  override def itemDataSource: ItemDataSource = <%= capitalisedName %>Context.itemDataSource

  lazy val routes = {
    logRequestResult("<%= serviceName %>") {
      statusRoutes ~ itemRoutes
    }
  }
}
