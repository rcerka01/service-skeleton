package <%= packageName %>.api.directives

import <%= packageName %>.api.directives.custom.{CacheDirectives, PaginateDirectives, SortDirectives}
import <%= packageName %>.domain.responses.ResponseWrapper

trait EnabledDirectives extends ResponseWrapper
  with CacheDirectives
  with PaginateDirectives
  with SortDirectives
