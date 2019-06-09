package be.mcjava.dao;


import be.mcjava.model.CookingOrders;
import be.mcjava.model.CustomerOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CookingOrderDao {
    private Long id;
    private List<CustomerOrder> ordersToCook = new ArrayList<>();
    private boolean finished;


/**    public ArrayList<CustomerOrder> getOrdersToCook()throws SQLException {
 String sql = "Select * From Customer";
 PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement( sql );
 ResultSet resultSet = preparedStatement.executeQuery();

 while (resultSet.next()) {
 CookingOrders cookingOrder = new CookingOrders(resultSet.getLong("id"), resultSet.getClob ("orders to cook"));
 ordersToCook.add(cookingOrder);

 return ordersToCook;
 }return null;
 }*/
}