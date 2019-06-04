package be.mcjava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoConnector {
    //private static final String URL = "jdbc:mysql://192.168.99.100/test_db";
    private static final String URL = "jdbc:mysql://localhost/mcjava";
    private static final String USER = "root";
    //private static final String PASSWORD = "password";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException{
            return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
