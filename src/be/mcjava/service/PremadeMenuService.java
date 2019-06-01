package be.mcjava.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PremadeMenuService {
    private static final String url = "jdbc:mysql://192.168.99.100/test_db";
    private static final String user = "root";
    private static final String password = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
