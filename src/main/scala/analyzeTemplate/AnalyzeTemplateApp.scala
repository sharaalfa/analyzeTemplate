package analyzeTemplate

import org.apache.spark.{SparkConf, SparkContext}




object AnalyzeTemplateApp extends AnalyzeTemplate {
  @transient
  lazy val conf: SparkConf = new SparkConf().setAppName("AnalyzeTemplate").setMaster("local[5]")
    .set("spark.executor.memory", "10g")
  @transient
  lazy val sc: SparkContext = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    val lines = sc.textFile(s"${getClass
      .getResource("/analyzeTemplate")
      .getPath}/shkib1.csv")

    val raw = rawJournal(lines)
    scoreRequests(raw)
    scoreData(raw)

    //sc.stop()

  }
}