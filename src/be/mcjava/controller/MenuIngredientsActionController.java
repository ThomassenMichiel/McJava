package be.mcjava.controller;

import be.mcjava.dao.AllowedMenuProductsDao;
import be.mcjava.dao.ProductsDao;
import be.mcjava.model.AllowedMenuProduct;
import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.model.Product;
import be.mcjava.model.SingleOrderItem;
import be.mcjava.service.AllowedMenuProductService;
import be.mcjava.service.CustomerOrderService;
import be.mcjava.service.PreMadeOrderMenuService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MenuIngredientsActionController {
    @FXML
    private HBox headerhbox;

    @FXML
    private GridPane mainproductsgrid;

    private VBox chosenVBox;

    private VBox productsOverviewVBox;

    private ViewManager viewManager = new ViewManager();

    private List<Product> productList;

    private PreMadeOrderMenu preMadeOrderMenu;

    private List<SingleOrderItem> productsToOrderList = new ArrayList<>();

    private List<AllowedMenuProduct> allowedMenuProductList;

    @FXML
    public void initialize() {
        allowedMenuProductList = AllowedMenuProductService.getAllowedMenuProductsByPremadeMenuName();
        buildPreMadeMenuAllowedItemsOverrview();
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

    @FXML
    private void menuProductClicked(MouseEvent mouseEvent) {
        Button clickedButton = (Button) mouseEvent.getSource();
        VBox clickedButtonParentVBox = (VBox) clickedButton.getParent();
        Integer clickedGridPaneColumnIndex = GridPane.getColumnIndex(clickedButtonParentVBox);
        removeProductFromOverview(clickedButton, clickedButtonParentVBox, clickedGridPaneColumnIndex);

        addProductToProductsToOrderList(clickedButton.getText());
    }

    @FXML
    public void confirmOrderPressed(ActionEvent actionEvent) {
        PreMadeOrderMenuService.addProductsListToPreMadeOrderMenu(productsToOrderList);
        CustomerOrderService.addCurrentPreMadeMenu();
        viewManager.displayFmxlScreen("../view/CustomerMainMenuOverview.fxml");
    }

    public void cancelOrderPressed(ActionEvent actionEvent) {
        //Todo: check if all needed items are chosen
        //Todo: cencel current menu-creation, remove already created obj
        viewManager.displayFmxlScreen("../view/CustomerMainMenuOverview.fxml");
    }

    private void addProductToProductsToOrderList(String productName) {
        ProductsDao productsDao = new ProductsDao();
        Product product = productsDao.getProductsByName(productName).get(0);
        SingleOrderItem singleOrderItem = new SingleOrderItem();
        singleOrderItem.setItems(product);
        singleOrderItem.setAmount(1);
        productsToOrderList.add(singleOrderItem);
    }

    private void removeProductFromOverview(Button buttonToRemove, VBox fromVBox, Integer clickedGridPaneColumnIndex) {
        buttonToRemove.setOnMouseClicked(mouseEvent -> chosenMenuProductClicked(mouseEvent));
        VBox targetVBox = (VBox) getNodeFromGridPane(mainproductsgrid, clickedGridPaneColumnIndex, 1);
        targetVBox.getChildren().add(buttonToRemove);
        fromVBox.getChildren().remove(buttonToRemove);
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private void buildPreMadeMenuAllowedItemsOverrview() {
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
}
