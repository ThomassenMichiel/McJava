package be.mcjava.controller;

import be.mcjava.dao.AllowedMenuProductsDao;
import be.mcjava.dao.ProductsDao;
import be.mcjava.model.AllowedMenuProduct;
import be.mcjava.model.Product;
import be.mcjava.service.ChosenProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class MenuIngredientsActionController {
    @FXML
    private HBox headerhbox;

    @FXML
    private GridPane mainproductsgrid;

    private VBox chosenVBox;

    private VBox productsOverviewVBox;

    private List<Product> productList;

    @FXML
    public void initialize(){
        AllowedMenuProductsDao allowedMenuProductsDao = new AllowedMenuProductsDao();
        List<AllowedMenuProduct> allowedMenuProductList = allowedMenuProductsDao.getAllowedMenuProductsByPremadeMenuName(ChosenProductService.preMadeMenu.getName());
        productsOverviewVBox = new VBox();
        for (AllowedMenuProduct allowedMenuProduct : allowedMenuProductList) {
            Button button = new Button(allowedMenuProduct.getProductName());
            button.setOnMouseClicked(mouseEvent -> menuProductClicked(mouseEvent));
            productsOverviewVBox.getChildren().add(button);
        }
        mainproductsgrid.add(productsOverviewVBox,0,0);
        chosenVBox = new VBox();
        mainproductsgrid.add(chosenVBox,1,0);
    }

    @FXML
    private void menuProductClicked(MouseEvent mouseEvent) {
        Button clickedButton = (Button) mouseEvent.getSource();
        String chosenProductName = clickedButton.getText();
        removeProductFromOverview(clickedButton);
        chosenVBox.getChildren().add(clickedButton);
    }

    private void removeProductFromOverview(Button buttonToRemove) {
        buttonToRemove.setOnMouseClicked(mouseEvent -> selectMenuProductClicked(mouseEvent));
        productsOverviewVBox.getChildren().remove(buttonToRemove);
    }

    @FXML
    private void selectMenuProductClicked(MouseEvent mouseEvent) {
        Button clickedButton = (Button) mouseEvent.getSource();
        clickedButton.setOnMouseClicked(mouseEvent2 -> menuProductClicked(mouseEvent2));
        productsOverviewVBox.getChildren().add(clickedButton);
        chosenVBox.getChildren().remove(clickedButton);
    }
}
