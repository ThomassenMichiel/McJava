package be.mcjava.model;

import java.math.BigDecimal;

public interface OrderItem<T> {
    T getItems();
    void setItems(T t);
    BigDecimal getPrice();
    void setPrice(BigDecimal price);
    int getAmount();
    void setAmount(int amount);
    boolean isFinished();
    void setFinished(boolean finished);
}
