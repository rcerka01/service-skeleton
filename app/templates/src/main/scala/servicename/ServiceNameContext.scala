package <%= packageName %>

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import <%= packageName %>.config.Config

import <%= packageName %>.datasource.{ItemDataSource, RandomItemDataSource}

object <%= capitalisedName %>Context extends Config {
  implicit val system: ActorSystem = ActorSystem(serviceName)
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val itemDataSource: ItemDataSource = new RandomItemDataSource
}
