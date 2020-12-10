package com.wql.jdbc.utils;


import java.io.Closeable;
import java.io.IOException;

public class CloseAll {
    /**
     * 关闭资源
     */
    public static void closeAll(AutoCloseable... ca) {
        for (AutoCloseable closeable : ca) {
            if (ca != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
