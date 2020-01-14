package com.robinliew.flink.day02

import java.sql.{Connection, DriverManager, PreparedStatement}

import com.robinliew.flink.day02.bean.Student
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext
import org.apache.flink.streaming.api.functions.source.{RichSourceFunction, SourceFunction}


class SourceFromMySQL extends RichSourceFunction{

  var ps: PreparedStatement = _
  private var connection: Connection = _

  /**
    * open() 方法中建立连接，这样不用每次 invoke 的时候都要建立连接和释放连接。
    * @param parameters
    */
  override def open(parameters: Configuration): Unit = {
    super.open(parameters)
    connection = getConnection
    val sql = "select * from Student;"
    ps = this.connection.prepareStatement(sql)
  }

  /**
    * 程序执行完毕就可以进行，关闭连接和释放资源的动作了
    */
  override def close(): Unit = {
    super.close()
    if (connection != null) { //关闭连接和释放资源
      connection.close
    }
    if (ps != null) ps.close
  }

  /**
    * DataStream 调用一次 run() 方法用来获取数据
    * @param ctx
    */
  override def run(ctx: SourceContext[Student]) = {
    val resultSet = ps.executeQuery
    while(resultSet.next){
      val student = new Student(
        resultSet.getInt("id"),
        resultSet.getString("name").trim,
        resultSet.getString("password").trim,
        resultSet.getInt("age"))
      ctx.collect(student)
    }
  }

  override def cancel(): Unit = ???


  private def getConnection = {
    var con: Connection = null
    try {
      Class.forName("com.mysql.jdbc.Driver")
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8", "root", "root123456")
    } catch {
      case e: Exception =>
        System.out.println("-----------mysql get connection has exception , msg = " + e.getMessage)
    }
    con
  }

}
