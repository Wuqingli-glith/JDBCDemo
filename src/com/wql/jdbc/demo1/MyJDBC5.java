package com.wql.jdbc.demo1;
import com.wql.jdbc.domain.Account;
import com.wql.jdbc.utils.CloseAll;
import com.wql.jdbc.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyJDBC5 {

    /**
     * 测试JDBCUtils工具类
     * @param args
     */
    public static void main(String[] args) {

        List<Account> list = new MyJDBC5().show();
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
            //2.获取连接对象
            connection = JDBCUtils.getConnection();

            //3.编写sql语句
            String sql = "select * from account";
            //4.获取Statement对象
            statement = connection.createStatement();
            //5.获取查询对象
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                account.setId(resultSet.getInt("id"));
                account.setName(resultSet.getString("name"));
                account.setMoney(resultSet.getDouble("money"));
                list.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseAll.closeAll(statement,connection);
        }
        return list;
    }
}
