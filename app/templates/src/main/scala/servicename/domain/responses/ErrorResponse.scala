package <%= packageName %>.domain.responses

import org.joda.time.LocalDateTime

import java.util.UUID

case class ErrorResponse(error: ErrorItem)

case class ErrorItem(
  id: String,
  href: String,
  status: Int,
  message: String,
  replied_at: LocalDateTime)

object ErrorResponse {
  private def hash = UUID.randomUUID.toString

  def apply(documentationUrl: String, httpStatus: Int, message: String): ErrorResponse =
    ErrorResponse(
      error =
        ErrorItem(
          id = hash,
          href = documentationUrl,
          status = httpStatus,
          message = message,
          replied_at = new LocalDateTime()
        )
      )
}
