package be.mcjava.dao;

import be.mcjava.model.CustomerOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerOrderDao {

    public static void saveCustomerOrder(CustomerOrder customerOrder) {
        Long generatedKey = -1L;
        String sql = "insert into customer_order (name,telephone_number,finished_cooking,ordered) values (?,?,?,?)";
        try(PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,customerOrder.getName());
            preparedStatement.setString(2,customerOrder.getTelephoneNumber());
            preparedStatement.setBoolean(3,false);
            preparedStatement.setBoolean(4,true);
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if(resultSet.next()){
                    generatedKey = resultSet.getLong(1);
                }
            }
            OrderItemDao.saveOrderItems(customerOrder.getItemsToOrder(),generatedKey);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
