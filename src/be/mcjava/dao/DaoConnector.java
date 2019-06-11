package be.mcjava.dao;

import be.mcjava.controller.ErrorController;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.application.Platform;

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
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (CommunicationsException ce) {
            ErrorController.showError(ce,"Connection error","Could not connect to database");
            Platform.exit();
        } catch (SQLException se) {
            if (se.getErrorCode() == 1045) {
                ErrorController.showError(se,"Connection error","Check user credentials");
                Platform.exit();
            }
            throw se;
        }
        return null;
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
