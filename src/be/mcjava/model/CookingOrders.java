package be.mcjava.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contains all data related to a single customer order that needs to be prepared.
 */
public class CookingOrders {
    private Long id;
    private List<CustomerOrder> ordersToCook;
    private boolean finished;
    
    public CookingOrders() {
        this(null,new ArrayList<>());
    }
    
    public CookingOrders(List<CustomerOrder> ordersToCook) {
        this(null,ordersToCook);
    }
    
    public CookingOrders(Long id, List<CustomerOrder> ordersToCook) {
        setId(id);
        setOrdersToCook(ordersToCook);
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<CustomerOrder> getOrdersToCook() {
        return ordersToCook;
    }
    
    public void setOrdersToCook(List<CustomerOrder> ordersToCook) {
        this.ordersToCook = ordersToCook;
    }
    
    public boolean isFinished() {
        return finished;
    }
    
    public void setFinished(boolean finished) {
        boolean actualFinished = ordersToCook.stream()
                .allMatch(CustomerOrder::isFinishedCooking);
        this.finished = actualFinished && finished;
    }
    
    public void addOrder(CustomerOrder order) {
        if (!containsOrder(order)) {
            ordersToCook.add(order);
        }
    }
    
    private boolean containsOrder(CustomerOrder order) {
        return Objects.nonNull(order) && ordersToCook.contains(order);
    }
    
}
