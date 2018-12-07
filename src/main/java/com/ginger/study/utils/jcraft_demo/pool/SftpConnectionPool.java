package com.ginger.study.utils.jcraft_demo.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ginger on 18-1-11
 */
public class SftpConnectionPool {
    private int poolSize;
    private static ThreadLocal <Connection>connThreadLocal = new ThreadLocal<Connection>();  // 线程局部变量是局限于线程内部的变量

    public static Connection getConnection() {
        // ②如果connThreadLocal没有本线程对应的Connection创建一个新的Connection，
        // 并将其保存到线程本地变量中。
        if (connThreadLocal.get() == null) {
            Connection conn = getConnection();
            connThreadLocal.set(conn);
            return conn;
        } else {
            return connThreadLocal.get();
            // ③直接返回线程本地变量
        }
    }
    public void addTopic() {
        // ④从ThreadLocal中获取线程对应的Connection
        try {
            Statement stat = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
