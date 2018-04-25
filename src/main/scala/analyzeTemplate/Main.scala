package analyzeTemplate

import java.io.{File, FileInputStream}
import java.nio.charset.Charset
import java.util
import java.util.{ArrayList, List}

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Main {


  def main(args: Array[String]): Unit = {
    val list2 = new ListBuffer[String]()
    val list4 = new ListBuffer[String]()
    val list8 = new ListBuffer[Double]()
    val myFile = new File(s"${getClass
      .getResource("/analyzeTemplate")
      .getPath}/171201_Ops_Webinar_-_data.xlsx")
    val fis = new FileInputStream(myFile); // Finds the workbook instance for XLSX file XSSFWorkbook myWorkBook = new XSSFWorkbook (fis); // Return first sheet from the XLSX workbook XSSFSheet mySheet = myWorkBook.getSheetAt(0); // Get iterator to all the rows in current sheet Iterator<Row> rowIterator = mySheet.iterator(); // Traversing over each row of XLSX file while (rowIterator.hasNext()) { Row row = rowIterator.next(); // For each row, iterate through each columns Iterator<Cell> cellIterator = row.cellIterator(); while (cellIterator.hasNext()) { Cell cell = cellIterator.next(); switch (cell.getCellType()) { case Cell.CELL_TYPE_STRING: System.out.print(cell.getStringCellValue() + "\t"); break; case Cell.CELL_TYPE_NUMERIC: System.out.print(cell.getNumericCellValue() + "\t"); break; case Cell.CELL_TYPE_BOOLEAN: System.out.print(cell.getBooleanCellValue() + "\t"); break; default : } } System.out.println(""); }
    val myWorkBook = new XSSFWorkbook (fis)
    val mySheet = myWorkBook.getSheetAt(0)
    val rowIterator = mySheet.iterator()
    while (rowIterator.hasNext) {
      val row = rowIterator.next()
      val cellIterator = row.cellIterator()
      var count = 0
      while(cellIterator.hasNext){
        count = count + 1
        val cell = cellIterator.next()
        cell.getCellType match {
          case Cell.CELL_TYPE_STRING => {
            if (count%2 == 0 && count%4 != 0) list2 += cell.getStringCellValue
            if (count%4 == 0) list4 += cell.getStringCellValue
            if (count == 9) count = 0
          }
          case Cell.CELL_TYPE_NUMERIC => {
            if(count%8 == 0) list8 += cell.getNumericCellValue
            if (count == 9) count = 0
          }

          //case Cell.CELL_TYPE_BOOLEAN => print(cell.getBooleanCellValue + "\n")
        }

      }

    }
    var time_roof_bolter_oper_delay: Double = 0
    var time_roof_bolter_prod_time: Double = 0
    var time_roof_standby: Double = 0
    var time_unscheduled_repair: Double = 0
    var time_LHD_oper_delay: Double = 0
    var time_LHD_prod_time: Double = 0
    var time_LHD_standby: Double = 0
    var time_LHD_unscheduled_repair: Double = 0
    var time_jumbo_oper_delay: Double = 0
    var time_jumbo_prod_time: Double = 0
    var time_jumbo_standby: Double = 0
    var time_jumbo_unscheduled_repair: Double = 0
    for (i <- 0 to 30782) {
      if (list2(i).equals("Roof bolter")) {
        if (list4(i).equals("Operating Delay"))
          time_roof_bolter_oper_delay = time_roof_bolter_oper_delay + list8(i)
        if (list4(i).equals("Production Time"))
          time_roof_bolter_prod_time = time_roof_bolter_prod_time + list8(i)
        if (list4(i).equals("Standby"))
          time_roof_standby = time_roof_standby + list8(i)
        if (list4(i).equals("Unscheduled Repair"))
          time_unscheduled_repair = time_unscheduled_repair + list8(i)
      }

      if (list2(i).equals("LHD")) {
        if (list4(i).equals("Operating Delay"))
          time_LHD_oper_delay = time_LHD_oper_delay + list8(i)
        if (list4(i).equals("Production Time"))
          time_LHD_prod_time = time_LHD_prod_time + list8(i)
        if (list4(i).equals("Standby"))
          time_LHD_standby = time_LHD_standby + list8(i)
        if (list4(i).equals("Unscheduled Repair"))
          time_LHD_unscheduled_repair = time_LHD_unscheduled_repair + list8(i)

      }

      if (list2(i).equals("Jumbo drill")) {
        if (list4(i).equals("Operating Delay"))
          time_jumbo_oper_delay =time_jumbo_oper_delay + list8(i)
        if (list4(i).equals("Production Time"))
          time_jumbo_prod_time = time_jumbo_prod_time + list8(i)
        if (list4(i).equals("Standby"))
          time_jumbo_standby = time_jumbo_standby + list8(i)
        if (list4(i).equals("Unscheduled Repair"))
          time_jumbo_unscheduled_repair = time_jumbo_unscheduled_repair + list8(i)
      }

    }
    println("name time                   " + "value")
    print("time_roof_bolter_oper_delay   " + time_roof_bolter_oper_delay/(60*60) + "\n")
    print("time_roof_bolter_prod_time    " + time_roof_bolter_prod_time/(60*60) + "\n")
    print("time_roof_standby             " + time_roof_standby/(60*60) + "\n")
    print("time_unscheduled_repair       " + time_unscheduled_repair/(60*60) + "\n")
    print("time_LHD_oper_delay           " + time_LHD_oper_delay/(60*60) + "\n")
    print("time_LHD_prod_time            " + time_LHD_prod_time/(60*60) + "\n")
    print("time_LHD_standby              " + time_LHD_standby/(60*60) + "\n")
    print("time_LHD_unscheduled_repair   " + time_LHD_unscheduled_repair/(60*60) + "\n")
    print(" time_jumbo_oper_delay        " +  time_jumbo_oper_delay/(60*60) + "\n")
    print("time_jumbo_prod_time          " + time_jumbo_prod_time/(60*60) + "\n")
    print("time_jumbo_standby            " + time_jumbo_standby/(60*60) + "\n")
    print("time_jumbo_unscheduled_repair " + time_jumbo_unscheduled_repair/(60*60) + "\n")

    //println(list2.toList(30784))
    //println(list4.toList)
    //println(list8.toList)



  }

}
