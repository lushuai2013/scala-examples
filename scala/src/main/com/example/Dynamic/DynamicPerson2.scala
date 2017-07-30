package com.example.Dynamic

import scala.collection.mutable
import scala.language.dynamics

/**
  * Created by lushuai on 16-10-6.
  */
class DynamicPerson2  extends Dynamic {

  //定义一个方法类型 CallFun，它接收 Int 类型参数，并返回 String 类型 ，这个有点像 C# 中的 delegate
  type CallFun = Int => String
  //Map对象，存放属性
  private val fields = mutable.HashMap.empty[String, Any].withDefault { key => throw new NoSuchFieldError(key) }
  //Map对象，存放方法对象
  private val functions = mutable.HashMap.empty[String, CallFun].withDefault { key => throw new NoSuchFieldError(key) }

  //选取对象
  def selectDynamic(key: String) = fields(key)

  /**
    * 更新key对应的value
    * 这里做了一个判断，如果key以call字符串开头，我们认为是args是CallFun类型
    */
  def updateDynamic(key: String)(args: Any): Unit = {
    args match {
      case x if key.startsWith("call") => functions(key) = x.asInstanceOf[CallFun]
      case _ => fields(key) = args
    }
  }

  //这个就是用来动态执行方法的
  def applyDynamic(key: String)(arg:Int) = {
    println(functions(key)(arg))
  }

}



object DynamicPersonExample2 extends  App{
  val person = new DynamicPerson2()
  //设置Name属性
  person.Name = "Mike"
  //定义一个 call 方法
  person.call = (age:Int) => s"${person.Name} 今年 $age 岁"
  //通过 applyDynamic 执行 call 方法
  person.call(80)  //这句代码将打印出："Mike 今年 80 岁"
}


