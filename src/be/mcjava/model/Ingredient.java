package be.mcjava.model;

/**
 * Contains the data for all the ingredients
 */
public class Ingredient {
    private Long id;
    private String name;
    private int currentStock;
    
    public Ingredient(String name, int currentStock) {
        this(null,name,currentStock);
    }
    
    public Ingredient(Long id, String name, int currentStock) {
        setId(id);
        setName(name);
        setCurrentStock(currentStock);
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
    
    public int getCurrentStock() {
        return currentStock;
    }
    
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
    
    public boolean isAvailable() {
        return currentStock > 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Ingredient that = (Ingredient) o;
    
        return getName().equals(that.getName());
    }
    
    @Override
    public int hashCode() {
        return 31 * getName().hashCode();
    }
}
