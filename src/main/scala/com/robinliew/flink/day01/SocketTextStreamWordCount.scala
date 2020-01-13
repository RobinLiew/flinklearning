package com.robinliew.flink.day01

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object SocketTextStreamWordCount {

  def main(args: Array[String]): Unit = {
    //参数检查//参数检查

    if (args.length ne 2) {
      System.err.println("USAGE:\nSocketTextStreamWordCount <hostname> <port>")
      return
    }
    val hostname = args(0)
    val port = args(1).toInt

    // set up the streaming execution environment// set up the streaming execution environment

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //获取数据
    val stream = env.socketTextStream(hostname, port)

    implicit val typeInfo = TypeInformation.of(classOf[(String,Integer)])
    //计数
    val sum = stream.flatMap(new LineSplitter()).keyBy(0).sum(1)

    sum.print

    env.execute("Java WordCount from SocketTextStream Example")

  }

}
