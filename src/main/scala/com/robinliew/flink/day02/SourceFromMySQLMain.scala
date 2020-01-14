package com.robinliew.flink.day02

import com.robinliew.flink.day02.SourceFromMySQL
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object SourceFromMySQLMain {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    env.addSource(new SourceFromMySQL).print

    env.execute("Flink add data sourc")
  }

}
