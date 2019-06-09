package be.mcjava.model;

import java.math.BigDecimal;

public abstract class AbstractOrderItem<T> {
    private Long id;
    private T product;
    private int amount;
    private BigDecimal price;
    private boolean finished;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public T getItems() {
        return product;
    }
    
    
    public void setItems(T t) {
        this.product = t;
        recalculateTotalPrice(product,amount);
    }
    
    
    public BigDecimal getPrice() {
        return price;
    }
    
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    
    public int getAmount() {
        return amount;
    }
    
    
    public void setAmount(int amount) {
        this.amount = amount;
        recalculateTotalPrice(product,amount);
    }
    
    
    public boolean isFinished() {
        return finished;
    }
    
    
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    
    protected abstract void recalculateTotalPrice(T t, int amount);
    
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractOrderItem)) return false;
        
        AbstractOrderItem<?> that = (AbstractOrderItem<?>) o;
        
        if (!product.equals(that.product)) return false;
        return getPrice().equals(that.getPrice());
    }
    
    
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
