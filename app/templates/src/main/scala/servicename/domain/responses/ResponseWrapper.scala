package <%= packageName %>.domain.responses

import <%= packageName %>.domain.params.Paginate
import <%= packageName %>.domain.responses.ResponseWrapper.{MultiEntityResponseData, SingleEntityResponseData}

import scala.concurrent.{ExecutionContext, Future}

trait ResponseWrapper {
  implicit def executor: ExecutionContext

  def toResponse[T](pagination: Paginate)
                   (implicit resultsFuture: Future[MultiEntityResponseData[T]]): Future[Response[T]] = {

    resultsFuture.map { results =>
      Response(
        total = results.total,
        limit = pagination.limit,
        offset = pagination.offset,
        results = results.entities
      )
    }
  }

  def toResponse[T]()
                   (implicit resultsFuture: Future[SingleEntityResponseData[T]]): Future[Response[T]] = {

    resultsFuture.map { result =>
      Response(
        total = result.total,
        limit = 1,
        offset = 0,
        results = result.entity.toSeq
      )
    }
  }

}

object ResponseWrapper {
  case class SingleEntityResponseData[T](entity: Option[T], total: Int)
  case class MultiEntityResponseData[T](entities: Seq[T], total: Int)
}
