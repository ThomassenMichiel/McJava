package be.mcjava.dao;

import be.mcjava.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao {
    private List<Product> productList = new ArrayList<>();
    
    public List<Product> getProductsByPremadeMenuTitle(String premadeMenuTitle) {
        IngredientDao ingredientDao = new IngredientDao();
        String sql = "select * from product \n" +
                "join allowed_menu_products on product.id = allowed_menu_products.product_id\n" +
                "join premade_menu on allowed_menu_products.premade_menu_id = premade_menu.id\n" +
                "where premade_menu.name = ? order by allowed_menu_products.menu_item_number";
        try (Connection connection = DaoConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, premadeMenuTitle);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product.Builder(resultSet.getString("name"))
                            .withId(resultSet.getLong("id"))
                            .withPrice(resultSet.getBigDecimal("price"))
                            .withIngredients(ingredientDao.getIngredientsByProductId(resultSet.getLong("id")))
                            .build();
                    productList.add(product);
                }
                return productList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Product> getProductsByName(String productName) {
        IngredientDao ingredientDao = new IngredientDao();
        String sql = "select * from product where name like ?";
        try (Connection connection = DaoConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, productName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = new Product.Builder(resultSet.getString("name"))
                            .withId(resultSet.getLong("id"))
                            .withPrice(resultSet.getBigDecimal("price"))
                            .withIngredients(ingredientDao.getIngredientsByProductId(resultSet.getLong("id")))
                            .build();
                    productList.add(product);
                }
                return productList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getProductNameByProductId(Long productId) {
        String sql = "select product.name from product where id = ?";
        try (Connection connection = DaoConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, productId);
            ;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
