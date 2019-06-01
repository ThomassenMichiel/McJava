package be.mcjava.service;

import be.mcjava.model.Menu;
import be.mcjava.model.PreMadeOrderMenu;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private static final String url = "jdbc:mysql://192.168.99.100/test_db";
    private static final String user = "root";
    private static final String password = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public List<PreMadeOrderMenu> populateMenus() throws SQLException {
        String sql = "select * from premade_menu";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<PreMadeOrderMenu> preMadeOrderMenuListList = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    PreMadeOrderMenu preMadeOrderMenu = new PreMadeOrderMenu();
                    preMadeOrderMenu.setName(resultSet.getString("name"));
                    preMadeOrderMenu.setPrice(resultSet.getBigDecimal("price"));
                    preMadeOrderMenu.setpictureName(resultSet.getString("graphic_name"));
                    preMadeOrderMenuListList.add(preMadeOrderMenu);
                }
                return preMadeOrderMenuListList;
            }
        }
    }
}
