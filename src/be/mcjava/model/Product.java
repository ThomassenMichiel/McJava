package be.mcjava.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Contains all the data related to a single product (e.g. BigMac
 */
public class Product {
    private Long id;
    private Map<Ingredient, Integer> ingredients;
    private BigDecimal price;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(Map<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public boolean isAvailable() {
        //todo: implement this, returns false so code will compile -Michiel 24/5/2019
        return false;
    }
}
