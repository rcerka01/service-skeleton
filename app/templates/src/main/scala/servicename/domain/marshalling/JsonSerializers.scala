package <%= packageName %>.domain.marshalling

import <%= packageName %>.domain.Item
import <%= packageName %>.domain.responses.{ErrorItem, ErrorResponse, Response, Status}
import org.joda.time.LocalDateTime
import spray.json._

import scala.util.{Failure, Success, Try}

trait JsonSerializers extends DefaultJsonProtocol {
  // JODA LocalDateTime
  implicit val jodaLocalDateTimeFormat: JsonFormat[LocalDateTime] =
    new JsonFormat[LocalDateTime] {
      override def write(obj: LocalDateTime): JsValue = JsString(obj.toString)
      override def read(json: JsValue): LocalDateTime = json match {
        case JsString(s) => Try(LocalDateTime.parse(s)) match {
          case Success(result) => result
          case Failure(exception) =>
            deserializationError(s"Serialization error. Could not parse $s as date::time $exception")
        }
        case notAJsString =>
          deserializationError(s"Serialization error. Could not parse value: $notAJsString")
      }
    }

  implicit val itemJson: RootJsonFormat[Item] = jsonFormat1(Item)
  implicit val statusJson: RootJsonFormat[Status] = jsonFormat1(Status)
  implicit val responseJson: RootJsonFormat[Response[Item]] = jsonFormat4(Response[Item])

  implicit val errorItemResponseJson: RootJsonFormat[ErrorItem] = jsonFormat5(ErrorItem)
  implicit val errorResponseJson: RootJsonFormat[ErrorResponse] = jsonFormat1(ErrorResponse.apply) // Class with Companion object
}
