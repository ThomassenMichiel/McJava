package be.mcjava.controller;

import be.mcjava.dao.PreMadeOrderMenuDao;
import be.mcjava.model.CustomerOrder;
import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.service.CustomerOrderService;
import be.mcjava.service.PreMadeOrderMenuService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;


public class MenuActionController {
    @FXML
    private GridPane maingrid;

    @FXML
    private GridPane productsgrid;

    @FXML
    private HBox productshbox;

    @FXML
    private Button finishorderbutton;

    @FXML
    private Label totalOrderedLabel;

    private ViewManager viewManager = new ViewManager();

    private List<PreMadeOrderMenu> mainPreMadeOrderMenuList;
    private List<PreMadeOrderMenu> productsPreMadeOrderMenuList;
    private PreMadeOrderMenuDao preMadeOrderMenuDao;

    private String productsPicturesPath = "resources/menutextandimages/";

    private double combinedImagesWidth = 0;

    @FXML
    public void initialize() throws FileNotFoundException, SQLException {
        getMenuData();
        addMenusToGrid();
        if (CustomerOrderService.isOrderValid()) {
            finishorderbutton.setDisable(false);
        } else {
            finishorderbutton.setDisable(true);
        }
        totalOrderedLabel.setText(CustomerOrderService.customerOrder.getFinalPrice().toString() + " €");
    }

    private void getMenuData() throws SQLException {
        mainPreMadeOrderMenuList = PreMadeOrderMenuService.getMenusByIdRange(1, 4);
        productsPreMadeOrderMenuList = PreMadeOrderMenuService.getMenusByIdRange(5, 9);
    }

    private void addMenusToGrid() throws FileNotFoundException {
        int columnPosition = 0;
        int rowPosition = 0;

        for (PreMadeOrderMenu preMadeOrderMenu : mainPreMadeOrderMenuList) {
            VBox vBox = new VBox();
            Image menuImage = new Image(new FileInputStream(productsPicturesPath + preMadeOrderMenu.getPictureName()));
            combinedImagesWidth += menuImage.getWidth();
            vBox.getChildren().add(new ImageView(menuImage));
            Label label = new Label(preMadeOrderMenu.getName());
            vBox.getChildren().add(label);
            vBox.setOnMouseClicked(mouseEvent -> menusClicked(mouseEvent));
            vBox.setAlignment(Pos.CENTER);
            maingrid.add(vBox, columnPosition++, rowPosition);
        }

        ViewManager.setStageWidth(combinedImagesWidth);
        ViewManager.setStageHeight(600);
        for (PreMadeOrderMenu preMadeOrderMenu : productsPreMadeOrderMenuList) {
            VBox vBox = new VBox();
            Image menuImage = new Image(new FileInputStream(productsPicturesPath + preMadeOrderMenu.getPictureName()));
            ImageView imageView = new ImageView(menuImage);
            imageView.setScaleX(0.4);
            imageView.setScaleY(0.4);
            vBox.getChildren().add(imageView);
            Label label = new Label(preMadeOrderMenu.getName());
            vBox.getChildren().add(label);
            vBox.setOnMouseClicked(mouseEvent -> {
                productsClicked(mouseEvent);
            });
            vBox.setAlignment(Pos.CENTER);
            productshbox.getChildren().add(vBox);
        }
    }

    @FXML
    private void menusClicked(MouseEvent selectedMenu) {
        createNewOrderFromExistingMenu(selectedMenu, mainPreMadeOrderMenuList);
    }

    @FXML
    private void productsClicked(MouseEvent selectedMenu) {
        createNewOrderFromExistingMenu(selectedMenu, productsPreMadeOrderMenuList);
    }

    private void createNewOrderFromExistingMenu(MouseEvent selectedMenu, List<PreMadeOrderMenu> baseMenuList) {
        String pressedGuiElementText = getTextFromGuiElementAsString(selectedMenu);
        PreMadeOrderMenu originalPreMadeOrderMenu = PreMadeOrderMenuService.getOriginalPremadeOrderByMenuName(baseMenuList, pressedGuiElementText);
        CustomerOrderService.createNewOrderItem(pressedGuiElementText, originalPreMadeOrderMenu);
        displayMenuItemsChoiceView();
    }

    private String getTextFromGuiElementAsString(MouseEvent clickedElement) {
        VBox vBox = (VBox) clickedElement.getSource();
        Label label = (Label) vBox.getChildren().get(1);
        return label.getText();
    }

    private void displayMenuItemsChoiceView() {
        viewManager.displayFmxlScreen("../view/CustomerMenuIngredientsChoice.fxml");
    }

    public void finishOrderPressed(ActionEvent actionEvent) {
        CustomerOrderService.saveCustomerOrder();
        viewManager.displayFmxlScreen("../view/CustomerReceiptScreen.fxml");
    }

    public void cancelOrderPressed(ActionEvent actionEvent) {
        CustomerOrder c = CustomerOrderService.customerOrder;
        CustomerOrderService.cancelCustomerOrder();
        viewManager.displayFmxlScreen("../view/CustomerLoginScreen.fxml");
    }
}
