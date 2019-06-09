package be.mcjava.service;

import be.mcjava.dao.IngredientDao;
import be.mcjava.dao.ProductsDao;
import be.mcjava.model.Ingredient;
import be.mcjava.model.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static List<Product> getProductsListByNameList(List<String> productToOrderNamesList) {
       List<Product> productList = productToOrderNamesList.stream().map(ProductService::getProductByName).collect(Collectors.toList());
       return productList;
    }

    /***
     * removes the ingredients that are used in a product from the stock of each ingredient
     * first gets a list of all needed ingredients and amounts of them
     * then uses the IngredientDao to update the stock
     * @param allProductsInACustomerOrder
     */
    public static void removeIngredientsFromStock(List<Product> allProductsInACustomerOrder) {
        Map<Ingredient,Integer> usedIngredientsStock = IngredientService.getAllUsedIngredients(allProductsInACustomerOrder);
        IngredientDao.removeIngredientsFromStock(usedIngredientsStock);
    }

    /***
     * returns a list of all ingredients from all products in the list that are out of stock
     * @param productList
     */
    public static List<Ingredient> getOutOfStockIngredientsList(List<Product> productList) {
        Map<Ingredient,Integer> usedIngredientsStock = IngredientService.getAllUsedIngredients(productList);
        return usedIngredientsStock
                .entrySet()
                .stream()
                .filter(ingredient -> ingredient.getValue()<=0)
                .map(ingredient -> ingredient.getKey())
                .collect(Collectors.toList());
    }
}
