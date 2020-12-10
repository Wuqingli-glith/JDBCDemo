package com.wql.login;

import com.wql.jdbc.utils.CloseAll;
import com.wql.jdbc.utils.JDBCUtils2;
import com.wql.login.domain.User;
import java.sql.*;
import java.util.Scanner;

/**
 * 验证用户登录是否成功，若数据库中存在数据，则登录成功，反之失败！
 */
public class Login {

    /**
     * 返回值类型使用user对象接收，用于后续的数据库数据操作
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = input.next();
        System.out.println("请输入密码：");
        String password = input.next();

        User user = login(username,password);
        if(user!=null){
            System.out.println("登录成功！");
        }else{
            System.out.println("用户名或密码错误！");
        }

    }

    private static User login(String username, String password) {
        //1.获取连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        try {
            conn = JDBCUtils2.getConnection();
            //获取执行对象PrepareStatement
            pstmt = conn.prepareStatement("select * from user where username =? and password =?");

            //给定占位符赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            result = pstmt.executeQuery();

            if(result.next()){
                int id = result.getInt("id");
                String u1 = result.getString("username");
                String p1 = result.getString("password");
                return new User(id,u1,p1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseAll.closeAll(result,pstmt,conn);
        }
        return null;
    }
}
