package com.wql.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *测试添加数据
 */
public class MyJDBC2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement sta = null;
        try {
            //1.获取mysql驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.通过DriverManager获取连接对象
            conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/wql","root","123456");

            //3.定义DML语句使用executeUpdate方法
            String sql = "insert into account(name,money)values('吴清丽',1000)";

            //4.通过conn对象获取Statement对象
            sta = conn.createStatement();

            //5.执行excuteUpdate（用于DML语句）方法
            //返回的int型为：记录的行数，当行数大于0则表示sql语句执行成功
            int count = sta.executeUpdate(sql);
            System.out.println(count);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            //当sta不为空，则释放
            if(sta!=null){
                try {
                    sta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
