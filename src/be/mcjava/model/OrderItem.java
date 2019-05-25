package be.mcjava.model;

import java.math.BigDecimal;

/**
 * Contains all the data related to a single ordered item on the CustomerOrder
 */
public class OrderItem {
    private Long id;
    private Product product;
    private int amount;
    private BigDecimal totalPrice;
    private boolean finished;
    
    public OrderItem(Product product, int amount) {
        this(null,product,amount);
    }
    
    public OrderItem(Long id, Product product, int amount) {
        setId(id);
        totalPrice = BigDecimal.ZERO;
        setProduct(product);
        setAmount(amount);
    }
    
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
        recalculateTotalPrice(product,amount);
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
        recalculateTotalPrice(product,amount);
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public boolean isFinished() {
        return finished;
    }
    
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    
    private void recalculateTotalPrice(Product product, int amount) {
        setTotalPrice(product.getPrice().multiply(new BigDecimal(amount)));
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        OrderItem orderItem = (OrderItem) o;
        
        return getProduct().equals(orderItem.getProduct());
    }
    
    @Override
    public int hashCode() {
        return getProduct().hashCode();
    }
}
