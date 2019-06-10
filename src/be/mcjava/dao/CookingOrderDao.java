package be.mcjava.dao;


import be.mcjava.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CookingOrderDao {
    
    public List<CookingOrders> getOrdersToCook() {
        String getIds = "select distinct co.id\n" +
                "from customer_order co\n" +
                "where co.ordered = true\n" +
                "  and co.finished_cooking = false;";
        String getOrders = "select co.id, p.name, p.price, o.amount, o.id as orderId, o.finished\n" +
                "from customer_order co\n" +
                "       join customer_order_items coi on co.id = coi.customer_order_id\n" +
                "       join order_item o on coi.order_item_id = o.id\n" +
                "       join product p on o.product_id = p.id\n" +
                "where co.id = ?\n" +
                "  and co.finished_cooking = false\n" +
                "order by co.id";
        
        try (Connection connection = DaoConnector.getConnection();
             PreparedStatement getIdPS = connection.prepareStatement(getIds);
             PreparedStatement getOrdersPS = connection.prepareStatement(getOrders);
             ResultSet idResultSet = getIdPS.executeQuery()) {
            List<CookingOrders> cookingOrders = new ArrayList<>();
            while (idResultSet.next()) {
                getOrdersPS.setLong(1, idResultSet.getLong("id"));
                try (ResultSet orderResultSet = getOrdersPS.executeQuery()) {
                    CookingOrders orders = new CookingOrders();
                    CustomerOrder customerOrder = new CustomerOrder.Builder().build();
                    while (orderResultSet.next()) {
                        Product product = new Product.Builder(orderResultSet.getString("name"))
                                .build();
                        SingleOrderItem singleOrderItem = new SingleOrderItem.Builder(product)
                                .withAmount(orderResultSet.getInt("amount"))
                                .withId(orderResultSet.getLong("orderId"))
                                .build();
                        singleOrderItem.setFinished(orderResultSet.getBoolean("finished"));
                        customerOrder.setId(orderResultSet.getLong("id"));
                        customerOrder.addItem(singleOrderItem);
                    }
                    orders.getOrdersToCook().add(customerOrder);
                    cookingOrders.add(orders);
                }
            }
            return cookingOrders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}