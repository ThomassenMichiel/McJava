package be.mcjava.dao;

import be.mcjava.model.Product;

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
        try (PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql)) {
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
}
