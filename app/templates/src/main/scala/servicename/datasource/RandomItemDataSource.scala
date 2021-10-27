package <%= packageName %>.datasource

import <%= packageName %>.domain.Item
import <%= packageName %>.domain.responses.ResponseWrapper.{MultiEntityResponseData, SingleEntityResponseData}

import scala.concurrent.Future
import scala.util.Random

class RandomItemDataSource extends ItemDataSource {
  val LENGTH = 4
  private def randomItem = Item(Random.nextString(length = LENGTH))
  private val itemRepository: Seq[Item] = LazyList.continually(randomItem)

  override def getSingleItem(id: Int): Future[SingleEntityResponseData[Item]] = {
    Future.successful(SingleEntityResponseData(itemRepository.headOption, 1))
  }

  override def getMultipleItems(limit: Int, offset: Int): Future[MultiEntityResponseData[Item]] = {
    val results: Seq[Item] = itemRepository.take(limit)
    Future.successful(MultiEntityResponseData(results, results.size))
  }
}
