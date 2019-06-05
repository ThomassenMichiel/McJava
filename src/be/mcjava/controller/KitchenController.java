package be.mcjava.controller;

import be.mcjava.model.CookingOrders;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.FileNotFoundException;
import java.util.List;

public class KitchenController {

    private List<CookingOrders> cookingOrdersList;

    @FXML
    private GridPane contentgrid;

    @FXML
    private void initialize() throws FileNotFoundException {
        try {
            getOrderData();
            addOrderToGrid();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getOrderData() {
        //gets orders from DB
        //refreshes every 30 secs.
    }

    private void addOrderToGrid() {
        for (CookingOrders cookingOrders : cookingOrdersList) {
            VBox vBox = new VBox();
            Label label = new Label( cookingOrders.getOrdersToCook().toString() );
 // creates a text version of the order in the view.
            Button button = new Button( "#Finish" );
            button.setOnMouseClicked(  mouseEvent ->
                    finishedIsPressed( mouseEvent ));
            vBox.getChildren().add( button);

        }
        // with every order comes a "finished" button.
        // the finished button has onAction = "#finishedIsPressed".
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
