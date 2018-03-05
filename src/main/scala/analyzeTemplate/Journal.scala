package analyzeTemplate

case class Journal(time: Option[String], srcUser: Option[String], srcIp: Option[String], srcPort: Int,
                   destUser: Option[String], destIp: Option[String], destPort: Int, inputByte: Int,
                   outputByte: Int) extends Serializable