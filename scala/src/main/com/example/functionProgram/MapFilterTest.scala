package com.example.functionProgram

/**
  *
  * 非函数式的写法
  * 输入一个Map[String, Any]，要求过滤掉这个map中所有key="version"的元素。

  * Created by lushuai on 16-10-6.
  */
object MapFilterTest {
  def main(args: Array[String]) {
    val subMap =Map("version"->"3","hello"->"world")
    val map=Map("version"->1,"query"->"zhangsan","pageNum"->"1","resulPerPage"->"10","data"->subMap)

    printf("原始集合: %s",map)
    println()
    printf("过滤后的集合: %s",map.filter(_._1 != "version"))  //输出结果子集合的数据没有过滤掉
  }
}
