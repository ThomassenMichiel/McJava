package be.mcjava.model;

import java.util.List;

/**
 * Contains the data for the menu, where the customer can choose and order his/her food.
 */
public class Menu {
    private List<Product> products;
    
    public List<Product> getProducts() {
        return products;
    }
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
