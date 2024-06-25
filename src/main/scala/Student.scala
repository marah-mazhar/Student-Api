import requests._
import ujson._
import java.io._

object Student extends App {
  val url = "https://freetestapi.com/api/v1/students"
  val response = requests.get(url)
  val studentsData = ujson.read(response.text())

  // Pretty print the JSON data
  val prettyJson = ujson.write(studentsData, indent = 4)
  println(prettyJson)

  // Save JSON to a file
  val pw = new PrintWriter(new File("students.json"))
  pw.write(prettyJson)
  pw.close()

  // Extract and print all parameters for each student
  studentsData.arr.foreach { student =>
    println("Student Information:")
    student.obj.foreach { case (key, value) =>
      println(s"$key: $value")
    }
    println("-----")
  }
}
