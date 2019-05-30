package be.mcjava.model;

import java.math.BigDecimal;
import java.util.List;

public class PreMadeOrderMenu extends AbstractOrderItem<List<SingleOrderItem>>  {
    private BigDecimal price;
    private String name;
    
    
    @Override
    public BigDecimal getPrice() {
        return price;
    }
    
    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    protected void recalculateTotalPrice(List<SingleOrderItem> singleOrderItems, int amount) {
        setPrice(price.multiply(new BigDecimal(amount)));
    }
    
    
    
}
