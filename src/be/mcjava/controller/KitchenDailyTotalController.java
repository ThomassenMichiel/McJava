package be.mcjava.controller;

import be.mcjava.dao.CustomerOrderDao;
import be.mcjava.model.AbstractOrderItem;
import be.mcjava.model.SingleOrderItem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class KitchenDailyTotalController {
    @FXML
    private TableColumn<AbstractOrderItem,String> totalColumn;
    @FXML
    private TableView<AbstractOrderItem> itemsTable;
    @FXML
    private TableColumn<SingleOrderItem,String> productColumn;
    @FXML
    private TableColumn<AbstractOrderItem,Integer> amountColumn;
    @FXML
    private Button switchView;
    
    public void initialize() {
        List<AbstractOrderItem> orderSummary = CustomerOrderDao.getOrderSummary();
        ObservableList<AbstractOrderItem> details = FXCollections.observableList(orderSummary);
    
        productColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getItems().getName()));
        amountColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getAmount()).asObject());
        totalColumn.setCellValueFactory(param -> new SimpleStringProperty("€ " + param.getValue().getPrice().toString()));
        
        itemsTable.setItems(details);
    }
    
    
    public void goToIncomingOrders(ActionEvent actionEvent) {
        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("/be/mcjava/view/KitchenScreen.fxml");
        ViewManager.setStageDimensions(1280,720,1680,1050,1920,1080);
    }
}
