package be.mcjava.model;

import java.math.BigDecimal;
import java.util.List;

public class PreMadeOrderMenu extends AbstractOrderItem<List<SingleOrderItem>>  {
    private BigDecimal menuPrice;
    
    
    @Override
    public BigDecimal getPrice() {
        return menuPrice;
    }
    
    @Override
    public void setPrice(BigDecimal price) {
        this.menuPrice = price;
    }
    
    @Override
    protected void recalculateTotalPrice(List<SingleOrderItem> singleOrderItems, int amount) {
        setPrice(menuPrice.multiply(new BigDecimal(amount)));
    }
    
    
    
}
