package be.mcjava.service;

import be.mcjava.dao.IngredientDao;
import be.mcjava.dao.ProductsDao;
import be.mcjava.model.Ingredient;
import be.mcjava.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    private static ProductsDao productsDao = new ProductsDao();

    /***
     * returns a Product object from the DB that corresponds to the given name
     * the Dao's method can return a list, in this case returns a List of size 1
     * @param chosenProductName
     * @return
     */
    public static Product getProductByName(String chosenProductName) {
        return productsDao.getProductsByName(chosenProductName).get(0);
    }

    /***
     * remove the ingredients that are used in a product from the stock of each ingredient
     * first gets a list of all needed ingredients and amounts of them
     * then uses the IngredientDao to update the stock
     * @param allProductsInACustomerOrder
     */
    public static void removeIngredientsFromStock(List<Product> allProductsInACustomerOrder) {
        Map<Ingredient,Integer> usedIngredientsStock = IngredientService.getAllUsedIngredients(allProductsInACustomerOrder);
        IngredientDao.removeIngredientsFromStock(usedIngredientsStock);
    }
}
