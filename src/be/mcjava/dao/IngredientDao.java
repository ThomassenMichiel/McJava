package be.mcjava.dao;

import be.mcjava.model.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class IngredientDao {
    public static void removeIngredientsFromStock(Map<Ingredient, Integer> ingredientsUsedInACustomerOrder) {
        String sql = "update ingredient set current_stock = current_stock - ? where id = ?";
        try (Connection connection = DaoConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Ingredient ingredient : ingredientsUsedInACustomerOrder.keySet()) {
                preparedStatement.setInt(1, ingredientsUsedInACustomerOrder.get(ingredient));
                preparedStatement.setInt(2, ingredient.getId().intValue());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Map<Ingredient, Integer> getIngredientsByProductId(Long productId) {
        String sql = "select * from ingredient left join product_ingredient on ingredient.id = product_ingredient.ingredient_id left join product on product.id = product_ingredient.product_id where product.id = ?";
        try (Connection connection = DaoConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Map<Ingredient, Integer> ingredientIntegerMap = new HashMap<>();
                while (resultSet.next()) {
                    Ingredient ingredient = new Ingredient(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("current_stock")
                    );
                    String sqlAmountPerIngredient = "select amount from product_ingredient where product_id = ? and ingredient_id = ?";
                    int amount = 0;
                    try (PreparedStatement ps = connection.prepareStatement(sqlAmountPerIngredient)) {
                        ps.setLong(1, productId);
                        ps.setLong(2, ingredient.getId());
                        try (ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                amount = rs.getInt("amount");
                            }
                        }
                    }
                    ingredientIntegerMap.put(ingredient, amount);
                }
                return ingredientIntegerMap;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
