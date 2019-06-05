package be.mcjava.dao;

import be.mcjava.model.AllowedMenuProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllowedMenuProductsDao {
    public List<AllowedMenuProduct> getAllowedMenuProductsByPremadeMenuName(String preMadeMenuName){
        String sql = "select premade_menu.name,allowed_menu_products.menu_item_number,product.name as product_name\n" +
                "from allowed_menu_products\n" +
                "left join premade_menu on premade_menu.id = allowed_menu_products.premade_menu_id\n" +
                "left join product on product.id = allowed_menu_products.product_id\n" +
                "where premade_menu.name = ?;";
        try(PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql)){
            List<AllowedMenuProduct> allowedMenuProductList = new ArrayList<>();
            preparedStatement.setString(1,preMadeMenuName);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    AllowedMenuProduct allowedMenuProduct = new AllowedMenuProduct(
                            resultSet.getString("name"),
                            resultSet.getInt("menu_item_order"),
                            resultSet.getString("product_name")
                    );
                    allowedMenuProductList.add(allowedMenuProduct);
                }
                return allowedMenuProductList;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
