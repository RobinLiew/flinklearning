package com.robinliew.flink.day02


import com.robinliew.flink.day02.bean.Event
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

/**
  * 基于集合
  * 1、fromCollection(Collection) - 从 Java 的 Java.util.Collection 创建数据流。集合中的所有元素类型必须相同。
  * 2、fromCollection(Iterator, Class) - 从一个迭代器中创建数据流。Class 指定了该迭代器返回元素的类型。
  * 3、fromElements(T …) - 从给定的对象序列中创建数据流。所有对象类型必须相同。
  * 4、fromParallelCollection(SplittableIterator, Class) - 从一个迭代器中创建并行数据流。Class 指定了该迭代器返回元素的类型。
  * 5、generateSequence(from, to) - 创建一个生成指定区间范围内的数字序列的并行数据流。
  */
object DataSourceDemo {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val elements = env.fromElements(
      new Event(1, "barfoo", 1.0),
      new Event(2, "start", 2.0),
      new Event(3, "foobar", 3.0)
    )
    elements.print()

    /**
      * 从文件中读取
      */
    val text = env.readTextFile("F:\\flink\\source.txt")
    text.print()

    env.execute("Data Source")

  }

}
