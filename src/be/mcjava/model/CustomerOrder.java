package be.mcjava.model;


import java.util.List;

/**
 * Contains all data related to the order of a customer
 */
public class CustomerOrder {
    private Long id;
    private String name;
    private String telephoneNumber;
    private List<OrderItem> itemsToOrder;
    private boolean finished;
    
    public CustomerOrder(String name, String telephoneNumber, List<OrderItem> itemsToOrder) {
        this(null,name,telephoneNumber,itemsToOrder);
    }
    
    public CustomerOrder(Long id, String name, String telephoneNumber, List<OrderItem> itemsToOrder) {
        setId(id);
        setName(name);
        setTelephoneNumber(telephoneNumber);
        setItemsToOrder(itemsToOrder);
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
    
    public boolean isFinished() {
        return finished;
    }
    
    public void setFinished(boolean finished) {
        boolean actuallyFinished = getItemsToOrder().stream()
                .allMatch(OrderItem::isFinished);
        this.finished = actuallyFinished && finished;
    }
    
    public void addItem(OrderItem orderItem) {
        if (itemsToOrder.contains(orderItem)) {
            int positionOfOrderItem = itemsToOrder.indexOf(orderItem);
            OrderItem existingOrder = itemsToOrder.get(positionOfOrderItem);
            existingOrder.setAmount(existingOrder.getAmount() + orderItem.getAmount());
            return;
        }
        itemsToOrder.add(orderItem);
    }
    
    public void removeFromOrder(OrderItem orderItem) {
        itemsToOrder.remove(orderItem);
    }
    
    public void editOrder(OrderItem orderItem) {
        if (itemsToOrder.contains(orderItem)) {
            int positionOfOrderItem = itemsToOrder.indexOf(orderItem);
            itemsToOrder.set(positionOfOrderItem,orderItem);
        }
    }
}
