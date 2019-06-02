package be.mcjava.model;

import java.math.BigDecimal;

public abstract class AbstractOrderItem<T> implements OrderItem<T> {
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
    
    @Override
    public T getItems() {
        return product;
    }
    
    @Override
    public void setItems(T t) {
        this.product = t;
        recalculateTotalPrice(product,amount);
    }
    
    @Override
    public BigDecimal getPrice() {
        return price;
    }
    
    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Override
    public int getAmount() {
        return amount;
    }
    
    @Override
    public void setAmount(int amount) {
        this.amount = amount;
        recalculateTotalPrice(product,amount);
    }
    
    @Override
    public boolean isFinished() {
        return finished;
    }
    
    @Override
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    
    protected abstract void recalculateTotalPrice(T t, int amount);
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractOrderItem)) return false;
        
        AbstractOrderItem<?> that = (AbstractOrderItem<?>) o;
        
        if (getAmount() != that.getAmount()) return false;
        if (!product.equals(that.product)) return false;
        return getPrice().equals(that.getPrice());
    }
    
    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + getAmount();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
