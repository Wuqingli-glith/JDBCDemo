package com.wql.jdbc.demo1;

import java.sql.*;

public class MyJDBC3 {
    /**
     * 测试查询数据，将查询的结果作为结果集进行封装为结果集对象ResultSet
     */
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            //1.获取mysql驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取connection对象
            connection = DriverManager.getConnection
                    ("jdbc:mysql:///wql", "root", "123456");
            //3.定义sql语句
            String sql = "select * from account";

            //4.获取statement对象
            statement = connection.createStatement();

            //5.1获取查询结果集对象(DQL语言执行的方法为executeQuery)
            rs = statement.executeQuery(sql);

            //5.2将游标下移
            while (rs.next()) {
                //6.通过游标获取对应数据的数据类型
                int i = rs.getInt(1);
                String name = rs.getString("name");
                String money = rs.getString("money");
                System.out.println("账户id："+i+",账户姓名："+name+",账户金额："+money);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
