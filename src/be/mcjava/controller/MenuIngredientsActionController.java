package be.mcjava.controller;

import be.mcjava.dao.ProductsDao;
import be.mcjava.model.AllowedMenuProduct;
import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.model.Product;
import be.mcjava.model.SingleOrderItem;
import be.mcjava.service.AllowedMenuProductService;
import be.mcjava.service.CustomerOrderService;
import be.mcjava.service.PreMadeOrderMenuService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MenuIngredientsActionController {
    private VBox chosenVBox;

    private VBox productsOverviewVBox;

    @FXML
    private HBox productshbox;

    private ViewManager viewManager = new ViewManager();

    private List<Product> productList;

    private PreMadeOrderMenu preMadeOrderMenu;

    private List<SingleOrderItem> productsToOrderList = new ArrayList<>();

    private List<AllowedMenuProduct> allowedMenuProductList;

    private List<ListView> listViewList;

    @FXML
    public void initialize() {
        allowedMenuProductList = AllowedMenuProductService.getAllowedMenuProductsByPremadeMenuName();
        buildPreMadeMenuAllowedItemsOverview();
    }

    @FXML
    public void confirmOrderPressed(ActionEvent actionEvent) {
        List<String> productToOrderNamesList = new ArrayList<>();
        for (ListView listView : listViewList) {
            String chosenProductName = (String) listView.getSelectionModel().getSelectedItems().get(0);
            productToOrderNamesList.add(chosenProductName);
        }
        PreMadeOrderMenuService.addProductsToCurrentPreMadeMenuOrder(productToOrderNamesList);
        CustomerOrderService.addCurrentPreMadeMenu();
        viewManager.displayFmxlScreen("../view/CustomerMainMenuOverview.fxml");
    }

    public void cancelOrderPressed(ActionEvent actionEvent) {
        //Todo: check if all needed items are chosen
        //Todo: cancel current menu-creation, remove already created obj
        viewManager.displayFmxlScreen("../view/CustomerMainMenuOverview.fxml");
    }

    private void buildPreMadeMenuAllowedItemsOverview() {
        listViewList = new ArrayList<>();
        List<Integer> itemPositionsNeeded = allowedMenuProductList.stream().map(AllowedMenuProduct::getItemPositionInMenu).distinct().collect(Collectors.toCollection(ArrayList::new));
        for (Integer integer : itemPositionsNeeded) {
            List<String> list = allowedMenuProductList.stream()
                    .filter(s -> s.getItemPositionInMenu().equals( integer))
                    .map(AllowedMenuProduct::getProductName)
                    .collect(Collectors.toCollection(ArrayList::new));
            ListView productsListView = new ListView();
            ObservableList<String> observableList = FXCollections.observableList(list);
            productsListView.setItems(observableList);
            //mainproductsgrid.add(productsListView,  integer,1);
            listViewList.add(productsListView);
            productshbox.getChildren().add(productsListView);
        }
    }
}
