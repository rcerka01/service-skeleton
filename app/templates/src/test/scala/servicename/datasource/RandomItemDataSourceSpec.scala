package <%= packageName %>.datasource

import <%= packageName %>.domain.Item
import org.specs2.mutable.Specification
import util.FutureSupport

class RandomItemDataSourceSpec extends Specification
  with FutureSupport {

  "The RandomItemDataSource" should {
    "return an item at some point in the future" in {
      val randomItemDataSource = new RandomItemDataSource

      whenReady(randomItemDataSource.getSingleItem(1)) { item =>
        item must beSome[Item]
      }
    }
  }
}
