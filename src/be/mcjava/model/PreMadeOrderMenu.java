package be.mcjava.model;

import java.math.BigDecimal;
import java.util.List;

public class PreMadeOrderMenu extends AbstractOrderItem<List<SingleOrderItem>>  {
    private BigDecimal price;
    private String name;
    private String pictureName;


    public String getpictureName() {
        return pictureName;
    }

    public void setpictureName(String pictureName) {
        this.pictureName = pictureName;
    }
    
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreMadeOrderMenu)) return false;
        if (!super.equals(o)) return false;
        
        PreMadeOrderMenu that = (PreMadeOrderMenu) o;
        
        if (!getPrice().equals(that.getPrice())) return false;
        return getName().equals(that.getName());
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getPrice().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
    
    public static class Builder {
        private Long id;
        private String name;
        private List<SingleOrderItem> product;
        private int amount;
        private BigDecimal price;
        private String pictureName;
        
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withProduct(List<SingleOrderItem> product) {
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
        
        public Builder withPictureName(String pictureName){
            this.pictureName = pictureName;
            return this;
        }
        
        public PreMadeOrderMenu build() {
            PreMadeOrderMenu menu = new PreMadeOrderMenu();
            menu.setId(id);
            menu.setName(name);
            menu.setItems(product);
            menu.setPrice(price);
            menu.setAmount(amount);
            
            return menu;
        }
    }
    
}
