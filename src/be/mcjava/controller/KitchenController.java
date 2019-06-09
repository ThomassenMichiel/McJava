package be.mcjava.controller;

import be.mcjava.dao.CookingOrderDao;
import be.mcjava.model.AbstractOrderItem;
import be.mcjava.model.CookingOrders;
import be.mcjava.model.CustomerOrder;
import be.mcjava.model.SingleOrderItem;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class KitchenController {
    @FXML
    private FlowPane contentPane;
    private List<CookingOrders> ordersToCook = new ArrayList<>();
    
    public void initialize() {
        getOrderData();
    }
    
    private void getOrderData() {
        CookingOrderDao cookingOrderDao = new CookingOrderDao();
        getOrders(cookingOrderDao);
        pollDatabase(cookingOrderDao);
    }
    
    private void addOrderToGrid() {
        contentPane.getChildren().clear();
        for (CookingOrders order : ordersToCook) {
            for (CustomerOrder customerOrder : order.getOrdersToCook()) {
                VBox pane = new VBox();
                pane.setPadding(new Insets(15));
                pane.setStyle("-fx-border-color: black");
                for (AbstractOrderItem abstractOrderItem : customerOrder.getItemsToOrder()) {
                    if (abstractOrderItem instanceof SingleOrderItem) {
                        SingleOrderItem orderItem = (SingleOrderItem) abstractOrderItem;
                        pane.getChildren().add(new Text(orderItem.getAmount() + "\t" + orderItem.getItems().getName()));
                    }
                }
                contentPane.getChildren().add(pane);
            }
        }
    }
    
    private void pollDatabase(CookingOrderDao cookingOrderDao) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(30), event -> getOrders(cookingOrderDao)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    private void getOrders(CookingOrderDao cookingOrderDao) {
        System.out.println(LocalDateTime.now());
        ordersToCook = cookingOrderDao.getOrdersToCook();
        addOrderToGrid();
    }
    
    @FXML
    private void finishedIsPressed(MouseEvent event) {
        // add order to list finished orders.
        // when pressed initialize remove order.
        // when pressed
    }
    
    private void removeOrder() {
        // removes order initialized trough an action event method.
    }
    
}
