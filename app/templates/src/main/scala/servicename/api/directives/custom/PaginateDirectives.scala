package <%= packageName %>.api.directives.custom

import akka.http.scaladsl.model.{StatusCode, StatusCodes}
import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Directives.{parameters, provide, reject}
import <%= packageName %>.api.handlers.<%= capitalisedName %>Rejection
import <%= packageName %>.domain.params.Paginate

trait PaginateDirectives {

  def isInvalidOffset(offset: Int): Boolean = offset < 0

  def isInvalidPageLimit(maximumPageLimit: Int)(requestedPageLimit: Int): Boolean =
    requestedPageLimit < 0 || requestedPageLimit > maximumPageLimit

  case class InvalidOffsetRejection(offset: Int) extends <%= capitalisedName %>Rejection {
    val statusCode: StatusCode = StatusCodes.BadRequest
    val message = s"Invalid value for offset: '$offset'"
  }
  case class InvalidPageLimitRejection(maximumPageLimit: Int) extends <%= capitalisedName %>Rejection {
    val statusCode: StatusCode = StatusCodes.BadRequest
    val message = s"Page limit must be between 0 and $maximumPageLimit"
  }

  def paginate(defaultPageLimit: Int, maximumPageLimit: Int): Directive1[Paginate] = {
    parameters("offset".as[Int].?, "limit".as[Int].?).tflatMap {
      case (Some(offset), _) if isInvalidOffset(offset) =>
        reject(InvalidOffsetRejection(offset))
      case (_, Some(limit)) if isInvalidPageLimit(maximumPageLimit)(limit) =>
        reject(InvalidPageLimitRejection(maximumPageLimit))
      case (offset, limit) =>
        provide(Paginate(offset.getOrElse(0), limit.getOrElse(defaultPageLimit)))
    }
  }
}
