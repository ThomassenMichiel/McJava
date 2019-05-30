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
    
    public static class Builder {
        private Long id;
        private Product product;
        private int amount;
        private BigDecimal price;
        
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder withProduct(Product product) {
            this.product = product;
            return this;
        }
        
        public Builder withAmount(int amount) {
            this.amount = amount;
            return this;
        }
        
        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }
        
        public SingleOrderItem build() {
            SingleOrderItem menu = new SingleOrderItem();
            menu.setId(id);
            menu.setItems(product);
            menu.setPrice(price);
            menu.setAmount(amount);
            
            return menu;
        }
    }
}
