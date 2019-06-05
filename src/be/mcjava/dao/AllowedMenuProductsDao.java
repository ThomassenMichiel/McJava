package be.mcjava.dao;

import be.mcjava.model.AllowedMenuProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllowedMenuProductsDao {
    public List<AllowedMenuProduct> getAllowedMenuProductsByPremadeMenuName(String preMadeMenuName){
        String sql = "select * from allowed_menu_products,premade_menu where allowed_menu_products.premade_menu_id = premade_menu.id and premade_menu.name = ?";
        try(PreparedStatement preparedStatement = DaoConnector.getConnection().prepareStatement(sql)){
            List<AllowedMenuProduct> allowedMenuProductList = new ArrayList<>();
            preparedStatement.setString(1,preMadeMenuName);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    AllowedMenuProduct allowedMenuProduct = new AllowedMenuProduct(
                            resultSet.getLong("premade_menu_id"),
                            resultSet.getInt("menu_item_number"),
                            resultSet.getLong("product_id")
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
