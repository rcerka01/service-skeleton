package <%= packageName %>.api.directives.custom

import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.Directives.parameter
import <%= packageName %>.domain.params.Sort
import akka.http.scaladsl.server.Directives._

trait SortDirectives {
  def sort: Directive1[Sort] =
    readSortParams.map {
      case Some(sort) =>
        if (sort.startsWith("-")) {
          Sort(Some(sort.substring(1)), isAscending = false)
        } else {
          Sort(Some(sort))
        }
      case None => Sort(None)
    }

  private val readSortParams = parameter("sort".as[String].?)
}
