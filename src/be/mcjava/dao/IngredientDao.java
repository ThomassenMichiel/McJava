package be.mcjava.dao;

import be.mcjava.model.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class IngredientDao {
    public Map<Ingredient,Integer> getIngredientsByProductId(Long productId) {
        String sql = "select * from ingredient left join product_ingredient on ingredient.id = product_ingredient.ingredient_id left join product on product.id = product_ingredient.product_id where product.id = ?";
        try(PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql)){
            preparedStatement.setLong(1,productId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                Map<Ingredient,Integer> ingredientIntegerMap = new HashMap<>();
                while(resultSet.next()){
                    Ingredient ingredient = new Ingredient(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("current_stock")
                    );
                    String sql_amount = "select amount from product_ingredient where product_id = ?";
                    int amount = 0;
                    try(PreparedStatement ps = DaoConnector.getConnection().prepareStatement(sql_amount)){
                        ps.setLong(1,productId);
                        try(ResultSet rs = ps.executeQuery()){
                            while (rs.next()){
                                amount = rs.getInt("amount");
                            }
                        }
                    }
                    ingredientIntegerMap.put(ingredient,amount);
                }
                return ingredientIntegerMap;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
