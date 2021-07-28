package <%= packageName %>.api.directives

import akka.http.scaladsl.server.{Directive1, Rejection}
import akka.http.scaladsl.server.Directives._
import <%= packageName %>.api.validation.PaginationValidator
import <%= packageName %>.api.params.Paginate
import <%= packageName %>.api.rejection.{InvalidOffsetRejection, InvalidPageLimitRejection}

trait PaginateDirectives {
  private val paginationValidator = new PaginationValidator

  private val isInvalidPageLimit: Int => (Int => Boolean) =
    paginationValidator.isInvalidPageLimit

  def paginate(defaultPageLimit: Int, maximumPageLimit: Int): Directive1[Paginate] = {
    parameters("offset".as[Int].?, "limit".as[Int].?).tflatMap {
      case (Some(offset), _) if paginationValidator.isInvalidOffset(offset) =>
        reject(InvalidOffsetRejection(offset))
      case (_, Some(limit)) if paginationValidator.isInvalidPageLimit(maximumPageLimit)(limit) =>
        reject(InvalidPageLimitRejection(maximumPageLimit))
      case (offset, limit) =>
        provide(Paginate(offset.getOrElse(0), limit.getOrElse(defaultPageLimit)))
    }
  }
}