package main.scala.functionProgram

/**
  * Created by lushuai on 16-10-6.
  */
object MapFilterTest3 {
  def main(args: Array[String]) {
    val subMap = Map("version" -> "3", "hello" -> "world")
    val map = Map("version" -> 1, "query" -> "arganzheng",
      "pageNumber" -> "1", "resultPerPage" -> "10", "data" -> subMap)

    println(map)

    val resultMap = visit(map, (key, value) => {
      !key.equalsIgnoreCase("version")
    })

    println(resultMap)
  }

  def visit(tree: Map[String,Any],accept:(String,Any)=>Boolean): Unit ={
    tree.filter { case (k, v) => accept(k, v) }
      .map {
        case (k, map: Map[String, Any]) => (k, visit(map, accept))
        case (k, v) => (k, v)
      }
  }
}
