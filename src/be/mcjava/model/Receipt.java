package be.mcjava.model;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {
    private Long id;
    private String name;
    private String telephoneNumber;
    private List<OrderItem> orderedItems;
    private BigDecimal orderPrice;
    
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
    
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }
    
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
