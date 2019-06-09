package be.mcjava.dao;

import be.mcjava.model.AbstractOrderItem;
import be.mcjava.model.CustomerOrder;
import be.mcjava.model.Product;
import be.mcjava.model.SingleOrderItem;
import be.mcjava.service.CustomerOrderService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerOrderDao {

    public static void saveCustomerOrder(CustomerOrder customerOrder) {
        Long generatedKey = -1L;
        List<SingleOrderItem> singleOrderItemList;
        String sql = "insert into customer_order (name,telephone_number,finished_cooking,ordered) values (?,?,?,?)";
        try (PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customerOrder.getName());
            preparedStatement.setString(2, customerOrder.getTelephoneNumber());
            preparedStatement.setBoolean(3, false);
            preparedStatement.setBoolean(4, true);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    generatedKey = resultSet.getLong(1);
                    CustomerOrderService.customerOrder.setId(generatedKey);
                }
            }
            List<AbstractOrderItem> abstractOrderItemList = customerOrder.getItemsToOrder();
            for (AbstractOrderItem abstractOrderItem : abstractOrderItemList) {
                if (abstractOrderItem instanceof SingleOrderItem){
                    singleOrderItemList = new ArrayList<>();
                    singleOrderItemList.add((SingleOrderItem) abstractOrderItem);
                }else {
                    singleOrderItemList = (List<SingleOrderItem>) abstractOrderItem.getItems();
                }
                OrderItemDao.saveOrderItems(singleOrderItemList,generatedKey);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<AbstractOrderItem> getOrderSummary() {
        String sql = "select p.name, count(p.name) as count, p.id as productId, p.price as price, sum(p.price) as total\n" +
                "from customer_order co\n" +
                "       join customer_order_items coi on co.id = coi.customer_order_id\n" +
                "       join order_item o on coi.order_item_id = o.id\n" +
                "       join product p on o.product_id = p.id\n" +
                "group by o.product_id\n";
        try (PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<AbstractOrderItem> results = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product.Builder(resultSet.getString("name"))
                        .withId(resultSet.getLong("productId"))
                        .withPrice(resultSet.getBigDecimal("price"))
                        .build();
                SingleOrderItem singleOrderItem = new SingleOrderItem.Builder(product)
                        .withAmount(resultSet.getInt("count"))
                        .withPrice(resultSet.getBigDecimal("total"))
                        .build();
                results.add(singleOrderItem);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
