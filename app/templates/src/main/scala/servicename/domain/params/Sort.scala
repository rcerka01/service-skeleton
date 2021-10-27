package <%= packageName %>.domain.params

case class Sort(field: Option[String], isAscending: Boolean = true)
