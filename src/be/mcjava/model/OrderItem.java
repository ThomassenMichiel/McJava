package be.mcjava.model;

import java.math.BigDecimal;

/**
 * Contains all the data related to a single ordered item on the CustomerOrder
 */
public class OrderItem {
    private Long id;
    private Product product;
    private int amount;
    private BigDecimal totalPrice; //todo: adjust this when changing amount and/or product -Michiel 24/5/2019
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
