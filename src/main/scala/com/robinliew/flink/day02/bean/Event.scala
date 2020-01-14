package com.robinliew.flink.day02.bean

class Event {

  var id: Int = _
  var name: String = _
  var price: Double = _

  def this(id: Int,name: String,price: Double){
    this
    this.id = id
    this.name = name
    this.price = price
  }

}
