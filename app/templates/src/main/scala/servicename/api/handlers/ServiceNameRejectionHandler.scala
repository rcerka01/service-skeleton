package <%= packageName %>.api.handlers

import akka.http.scaladsl.model.{StatusCode, StatusCodes}
import akka.http.scaladsl.server.{Rejection, RejectionHandler}
import <%= packageName %>.config.Config


trait <%= capitalisedName %>Rejection extends Rejection {
  def statusCode: StatusCode
  def message: String
}

trait <%= capitalisedName %>RejectionHandler extends ErrorFormat
  with Config {

  implicit val rejectionHandler = RejectionHandler.newBuilder()
    .handle { case <%= unCapitalisedName %>Rejection: <%= capitalisedName %>Rejection =>
      completeWithError(errorDocumentationUrl) {
        ErrorResponseData(<%= unCapitalisedName %>Rejection.statusCode, <%= unCapitalisedName %>Rejection.message)
      }
    }
    .handleNotFound {
      completeWithError(errorDocumentationUrl) {
        ErrorResponseData(StatusCodes.NotFound, "Not found")
      }
    }
    .result()
}
