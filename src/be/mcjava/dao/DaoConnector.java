package be.mcjava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConnector {
//    private String URL = "jdbc:mysql://192.168.99.100/mcjava";
//    private static String URL = "jdbc:mysql://localhost/mcjava";
//    private static String USER = "root";
//    private String PASSWORD = "password";
//    private String PASSWORD = "mypass";
//    private static String PASSWORD = "root";
    
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    private DaoConnector() {}
    
    public static Connection getConnection() throws SQLException{
            return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    
    public static void setURL(String URL) {
        DaoConnector.URL = URL;
    }
    
    public static void setUSER(String USER) {
        DaoConnector.USER = USER;
    }
    
    public static void setPASSWORD(String PASSWORD) {
        DaoConnector.PASSWORD = PASSWORD;
    }
}
