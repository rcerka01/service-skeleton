package <%= packageName %>.api.rejection

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.RejectionHandler
import <%= packageName %>.api.directives.ErrorResponseDirectives.ErrorResponseData
import <%= packageName %>.api.directives.ErrorResponseDirectives
import <%= packageName %>.config.Config

trait <%= capitalisedName %>RejectionHandler extends ErrorResponseDirectives
  with Config {

  implicit val rejectionHandler = RejectionHandler.newBuilder()
    .handle { case <%= camelCaseName %>Rejection: <%= capitalisedName %>Rejection =>
      completeWithError(errorDocumentationUrl) {
        ErrorResponseData(<%= camelCaseName %>Rejection.statusCode, <%= camelCaseName %>Rejection.message)
      }
    }
    .handleNotFound {
      completeWithError(errorDocumentationUrl) {
        ErrorResponseData(StatusCodes.NotFound, "Not found")
      }
    }
    .result()
}
