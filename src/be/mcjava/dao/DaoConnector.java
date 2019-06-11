package be.mcjava.dao;

import be.mcjava.controller.ErrorController;
import be.mcjava.utils.PropertiesLoader;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import javafx.application.Platform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Objects;

public class DaoConnector {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    private DaoConnector() {
    }
    
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (CommunicationsException ce) {
            ErrorController.showError(ce, "Connection error", "Could not connect to database");
        } catch (SQLSyntaxErrorException sse) {
            ErrorController.showError(sse, "Connection Error", "Database does not exist");
        } catch (SQLException se) {
            if (se.getErrorCode() != 1045) {
                throw se;
            }
            ErrorController.showError(se, "Connection error", "Check user credentials");
        }
        Platform.exit();
        return null;
    }
    
    public static void setURL(String URL) {
        if (!URL.matches("jdbc\\:mysql:\\/\\/(\\w.+)\\/\\w+")) {
            throw new IllegalArgumentException("The JDBC URL is invalid.");
        }
        DaoConnector.URL = URL;
    }
    
    public static void setUSER(String USER) {
        DaoConnector.USER = USER;
    }
    
    public static void setPASSWORD(String PASSWORD) {
        DaoConnector.PASSWORD = PASSWORD;
    }
    
    public static void checkDbCredentials() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        try {
            final String password = propertiesLoader.getString("password");
            if (Objects.isNull(password) || password.isEmpty()) {
                throw new IllegalArgumentException("The password has not been set!");
            }
            final String jdbcUrl = propertiesLoader.getString("jdbcUrl");
            if (Objects.isNull(jdbcUrl) || jdbcUrl.isEmpty()) {
                throw new IllegalArgumentException("The url has not been set!");
            }
            final String user = propertiesLoader.getString("user");
            if (Objects.isNull(user) || user.isEmpty()) {
                throw new IllegalArgumentException("The user has not been set!");
            }
            
            DaoConnector.setPASSWORD(password);
            DaoConnector.setURL(jdbcUrl);
            DaoConnector.setUSER(user);
        } catch (IllegalArgumentException e) {
            ErrorController.showError(e,"Configuration is not set or invalid","Please check application.properties for invalid data");
            Platform.exit();
        }
    }
}
