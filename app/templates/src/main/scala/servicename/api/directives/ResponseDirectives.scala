package <%= packageName %>.api.directives

import <%= packageName %>.api.directives.ResponseDirectives.{MultiEntityResponseData, SingleEntityResponseData}
import <%= packageName %>.api.params.Paginate
import <%= packageName %>.domain.response.Response
import scala.concurrent.{ExecutionContextExecutor, Future}

trait ResponseDirectives {
  implicit def executor: ExecutionContextExecutor

  def toResponse[T](pagination: Paginate)
                   (resultsFuture: Future[MultiEntityResponseData[T]]): Future[Response[T]] = {

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
                   (resultsFuture: Future[SingleEntityResponseData[T]]): Future[Response[T]] = {

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

object ResponseDirectives {
  case class SingleEntityResponseData[T](entity: Option[T], total: Int)
  case class MultiEntityResponseData[T](entities: Seq[T], total: Int)
}

