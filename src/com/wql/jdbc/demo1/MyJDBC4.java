package com.wql.jdbc.demo1;
import com.wql.jdbc.domain.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyJDBC4 {

    public static void main(String[] args) {

        List<Account> list = new MyJDBC4().show();
        for (Account account : list) {
            System.out.println(account);
        }
    }

    /**
     * 讲对象信息存入List集合中，JDBC查询获取集合数据查询数据库信息
                * @return
     */
        public List<Account> show(){

        List<Account> list = new ArrayList<Account>();
        Account account = new Account();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
        //1.获取数据库连接
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接对象
            connection = DriverManager.getConnection("jdbc:mysql:///wql","root","123456");

            //3.编写sql语句
            String sql = "select * from account";
            //4.获取Statement对象
            statement = connection.createStatement();
            //5.获取查询对象
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double money = resultSet.getDouble("money");
                account.setId(id);
                account.setName(name);
                account.setMoney(money);
                list.add(account);
                account.toString();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }
}
