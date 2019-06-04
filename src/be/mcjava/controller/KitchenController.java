package be.mcjava.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class KitchenController {
    @FXML
    private VBox contentvbox;

    @FXML
    private void initialize() throws FileNotFoundException {
        try {
            getOrderData();
            addOrderToVBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getOrderData(){
        //gets orders from DB
        //refreshes every 30 secs.
    }
    private void addOrderToVBox(){
        // creates a text version of the order in the view.
        // with every order comes a "finished" button.
        // the finished button has onAction = "#finishedIsPressed".
    }
    @FXML
    private void finishedIsPressed (ActionEvent event){
        // add order to list finished orders.
        // when pressed initialize remove order.
        // when pressed
    }
    private void removeOrder(){
        // removes order initialized trough an action event method.
    }

}
