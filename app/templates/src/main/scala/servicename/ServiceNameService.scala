package <%= packageName %>

import akka.http.scaladsl.Http
import <%= packageName %>.api.Api
import <%= packageName %>.config.Config
import <%= capitalisedName %>Context._

import scala.concurrent.ExecutionContext

object <%= capitalisedName %>Service extends App
  with Api
  with Config {

  implicit def executor: ExecutionContext = system.dispatchers.lookup("<%= serviceName %>-dispatcher")

  Http().newServerAt(httpInterface, httpPort).bind(routes)

  log.info(s"Starting $serviceName on $httpInterface:$httpPort")
}
