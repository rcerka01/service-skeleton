package <%= packageName %>.datasource

import <%= packageName %>.domain.Item
import <%= packageName %>.domain.responses.ResponseWrapper.{MultiEntityResponseData, SingleEntityResponseData}

import scala.concurrent.Future

trait ItemDataSource {
  def getSingleItem(id: Int): Future[SingleEntityResponseData[Item]]
  def getMultipleItems(limit: Int, offset: Int): Future[MultiEntityResponseData[Item]]
}
