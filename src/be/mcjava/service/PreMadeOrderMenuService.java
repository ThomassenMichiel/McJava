package be.mcjava.service;

import be.mcjava.model.PreMadeOrderMenu;

import java.math.BigDecimal;

public class PreMadeOrderMenuService {
    public static PreMadeOrderMenu preMadeOrderMenu;

    /***
     * this creates a new PreMadeOrderMenu
     * @param nextOrderItemId
     * @param orderItemName
     * @param price
     */

    public static void createPreMadeOrderMenu(long nextOrderItemId, String orderItemName, BigDecimal price) {
        preMadeOrderMenu = new PreMadeOrderMenu.Builder()
                .withId(nextOrderItemId)
                .withName(orderItemName)
                .withPrice(price)
                .withAmount(1)
                .build();
    }
}
