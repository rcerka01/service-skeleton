package <%= packageName %>.api.error

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.ExceptionHandler
import <%= packageName %>.api.directives.ErrorResponseDirectives
import <%= packageName %>.api.directives.ErrorResponseDirectives.ErrorResponseData
import <%= packageName %>.config.Config
import org.slf4s.Logging

trait <%= capitalisedName %>ExceptionHandler extends ErrorResponseDirectives
  with Config
  with Logging {

  implicit val routingExceptionHandler =
    ExceptionHandler {
      case exception => {
        log.error(exception.getMessage, exception)
        completeWithError(errorDocumentationUrl) {
          ErrorResponseData(StatusCodes.InternalServerError, "Server Error")
        }
      }
    }
}
