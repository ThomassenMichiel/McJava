package be.mcjava.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Contains all the data related to a single product (e.g. BigMac).
 * <p>
 * This class represents the default value for a product, which can later be changed.
 */
public class Product {
    private Long id;
    private String name;
    private Map<Ingredient, Integer> ingredients;
    private BigDecimal price;
    
    private Product(){}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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
        return ingredients.entrySet()
                .stream()
                .allMatch(this::availableIngredients);
    }
    
    private boolean availableIngredients(Map.Entry<Ingredient, Integer> entry) {
        return entry.getKey().getCurrentStock() >= entry.getValue();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Product product = (Product) o;
        
        return getIngredients().equals(product.getIngredients());
    }
    
    @Override
    public int hashCode() {
        return 29 * ingredients.hashCode();
    }
    
    public static class Builder {
        private Long id;
        private String name;
        private Map<Ingredient, Integer> ingredients;
        private BigDecimal price;
        
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withIngredients(Map<Ingredient, Integer> ingredients) {
            this.ingredients = ingredients;
            return this;
        }
        
        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }
        
        public Product build() {
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setIngredients(ingredients);
            
            return product;
        }
    }
}