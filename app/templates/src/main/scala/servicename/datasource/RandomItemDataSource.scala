package <%= packageName %>.datasource

import <%= packageName %>.api.directives.ResponseDirectives.{MultiEntityResponseData,SingleEntityResponseData}
import <%= packageName %>.domain.Item

import scala.concurrent.Future
import scala.util.Random

class RandomItemDataSource extends ItemDataSource {
  private def randomItem = Item(Random.nextString(length = 4))
  private val itemRepository: Seq[Item] = Stream.continually(randomItem)

  override def getSingleItem(id: Int): Future[SingleEntityResponseData[Item]] = {
    Future.successful(SingleEntityResponseData(itemRepository.headOption, 1))
  }

  override def getMultipleItems(limit: Int, offset: Int): Future[MultiEntityResponseData[Item]] = {
    val results: Seq[Item] = itemRepository.take(limit)
    Future.successful(MultiEntityResponseData(results, results.size))
  }
}
