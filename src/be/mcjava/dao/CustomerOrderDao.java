package be.mcjava.dao;

import be.mcjava.model.CustomerOrder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerOrderDao {

    public static void saveCustomerOrder(CustomerOrder customerOrder) {
        String sql = "insert into customer_order (name,telephone_number,finished_cooking,order) values ?,?,?,?";
        try(PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql)){
            preparedStatement.setString(1,customerOrder.getName());
            preparedStatement.setString(2,customerOrder.getTelephoneNumber());
            preparedStatement.setBoolean(3,false);
            preparedStatement.setBoolean(4,true);
            preparedStatement.executeUpdate();
            OrderItemDao.saveOrderItems(customerOrder.getItemsToOrder());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
