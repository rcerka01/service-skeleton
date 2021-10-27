package <%= packageName %>.api.directives.custom

import akka.http.scaladsl.model.HttpHeader
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.Directive0
import akka.http.scaladsl.server.Directives.respondWithHeader

trait CacheDirectives {
  /**
    * max-age=300 = (5 Minutes)
    * stale-while-revalidate=7200 = (2 Hours)
    */
  private val defaultCacheHeader: HttpHeader = RawHeader(
    "Cache-Control",
    "public, max-age=300, s-maxage=300, stale-while-revalidate=7200, stale-if-error=7200"
  )

  private val defaultNoCacheHeader: HttpHeader = RawHeader(
    "Cache-Control",
    "no-cache, no-store, must-revalidate"
  )

  val respondWithCacheHeaders: Directive0 = respondWithHeader(defaultCacheHeader)
  val respondWithNoCacheHeaders: Directive0 = respondWithHeader(defaultNoCacheHeader)
}
