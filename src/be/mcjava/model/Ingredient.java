package be.mcjava.model;

/**
 * Contains the data for all the ingredients
 */
public class Ingredient {
    private Long id;
    private String name;
    private int currentStock;
    
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
    
    public int getCurrentStock() {
        return currentStock;
    }
    
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
    
    public boolean isAvailable() {
        //todo: implement this, returns false so code will compile -Michiel 24/5/2019
        return false;
    }
}
