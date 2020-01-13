package com.robinliew.flink.day01

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.util.Collector

class LineSplitter extends FlatMapFunction[String, (String, Integer)]{

  override def flatMap(t: String, collector: Collector[(String, Integer)]): Unit = {
    val tokens = t.toLowerCase.split("\\W+")
    for (token <- tokens) {
      if (token.length > 0) collector.collect((token, 1))
    }
  }

}
