package <%= packageName %>

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import <%= packageName %>.api.Api
import <%= packageName %>.config.Config
import org.slf4s.Logging
import scala.concurrent.ExecutionContextExecutor

object <%= capitalisedName %>Service extends App
  with Api
  with Config
  with Logging {

  implicit val system: ActorSystem = <%= capitalisedName %>Context.system
  implicit val materializer: ActorMaterializer = <%= capitalisedName %>Context.materializer

  implicit def executor: ExecutionContextExecutor = system.dispatcher


  Http().bindAndHandle(routes, httpInterface, httpPort)

  log.info(s"Starting $serviceName on $httpInterface:$httpPort")
}
