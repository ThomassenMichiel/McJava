package be.mcjava.dao;

import be.mcjava.model.AbstractOrderItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDao {
    public static void saveOrderItems(List<AbstractOrderItem> itemsToOrder) {
        String sql = "insert into order_item (product_id,amount,total_price,finished) values ?,?,?,?";
        try(PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql)) {
            for (AbstractOrderItem abstractOrderItem : itemsToOrder) {
                preparedStatement.setLong(1,abstractOrderItem.getId());
                preparedStatement.setInt(2,abstractOrderItem.getAmount());
                preparedStatement.setBigDecimal(3,abstractOrderItem.getPrice());
                preparedStatement.setBoolean(4,false);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
