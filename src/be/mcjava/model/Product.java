package be.mcjava.model;

import java.math.BigDecimal;
import java.util.HashMap;
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
    private boolean customized;
    
    private Product(){
        price = BigDecimal.ZERO;
    }
    
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
    
    public boolean isCustomized() {
        return customized;
    }
    
    public void setCustomized(boolean customized) {
        this.customized = customized;
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
        if (!(o instanceof Product)) return false;
        
        Product product = (Product) o;
        
        if (!getName().equals(product.getName())) return false;
        return getIngredients() != null ? getIngredients().equals(product.getIngredients()) : product.getIngredients() == null;
    }
    
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getIngredients() != null ? getIngredients().hashCode() : 0);
        return result;
    }
    
    public static class Builder {
        private Long id;
        private String name;
        private Map<Ingredient, Integer> ingredients;
        private BigDecimal price;
        private boolean customized;
        
        public Builder(String name) {
            this.name = name;
            this.price = BigDecimal.ZERO;
            this.ingredients = new HashMap<>();
        }
        
        public Builder withId(Long id) {
            this.id = id;
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
        
        public Builder isCustomized(boolean customized) {
            this.customized = customized;
            return this;
        }
        
        public Product build() {
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setIngredients(ingredients);
            product.setCustomized(customized);
            
            return product;
        }
    }
}