package com.wql.transaction;

import com.wql.jdbc.utils.CloseAll;
import com.wql.jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 演示转账，测试事务控制
 */
public class TransactionTest {

    public static void main(String[] args) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            //开启事务，在sql语句执行前
            conn.setAutoCommit(false);

            //将张三的钱-500使用占位符?
            String sql1 = "update account set money = money - ? where id = ?";
            //李四的钱+500
            String sql2 = "update account set money = money +? where id = ?";

            //获取PrepareStatement执行对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //赋值
            pstmt1.setDouble(1,500);
            pstmt1.setInt(2,1);
            pstmt2.setInt(1,500);
            pstmt2.setInt(2,2);

            pstmt1.executeUpdate();
//            int i = 3/0;
            pstmt2.executeUpdate();

            //提交事务，在sql语句完全执行完成后
            conn.commit();
        } catch (Exception e) {
            //当发生异常时，回滚事务；注意，catch应该捕获所有的异常
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            CloseAll.closeAll(conn,pstmt1,pstmt2);
        }

    }
}
