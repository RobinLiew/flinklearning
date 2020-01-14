package com.robinliew.flink.day02.bean

class Student {

  var id = 0
  var name: String = _
  var password: String = _
  var age = 0


  def this(id: Int, name: String, password: String, age: Int) {
    this()
    this.id = id
    this.name = name
    this.password = password
    this.age = age
  }

  override def toString: String = "Student{" + "id=" + id + ", name='" + name + '\'' + ", password='" + password + '\'' + ", age=" + age + '}'

  def getId: Int = id

  def setId(id: Int): Unit = {
    this.id = id
  }

  def getName: String = name

  def setName(name: String): Unit = {
    this.name = name
  }

  def getPassword: String = password

  def setPassword(password: String): Unit = {
    this.password = password
  }

  def getAge: Int = age

  def setAge(age: Int): Unit = {
    this.age = age
  }

}
