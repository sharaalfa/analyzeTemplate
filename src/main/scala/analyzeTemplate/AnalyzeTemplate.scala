package analyzeTemplate

import org.apache.spark.rdd.RDD

class AnalyzeTemplate extends Serializable {
  def rawJournal(lines: RDD[String]): RDD[Journal] =
    lines.map(line => {
      val arr = line.split(",")
      Journal(time = if (arr(0) == "") None else Some(arr(0).intern()), srcUser = if (arr(2) == "") None else Some(arr(2)
        .intern()), srcIp = if (arr(3) == "") None else Some(arr(3).intern()), srcPort = arr(3).toInt,
        destUser = if (arr(4) == "") None else Some(arr(4).intern()), destIp = if (arr(5) == "") None else Some(arr(5)
          .intern()), destPort = arr(6).toInt, inputByte = arr(7).toInt, outputByte = arr(8).toInt)
    })

  def scoreRequests(journal: RDD[Journal]): Unit = journal.map(x => (x, x.inputByte)).collect().toSeq.sortWith(_._2 > _
    ._2).take(5).foreach(println)
  def scoreData(journal: RDD[Journal]) = journal.map(x => (x, x.outputByte)).collect().toSeq.sortWith(_._2 > _._2)
    .take(5).foreach(println)
  def regulateRequests() = 0



}
