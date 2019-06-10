package be.mcjava.controller;

import be.mcjava.fxentensions.MultiSelectListView;
import be.mcjava.model.*;
import be.mcjava.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private List<String> productToOrderNamesList = new ArrayList<>();

    @FXML
    public void initialize() {
        allowedMenuProductList = AllowedMenuProductService.getAllowedMenuProductsByPremadeMenuName();
        buildAllowedItemChoicesOverview();
    }

    @FXML
    public void confirmOrderPressed(ActionEvent actionEvent) {
        if (onlyOneProductTypeInMenu()) {
            for (ListView listView : listViewList) {
                listView.getSelectionModel().getSelectedItems().forEach(s -> productToOrderNamesList.add((String) s));
            }
        } else {
            for (ListView listView : listViewList) {
                String chosenProductName = (String) listView.getSelectionModel().getSelectedItems().get(0);
                productToOrderNamesList.add(chosenProductName);
            }
        }
        if (isThereSufficientStock()) {
            if (onlyOneProductTypeInMenu()) {
                SingleOrderItemService.addProductsAsSingleOrderItems(productToOrderNamesList);
            } else {
                PreMadeOrderMenuService.addProductsToCurrentPreMadeMenuOrder(productToOrderNamesList);
                CustomerOrderService.addCurrentPreMadeMenu();
            }
            viewManager.displayFmxlScreen("/be/mcjava/view/CustomerMainMenuOverview.fxml");
        }
    }

    public void cancelOrderPressed(ActionEvent actionEvent) {
        viewManager.displayFmxlScreen("/be/mcjava/view/CustomerMainMenuOverview.fxml");
    }

    private void buildAllowedItemChoicesOverview() {
        listViewList = new ArrayList<>();
        List<Integer> itemPositionsNeeded = allowedMenuProductList.stream().map(AllowedMenuProduct::getItemPositionInMenu).distinct().collect(Collectors.toCollection(ArrayList::new));
        for (Integer integer : itemPositionsNeeded) {
            List<String> list = allowedMenuProductList.stream()
                    .filter(s -> s.getItemPositionInMenu() == integer)
                    .map(AllowedMenuProduct::getProductName)
                    .collect(Collectors.toCollection(ArrayList::new));
            ListView productsListView = onlyOneProductTypeInMenu() ? new MultiSelectListView() : new ListView();
            ObservableList<String> observableList = FXCollections.observableList(list);
            productsListView.setItems(observableList);
            listViewList.add(productsListView);
            productshbox.getChildren().add(productsListView);
        }
    }

    /***
     * returns true if the allowedMenuProductList has only 1 item allowed, to indicate it is not a real PreMadeMenu
     * but a collection of products eg: drinks, burgers, wraps...
     * @return
     */
    private boolean onlyOneProductTypeInMenu() {
        return allowedMenuProductList.stream().mapToInt(AllowedMenuProduct::getItemPositionInMenu).max().getAsInt() == 1;
    }

    /***
     * checks if there's enough stock to complete the current order
     * and displays a message with the ingredients that don't have enough stock
     * @return
     */
    private boolean isThereSufficientStock() {
        List<Product> outOfStockProductsList = ProductService.getOutOfStockProductList(ProductService.getProductsListByNameList(productToOrderNamesList));
        if(outOfStockProductsList.size() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Not enough stock");
            alert.setHeaderText("We cannot complete your order because there is not enough stock of the following ingredients");
            for (Product product : outOfStockProductsList) {
                alert.setContentText(alert.getContentText()+product.getName()+"\n");
            }
            Optional<ButtonType> result = alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }

}