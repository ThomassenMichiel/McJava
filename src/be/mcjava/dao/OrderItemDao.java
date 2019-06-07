package be.mcjava.dao;

import be.mcjava.model.Product;
import be.mcjava.model.SingleOrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderItemDao {
    public static void saveOrderItems(List<SingleOrderItem> singleOrderItemList, Long customerOrderGeneratedId) {
        Long generatedKey = -1L;
        String sql = "insert into order_item (product_id,amount,total_price,finished) values (?,?,?,?)";
        try (PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (SingleOrderItem singleOrderItem : singleOrderItemList) {
                Product product = singleOrderItem.getItems();
                preparedStatement.setLong(1, product.getId());
                preparedStatement.setInt(2, singleOrderItem.getAmount());
                preparedStatement.setBigDecimal(3, product.getPrice());
                preparedStatement.setBoolean(4, false);
                preparedStatement.executeUpdate();
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        generatedKey = resultSet.getLong(1);
                    }
                }
                String sqlInsertCustomerOrderITems =
                        "insert into customer_order_items values (?,?)";
                try (PreparedStatement customerOrderItemsPreparedStatement = DaoConnector.getConnection().prepareStatement(sqlInsertCustomerOrderITems)) {
                    customerOrderItemsPreparedStatement.setInt(1, customerOrderGeneratedId.intValue());
                    customerOrderItemsPreparedStatement.setInt(2, generatedKey.intValue());
                    customerOrderItemsPreparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
