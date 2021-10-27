package <%= packageName %>

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import <%= packageName %>.config.Config
import <%= packageName %>.datasource.{ItemDataSource, RandomItemDataSource}

object <%= capitalisedName %>Context extends Config {
  implicit val system: ActorSystem = ActorSystem(serviceName)
  val log: LoggingAdapter = system.log

  val itemDataSource: ItemDataSource = new RandomItemDataSource
}
