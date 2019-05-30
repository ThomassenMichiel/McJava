package be.mcjava.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Contains all data related to the order of a customer
 */
public class CustomerOrder {
    private Long id;
    private String name;
    private String telephoneNumber;
    private List<OrderItem> itemsToOrder;
    private boolean finishedCooking;
    private boolean ordered;
    private BigDecimal finalPrice;
    
    private CustomerOrder() {
        finalPrice = BigDecimal.ZERO;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    
    public List<OrderItem> getItemsToOrder() {
        return itemsToOrder;
    }
    
    public void setItemsToOrder(List<OrderItem> itemsToOrder) {
        this.itemsToOrder = itemsToOrder;
    }
    
    public boolean isOrdered() {
        return ordered;
    }
    
    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
    
    public boolean isFinishedCooking() {
        return finishedCooking;
    }
    
    public void setFinishedCooking(boolean finished) {
        boolean actuallyFinished = getItemsToOrder().stream()
                .allMatch(OrderItem::isFinished);
        this.finishedCooking = actuallyFinished && finished;
    }
    
    public BigDecimal getFinalPrice() {
        finalPrice = itemsToOrder.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        return finalPrice;
    }
    
    public void addItem(OrderItem singleOrderItem) {
        if (itemsToOrder.contains(singleOrderItem)) {
            int positionOfOrderItem = itemsToOrder.indexOf(singleOrderItem);
            OrderItem existingOrder = itemsToOrder.get(positionOfOrderItem);
            existingOrder.setAmount(existingOrder.getAmount() + singleOrderItem.getAmount());
            return;
        }
        itemsToOrder.add(singleOrderItem);
    }
    
    public void removeFromOrder(OrderItem singleOrderItem) {
        itemsToOrder.remove(singleOrderItem);
    }
    
    public void editOrder(OrderItem singleOrderItem) {
        if (itemsToOrder.contains(singleOrderItem)) {
            int positionOfOrderItem = itemsToOrder.indexOf(singleOrderItem);
            itemsToOrder.set(positionOfOrderItem, singleOrderItem);
        }
    }
    
    public static class Builder {
        private Long id;
        private String name;
        private String telephoneNumber;
        private List<OrderItem> itemsToOrder;
        
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder telephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }
        
        public Builder withItemsToOrder(List<OrderItem> singleOrderItems) {
            this.itemsToOrder = singleOrderItems;
            return this;
        }
        
        public CustomerOrder build() {
            CustomerOrder customerOrder = new CustomerOrder();
            customerOrder.setId(id);
            customerOrder.setName(name);
            customerOrder.setTelephoneNumber(telephoneNumber);
            customerOrder.setItemsToOrder(itemsToOrder);
            
            return customerOrder;
        }
    }
}
