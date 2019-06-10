package be.mcjava.dao;

import be.mcjava.model.PreMadeOrderMenu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreMadeOrderMenuDao {
    
    public List<PreMadeOrderMenu> populatePreMadeOrderMenuByIdRange(int fromId, int toId) throws SQLException {
        String sql = "select * from premade_menu where id between ? and ?";
        List<PreMadeOrderMenu> preMadeOrderMenuList = new ArrayList<>();
        try (Connection connection = DaoConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, fromId);
            preparedStatement.setInt(2, toId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    PreMadeOrderMenu preMadeOrderMenu = new PreMadeOrderMenu.Builder()
                            .withName(resultSet.getString("name"))
                            .withPrice(resultSet.getBigDecimal("price"))
                            .withAmount(1)
                            .withPictureName(resultSet.getString("graphic_name"))
                            .build();
                    preMadeOrderMenuList.add(preMadeOrderMenu);
                }
                return preMadeOrderMenuList;
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public PreMadeOrderMenu findMenuByName(String menuName) {
        String sql = "select * from premade_menu where name like ? LIMIT 1";
        try (Connection connection = DaoConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, menuName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return new PreMadeOrderMenu.Builder()
                            .withName(resultSet.getString("name"))
                            .withPrice(resultSet.getBigDecimal("price"))
                            .withPictureName(resultSet.getString("graphic_name"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
