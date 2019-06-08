package be.mcjava.service;

import be.mcjava.model.Ingredient;
import be.mcjava.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientService {

    /***
     * returns a Map<Ingredient,Integer> of all ingredients from a list of products
     * @param allProductsInACustomerOrder
     * @return
     */
    public static Map<Ingredient, Integer> getAllUsedIngredients(List<Product> allProductsInACustomerOrder) {
        Map<Ingredient, Integer> neededIngredientsAmountMap = new HashMap<>();
        for (Product product : allProductsInACustomerOrder) {
            Map<Ingredient, Integer> productIngredientsMap = new HashMap<>();
            productIngredientsMap = product.getIngredients();
            for (Ingredient ingredient : productIngredientsMap.keySet()) {
                if(neededIngredientsAmountMap.containsKey(ingredient)){
                    Integer amountInMap = neededIngredientsAmountMap.get(ingredient);
                    amountInMap += productIngredientsMap.get(ingredient);
                    neededIngredientsAmountMap.put(ingredient,amountInMap);
                }else {
                    neededIngredientsAmountMap.put(ingredient,productIngredientsMap.get(ingredient));
                }
            }
        }
        return neededIngredientsAmountMap;
    }
}
