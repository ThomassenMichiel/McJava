package be.mcjava.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The model class for the end-of-day report, containing all sales and made products
 */
public class Summary {
    private List<AbstractOrderItem> madeProducts;
    
    public Summary(List<AbstractOrderItem> madeProducts) {
        this.madeProducts = madeProducts;
    }
    
    public List<AbstractOrderItem> getMadeProducts() {
        return madeProducts;
    }
    
    public void setMadeProducts(List<AbstractOrderItem> madeProducts) {
        this.madeProducts = madeProducts;
    }
}
