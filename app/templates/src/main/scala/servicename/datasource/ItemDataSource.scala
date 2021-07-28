package <%= packageName %>.datasource

import <%= packageName %>.api.directives.ResponseDirectives.MultiEntityResponseData
import <%= packageName %>.domain.Item
import scala.concurrent.Future

trait ItemDataSource {
  def getSingleItem(id: Int): Future[Option[Item]]
  def getMultipleItems(limit: Int, offset: Int): Future[MultiEntityResponseData[Item]]
}
