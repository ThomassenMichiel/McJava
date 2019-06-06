package be.mcjava.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The model class for the end-of-day report, containing all sales and made products
 */
public class Summary {
    private Map<Product, Integer> madeProducts;
    private BigDecimal dailyTotal;
    
    public Summary(Map<Product, Integer> madeProducts) {
        this.madeProducts = madeProducts;
        this.dailyTotal = BigDecimal.ZERO;
        calculateDailyTotal();
    }
    
    public Map<Product, Integer> getMadeProducts() {
        return madeProducts;
    }
    
    public void setMadeProducts(Map<Product, Integer> madeProducts) {
        this.madeProducts = madeProducts;
    }
    
    public BigDecimal getDailyTotal() {
        return dailyTotal;
    }
    
    public void setDailyTotal(BigDecimal dailyTotal) {
        this.dailyTotal = dailyTotal;
    }
    
    public void calculateDailyTotal() {
        BigDecimal total = madeProducts.entrySet()
                .stream()
                .map(this::getTotalOfSingleProduct)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setDailyTotal(total);
    }
    
    private BigDecimal getTotalOfSingleProduct(Map.Entry<Product, Integer> entry) {
        return entry.getKey()
                .getPrice()
                .multiply(new BigDecimal(entry.getValue()));
    }
}
