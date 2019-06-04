package be.mcjava.dao;

import be.mcjava.model.PreMadeOrderMenu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreMadeOrderMenuDao {

    public List<PreMadeOrderMenu> populatePreMadeOrderMenu() throws SQLException {
        String sql = "select * from premade_menu";
        List<PreMadeOrderMenu> preMadeOrderMenuList = new ArrayList<>();

        try (
                PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                PreMadeOrderMenu preMadeOrderMenu = new PreMadeOrderMenu.Builder()
                        .withName(resultSet.getString("name"))
                        .withPrice(resultSet.getBigDecimal("price"))
                        .withPictureName(resultSet.getString("graphic_name"))
                        .build();
                preMadeOrderMenuList.add(preMadeOrderMenu);
            }
            return preMadeOrderMenuList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
