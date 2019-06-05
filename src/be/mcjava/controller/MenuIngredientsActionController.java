package be.mcjava.controller;

import be.mcjava.dao.AllowedMenuProductsDao;
import be.mcjava.model.AllowedMenuProduct;
import be.mcjava.model.Product;
import be.mcjava.service.ChosenProductService;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    public void initialize() {
        AllowedMenuProductsDao allowedMenuProductsDao = new AllowedMenuProductsDao();
        List<AllowedMenuProduct> allowedMenuProductList = allowedMenuProductsDao.getAllowedMenuProductsByPremadeMenuName(ChosenProductService.preMadeMenu.getName());
        //productsOverviewVBox = new VBox();
        //get number of columns needed
        int columnsNeeded = 0;
        for (AllowedMenuProduct allowedMenuProduct : allowedMenuProductList) {
            if (allowedMenuProduct.getItemPositionInMenu() > columnsNeeded) {
                columnsNeeded = allowedMenuProduct.getItemPositionInMenu();
            }
        }
        //create vboxes inside all needed columns on the gridPane and one row lower for chosen products
        for (int i = 0; i < columnsNeeded; i++) {
            VBox vBox = new VBox();
            mainproductsgrid.add(vBox, i, 0);
            VBox vBox2 = new VBox();
            mainproductsgrid.add(vBox2, i, 1);
        }
        for (AllowedMenuProduct allowedMenuProduct : allowedMenuProductList) {
            Button button = new Button(allowedMenuProduct.getProductName());
            button.setOnMouseClicked(mouseEvent -> menuProductClicked(mouseEvent));
            VBox vBox = (VBox) getNodeFromGridPane(mainproductsgrid, allowedMenuProduct.getItemPositionInMenu() - 1, 0);
            vBox.getChildren().add(button);
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    @FXML
    private void menuProductClicked(MouseEvent mouseEvent) {
        Button clickedButton = (Button) mouseEvent.getSource();
        VBox clickedButtonParentVBox = (VBox) clickedButton.getParent();
        Integer clickedGridPaneColumnIndex = GridPane.getColumnIndex(clickedButtonParentVBox);
        removeProductFromOverview(clickedButton, clickedButtonParentVBox, clickedGridPaneColumnIndex);
    }

    private void removeProductFromOverview(Button buttonToRemove, VBox fromVBox, Integer clickedGridPaneColumnIndex) {
        buttonToRemove.setOnMouseClicked(mouseEvent -> chosenMenuProductClicked(mouseEvent));
        VBox targetVBox = (VBox) getNodeFromGridPane(mainproductsgrid, clickedGridPaneColumnIndex, 1);
        targetVBox.getChildren().add(buttonToRemove);
        fromVBox.getChildren().remove(buttonToRemove);
    }

    @FXML
    private void chosenMenuProductClicked(MouseEvent mouseEvent) {
        Button clickedButton = (Button) mouseEvent.getSource();
        clickedButton.setOnMouseClicked(mouseEvent2 -> menuProductClicked(mouseEvent2));
        VBox clickedVBox = (VBox) clickedButton.getParent();
        clickedVBox.getChildren().remove(clickedButton);
        Integer clickedGridPaneColumnIndex = GridPane.getColumnIndex(clickedVBox);
        VBox targetVBox = (VBox) getNodeFromGridPane(mainproductsgrid, clickedGridPaneColumnIndex, 0);
        targetVBox.getChildren().add(clickedButton);
    }
}
