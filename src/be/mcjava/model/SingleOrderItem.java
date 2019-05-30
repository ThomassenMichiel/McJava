package be.mcjava.model;

import java.math.BigDecimal;

/**
 * Contains all the data related to a single ordered item on the CustomerOrder
 */
public class SingleOrderItem extends AbstractOrderItem<Product> {
    @Override
    protected void recalculateTotalPrice(Product product, int amount) {
        setPrice(product.getPrice().multiply(new BigDecimal(amount)));
    }
}
