package be.mcjava.model;


import java.util.List;

/**
 * Contains all data related to the order of a customer
 */
public class CustomerOrder {
    private Long id;
    private String name;
    private String telephoneNumber;
    private List<OrderItem> orderedItems;
    
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
    
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    
    public List<OrderItem> getOrderedItems() {
        return orderedItems;
    }
    
    public void setOrderedItems(List<OrderItem> orderedItems) {
        this.orderedItems = orderedItems;
    }
    
    public void addToOrder(OrderItem orderItem) {
    }
    
    public void removeFromOrder(OrderItem orderItem) {
    }
    
    public void editOrder(OrderItem orderItem) {
    
    }
}
