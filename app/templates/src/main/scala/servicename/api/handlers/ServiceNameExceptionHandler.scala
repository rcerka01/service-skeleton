package <%= packageName %>.api.handlers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.ExceptionHandler
import <%= packageName %>.<%= capitalisedName %>Context.log
import <%= packageName %>.config.Config

trait <%= capitalisedName %>ExceptionHandler extends Config with ErrorFormat {

  implicit val routingExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case e: Throwable => {
        log.error(e.getMessage)
        completeWithError(errorDocumentationUrl) {
          ErrorResponseData(StatusCodes.InternalServerError, "Server Error")
        }
      }
    }
}
