package be.mcjava.controller;

import be.mcjava.model.AbstractOrderItem;
import be.mcjava.model.CustomerOrder;
import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.model.SingleOrderItem;
import be.mcjava.service.CustomerOrderService;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class CustomerReceiptController {
    @FXML
    private Text id;
    @FXML
    private GridPane items;
    
    private int row = 5;
    private int column = 0;
    
    public void initialize() {
        CustomerOrder customerOrder = CustomerOrderService.customerOrder;
        setConstraints();
    
        id.setText("== " + customerOrder.getId() + " ==");
        id.setFont(Font.font(24));
        id.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(id,HPos.CENTER);
        GridPane.setMargin(id,new Insets(30,0,30,0));
        customerOrder.getItemsToOrder().forEach(this::addItemsToDisplay);
        setTotalPrice(customerOrder);
    }
    
    private void setTotalPrice(CustomerOrder customerOrder) {
        String formattedPrice = String.format("%5.2f", customerOrder.getFinalPrice());
        Text totalText = new Text("Total:");
        Text formattedPriceText = new Text(formattedPrice);
        GridPane.setMargin(totalText,new Insets(30,0,0,0));
        GridPane.setMargin(formattedPriceText,new Insets(30,0,0,0));
        items.addRow(row,new Text(),totalText,formattedPriceText);
    }
    
    private void setConstraints() {
        items.getColumnConstraints().add(new ColumnConstraints(200));
        items.getColumnConstraints().add(new ColumnConstraints(50));
        items.getColumnConstraints().add(new ColumnConstraints(50));
        items.getColumnConstraints().get(2).setHalignment(HPos.RIGHT);
    }
    
    private void addItemsToDisplay(AbstractOrderItem item) {
        if (item instanceof PreMadeOrderMenu) {
            createText(((PreMadeOrderMenu) item));
        }
        if (item instanceof SingleOrderItem) {
            createText(((SingleOrderItem) item));
        }
    }
    
    private void createText(SingleOrderItem item) {
        items.add(new Text(item.getItems().getName()),column++,row);
        items.add(new Text(String.valueOf(item.getAmount())),column++,row);
        String price = String.format("%5.2f", item.getPrice());
        items.add(new Text(price),column++,row);
        row++;
        column = 0;
    }
    
    private void createText(PreMadeOrderMenu item) {
        items.add(new Text(item.getName()),column++,row);
        items.add(new Text(String.valueOf(item.getAmount())),column++,row);
        String price = String.format("%5.2f", item.getPrice());
        items.add(new Text(price),column++,row);
        row++;
        item.getItems().forEach(this::addEachItemInAMenu);
        column = 0;
    }
    
    private void addEachItemInAMenu(SingleOrderItem item) {
        String itemEntry = "\t- " + item.getItems().getName();
        items.add(new Text(itemEntry),0,row++);
    }
}
