package com.wql.jdbc.utils;

import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取数据库连接工具类
 */
public class JDBCUtils2 {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    /**
     * 静态代码块在类加载时执行一次
     */
    static {
        Properties pro = new Properties();
        URL resource = JDBCUtils2.class.getClassLoader().getResource("jdbcutils.properties");
        String path = resource.getPath();
        try {
            pro.load(new FileReader(path));
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return:返回connection连接对象
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
