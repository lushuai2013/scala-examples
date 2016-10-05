package main.scala.Dynamic
import scala.collection.mutable
import scala.language.dynamics
/**
  * Created by lushuai on 16-10-6.
  */
class DynamicPerson  extends Dynamic{
  private val fields =mutable.HashMap.empty[String,Any].withDefault{ key => throw new NoSuchFieldError(key) }
  // 获取key对应的value值
  def selectDynamic(key:String) = fields(key)
  //更新key对应的value值
  def updateDynamic(key: String)(args: Any): Unit ={
    fields(key) = args;
  }
}

object DynamicPersonExample extends  App{
  val person = new DynamicPerson()
  person.Age = 30
  person.Name = "Mike"
  //上面对 Age 和 Name 的赋值操作，就是通过 updateDynamic 完成的

  println("%s年龄是%d".format(person.Name,person.Age))
}
