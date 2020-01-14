package com.robinliew.flink.day02

import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

/**
  * 自定义Source
  * SourceFunction 接口它是所有 stream source 的根接口，它继承自一个标记接口（空接口）Function。
  * 1、run ： 启动一个 source，即对接一个外部数据源然后 emit 元素形成 stream（大部分情况下会通过在该方法里运行一个 while 循环的形式来产生 stream）。
  *
  * 2、cancel ： 取消一个 source，也即将 run 中的循环 emit 元素的行为终止。
  *
  * 正常情况下，一个 SourceFunction 实现这两个接口方法就可以了。其实这两个接口方法也固定了一种实现模板。
  *
  * 比如，实现一个 XXXSourceFunction，那么大致的模板是这样的：
  */
object HBaseWriteStreamDemo {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream = env.addSource(new SourceFunction[String](){
      @SerialVersionUID(1L)
      @volatile var isRunning = true

      override def run(sourceContext: SourceFunction.SourceContext[String]): Unit = {
        while(isRunning){
          sourceContext.collect(String.valueOf(Math.random() * 100))
        }
      }

      override def cancel(): Unit = false
    })
    dataStream.writeAsText("F:\\flink\\output")

    env.execute()
  }

}
