package <%= packageName %>.api.handlers

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCode
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import <%= packageName %>.domain.marshalling.JsonSerializers
import <%= packageName %>.domain.responses.ErrorResponse

case class ErrorResponseData(statusCode: StatusCode, message: String)

trait ErrorFormat extends JsonSerializers with SprayJsonSupport {

  def completeWithError(documentationUrl: String)
                       (errorResponseData: ErrorResponseData): StandardRoute = {

    val errorResponse = ErrorResponse(
      documentationUrl = documentationUrl,
      httpStatus = errorResponseData.statusCode.intValue,
      message = errorResponseData.message)

    complete(errorResponseData.statusCode, errorResponse)
  }
}
