package com.wql.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 测试jdbc连接mysql数据库
 */
public class MyJDBC {

    public static void main(String[] args) throws Exception {
        //1.获取jdbc驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.通过DriverManager获取mysql连接
        Connection conn = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/wql","root","123456");

        //3.定义sql语句
        String sql = "insert into t_stu(name,sex,t_class)values('吴清丽03号','女','JavaWeb')";

        //4.获取执行sql语句的对象
        Statement statement = conn.createStatement();
        //5.执行sql语句
        boolean ex = statement.execute(sql);
        System.out.println(ex);

        //关闭资源
        conn.close();
        statement.close();
    }
}
