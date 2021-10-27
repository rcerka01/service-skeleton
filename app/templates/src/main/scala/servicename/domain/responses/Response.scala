package <%= packageName %>.domain.responses

case class Response[T](
  total: Int,
  limit: Int,
  offset: Int,
  results: Seq[T])
