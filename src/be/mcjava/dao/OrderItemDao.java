package be.mcjava.dao;

import be.mcjava.model.AbstractOrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderItemDao {
    public static void saveOrderItems(List<AbstractOrderItem> itemsToOrder,Long customerOrderGeneratedId) {
        Long generatedKey = -1L;
        String sql = "insert into order_item (product_id,amount,total_price,finished) values (?,?,?,?)";
        try(PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (AbstractOrderItem abstractOrderItem : itemsToOrder) {
                preparedStatement.setLong(1,abstractOrderItem.getId());
                preparedStatement.setInt(2,abstractOrderItem.getAmount());
                preparedStatement.setBigDecimal(3,abstractOrderItem.getPrice());
                preparedStatement.setBoolean(4,false);
                preparedStatement.executeUpdate();
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if(resultSet.next()){
                        generatedKey = resultSet.getLong(1);
                    }
                }
                String sqlInsertCustomerOrderITems =
                        "insert into customer_order_items values (?,?)";
                try(PreparedStatement customerOrderItemsPreparedStatement = DaoConnector.getConnection().prepareStatement(sqlInsertCustomerOrderITems)){
                    customerOrderItemsPreparedStatement.setInt(1,customerOrderGeneratedId.intValue());
                    customerOrderItemsPreparedStatement.setInt(2,generatedKey.intValue());
                    customerOrderItemsPreparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
