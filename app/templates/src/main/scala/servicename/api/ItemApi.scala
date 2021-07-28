package <%= packageName %>.api

import akka.http.scaladsl.server.Directives._
import <%= packageName %>.api.directives.CustomDirectives
import <%= packageName %>.config.Config
import <%= packageName %>.datasource.ItemDataSource
import <%= packageName %>.domain.marshalling.JsonSerializers
import org.slf4s.Logging

trait ItemApi extends JsonSerializers
  with CustomDirectives
  with Config
  with Logging {

  def itemDataSource: ItemDataSource

  val itemRoutes = {
    path("items") {
      get {
        respondWithCacheHeaders {
          paginate(defaultPageLimit, maximumPageLimit) { pagination =>
            sort { itemSort =>
              complete {
                log.info("/items")
                toResponse(pagination) {
                  itemDataSource.getMultipleItems(pagination.limit, pagination.offset)
                }
              }
            }
          }
        }
      }
    } ~ path("items" / IntNumber) { id =>
      get {
        respondWithCacheHeaders {
          complete {
            log.info(s"/items/$id")
            toResponse {
              itemDataSource.getSingleItem(id)
            }
          }
        }
      }
    }
  }
}

