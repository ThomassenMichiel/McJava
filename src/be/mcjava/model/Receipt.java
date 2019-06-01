package be.mcjava.model;

import java.time.LocalDateTime;

public class Receipt {
    private CustomerOrder customerOrder;
    private LocalDateTime orderTime;
    
    public Receipt(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
        this.orderTime = LocalDateTime.now();
    }
    
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }
    
    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
    
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
