package <%= packageName %>.api.params

case class Sort(field: Option[String], isAscending: Boolean = true)
