package be.mcjava.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The model class for the end-of-day report, containing all sales and made products
 */
public class Summary {
    private List<AbstractOrderItem> madeProducts;
    private BigDecimal dailyTotal;
    
    public Summary(List<AbstractOrderItem> madeProducts) {
        this.madeProducts = madeProducts;
        this.dailyTotal = BigDecimal.ZERO;
        calculateDailyTotal();
    }
    
    public List<AbstractOrderItem> getMadeProducts() {
        return madeProducts;
    }
    
    public void setMadeProducts(List<AbstractOrderItem> madeProducts) {
        this.madeProducts = madeProducts;
    }
    
    public BigDecimal getDailyTotal() {
        return dailyTotal;
    }
    
    public void setDailyTotal(BigDecimal dailyTotal) {
        this.dailyTotal = dailyTotal;
    }
    
    public void calculateDailyTotal() {
        BigDecimal total = madeProducts.stream()
                .map(AbstractOrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setDailyTotal(total);
    }
}
