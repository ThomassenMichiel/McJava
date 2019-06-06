package be.mcjava.controller;

import be.mcjava.model.AbstractOrderItem;
import be.mcjava.model.CustomerOrder;
import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.model.SingleOrderItem;
import be.mcjava.service.CustomerOrderService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class CustomerReceiptController {
    @FXML
    private Label totalPriceLabel;
    @FXML
    private VBox items;
    
    public void initialize() {
        CustomerOrder customerOrder = CustomerOrderService.customerOrder;
        customerOrder.getItemsToOrder().forEach(this::addItemsToDisplay);
        String totalPrice = makeStringFixedLength("Total:", 44);
        totalPriceLabel.setText(String.format("%s %5.2f",totalPrice,customerOrder.getFinalPrice()));
    }
    
    private void addItemsToDisplay(AbstractOrderItem item) {
        if (item instanceof PreMadeOrderMenu) {
            createLabels(((PreMadeOrderMenu) item));
        }
        if (item instanceof SingleOrderItem) {
            createLabels(((SingleOrderItem) item));
        }
    }
    
    private void createLabels(SingleOrderItem item) {
        String name = makeStringFixedLength(item.getItems().getName());
        String firstLineString = String.format("%-30s\t%2d\t%05.2f", name, item.getAmount(), item.getPrice());
        Label firstLineLabel = new Label(firstLineString);
        items.getChildren().add(firstLineLabel);
    }
    
    private void createLabels(PreMadeOrderMenu menu) {
        String name = makeStringFixedLength(menu.getName());
        String firstLineString = String.format("%-30s\t%2d\t%05.2f", name, menu.getAmount(), menu.getPrice());
        Label firstLineLabel = new Label(firstLineString);
        items.getChildren().add(firstLineLabel);
        
        menu.getItems().forEach(this::addEachItemInAMenu);
    }
    
    private String makeStringFixedLength(String stringToMakeFixedLength, int length) {
        StringBuilder fixedLengthName = new StringBuilder(stringToMakeFixedLength);
        for (int i = fixedLengthName.length(); i < length; i++) {
            fixedLengthName.append(" ");
        }
        return fixedLengthName.toString();
    }
    
    private String makeStringFixedLength(String stringToMakeFixedLength) {
        return makeStringFixedLength(stringToMakeFixedLength,30);
    }
    
    private void addEachItemInAMenu(SingleOrderItem item) {
        String desiredStringLayout = String.format("\t- %s", item.getItems().getName());
        items.getChildren().add(new Label(desiredStringLayout));
    }
}
