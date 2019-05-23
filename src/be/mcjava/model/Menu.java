package be.mcjava.model;

import java.util.List;

/**
 * Contains the data for the menu, where the customer can choose and order his/her food.
 */
public class Menu {
    private List<MenuItem> menuItems;
    
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
    
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
