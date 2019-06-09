package be.mcjava.controller;

import be.mcjava.model.CookingOrders;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.*;

public class KitchenController {
    private List<CookingOrders> cookingOrdersList;

    @FXML
    private GridPane contentgrid;

    /**   @FXML
    private void initialize() throws FileNotFoundException {
    try {
    getOrderData();
    addOrderToGrid();
    } catch (Exception e) {
    e.printStackTrace();
    }
    }

    private void getOrderData() {
    CookingOrderDao cookingOrderDao = new CookingOrderDao();
    ExecutorService executorService = Executors.newFixedThreadPool( 5 );

    Future<List<CookingOrders>> futureCookingOrder = executorService.submit( cookingOrderDao::getOrdersToCook );
    try {
    cookingOrdersList = futureCookingOrder.get( 30, TimeUnit.SECONDS );
    } catch (InterruptedException e) {
    e.printStackTrace();
    } catch (ExecutionException e) {
    e.printStackTrace();
    } catch (TimeoutException e) {
    e.printStackTrace();
    }
    }

    private void addOrderToGrid() {
    for (CookingOrders cookingOrders : cookingOrdersList) {
    VBox vBox = new VBox();
    TextFlow textFlow = new TextFlow( );
    Text orderItem = new Text(  );
    cookingOrders.getOrdersToCook();// all products in 1 column.
    // creates a text version of the order in the view.
    Button button = new Button( "Finish" );
    button.setOnMouseClicked(  mouseEvent ->
    finishedIsPressed( mouseEvent ));
    vBox.getChildren().add( button);

    }
    // with every order comes a "finished" button.
    // the finished button has onAction = "#finishedIsPressed".
    }*/
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
