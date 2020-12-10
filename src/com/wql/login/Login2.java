package com.wql.login;

import com.wql.jdbc.utils.CloseAll;
import com.wql.jdbc.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 使用PrepareStatement对象操作sql语句，防止sql注入
 */
public class Login2 {
    /**
     * 测试连接
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login2 l2 = new Login2();
        System.out.println("请输入你的账号：");
        String username = input.next();
        System.out.println("请输入你的密码：");
        String password = input.next();
        boolean login = l2.login(username, password);
        if(login){
            System.out.println("登录成功！");
        }else{
            System.out.println("账号或密码错误！");
        }
    }
    public boolean login(String username, String password) {
        //1.获取数据库驱动
        //2.获取连接对象
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //3.sql语句
            String sql = "select * from user where username = ? and password = ?";
            //4.获取PrepareStatement对象
            preparedStatement = connection.prepareStatement(sql);

            //4.1给占位符进行赋值
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //5.执行sql语句
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            CloseAll.closeAll(resultSet,preparedStatement,connection);
        }
        return false;
    }
}
