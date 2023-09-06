package com.forq.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectionProvider {
    @Getter private static final DBConnectionProvider instance = new DBConnectionProvider();
    private Connection connection;

    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/forq?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "root";

    private DBConnectionProvider() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            try {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return connection;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
