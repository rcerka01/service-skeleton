package <%= packageName %>.api.directives

trait CustomDirectives extends ResponseDirectives
  with ErrorResponseDirectives
  with CacheDirectives
  with PaginateDirectives
  with SortDirectives
