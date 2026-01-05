package com.java.chat.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/chatdb";
    private static final String USER = "brochita";
    private static final String PASSWORD = "14082002";

    public static Connection getConnection() {
        try{
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(conn + "DB connection established");
            conn.setAutoCommit(true);
            return conn;
        } catch(Exception e){
            throw new RuntimeException("DB connection failed",e);
        }
    }
}
