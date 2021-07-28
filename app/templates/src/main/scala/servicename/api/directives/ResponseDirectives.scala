package <%= packageName %>.api.directives

import <%= packageName %>.api.directives.ResponseDirectives.MultiEntityResponseData
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
                   (resultsFuture: Future[Option[T]]): Future[Response[T]] = {

    resultsFuture.map { results =>
      Response(
        total = 1,
        limit = 1,
        offset = 0,
        results = results.toSeq
      )
    }
  }

  def toResponse[T](singleResult: T): Response[T] = {

    Response(
      total = 1,
      limit = 1,
      offset = 0,
      results = Seq(singleResult)
    )
  }
}

object ResponseDirectives {
  case class MultiEntityResponseData[T](entities: Seq[T], total: Int)
}

