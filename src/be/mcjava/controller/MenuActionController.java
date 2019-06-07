package be.mcjava.controller;

import be.mcjava.dao.CustomerOrderDao;
import be.mcjava.model.AbstractOrderItem;
import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.dao.PreMadeOrderMenuDao;
import be.mcjava.service.ChosenProductService;
import be.mcjava.service.CustomerOrderService;
import be.mcjava.service.PreMadeMenuService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class MenuActionController {
    @FXML
    private GridPane maingrid;

    @FXML
    private GridPane productsgrid;

    @FXML HBox productshbox;

    private List<PreMadeOrderMenu> mainPreMadeOrderMenuList;
    private List<PreMadeOrderMenu> productsPremadeOrderMenuList;
    private PreMadeOrderMenuDao preMadeOrderMenuDao;

    private String productsPicturesPath = "resources/menutextandimages/";

    private double combinedImagesWidth = 0;

    @FXML
    public void initialize() throws FileNotFoundException{
        preMadeOrderMenuDao = new PreMadeOrderMenuDao();
        try {
            getMenuData();
            addMenusToGrid();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getMenuData() throws SQLException {
        mainPreMadeOrderMenuList = preMadeOrderMenuDao.populatePreMadeOrderMenuByIdRange(1,4);
        productsPremadeOrderMenuList = preMadeOrderMenuDao.populatePreMadeOrderMenuByIdRange(5,9);
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
            vBox.setOnMouseClicked(mouseEvent -> {
                try {
                    menusClicked(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            vBox.setAlignment(Pos.CENTER);
            maingrid.add(vBox, columnPosition++, rowPosition);
        }
        columnPosition = 0;
        ViewManager.setStageWidth(combinedImagesWidth);
        ViewManager.setStageHeight(600);
        for (PreMadeOrderMenu preMadeOrderMenu : productsPremadeOrderMenuList) {
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
            //productsgrid.add(vBox, columnPosition++, rowPosition);
            productshbox.getChildren().add(vBox);
        }
    }

    @FXML
    private void menusClicked(MouseEvent mouseEvent) throws IOException {
        VBox vBox = (VBox) mouseEvent.getSource();
        Label label = (Label) vBox.getChildren().get(1);
        PreMadeOrderMenu originalPreMadeOrderMenu = mainPreMadeOrderMenuList.stream().filter(p -> p.getName().equals(label.getText())).findFirst().get();

        CustomerOrderService.createNewOrderItem(label.getText(),originalPreMadeOrderMenu);
        /*long id = 1L;
        if(CustomerOrderService.customerOrder.getItemsToOrder() != null) {
            id += CustomerOrderService.customerOrder.getItemsToOrder().size();
        }
        PreMadeOrderMenu preMadeOrderMenu = new PreMadeOrderMenu.Builder()
                .withId(id)
                .withName(label.getText())
                .withPrice(originalPreMadeOrderMenu.getPrice())
                .withAmount(1)
                .build();
        PreMadeMenuService.preMadeOrderMenu = preMadeOrderMenu;*/
        //CustomerOrderService.customerOrder.addItem(preMadeOrderMenu);
        //PreMadeMenuService preMadeMenuService = new PreMadeMenuService();
        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("../view/CustomerMenuIngredientsChoice.fxml");
    }

    @FXML
    private void productsClicked(MouseEvent mouseEvent) {
        VBox vBox = (VBox) mouseEvent.getSource();
        Label label = (Label) vBox.getChildren().get(1);
        ChosenProductService.preMadeMenu = productsPremadeOrderMenuList.stream().filter(p -> p.getName().equals(label.getText())).findFirst().get();
        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("../view/CustomerMenuIngredientsChoice.fxml");
    }

    public void finishOrderPressed(ActionEvent actionEvent) {
        System.out.println("ordered");
        System.out.println("-------");
        for (AbstractOrderItem orderItem : CustomerOrderService.customerOrder.getItemsToOrder()) {
            PreMadeOrderMenu p = (PreMadeOrderMenu) orderItem;
            System.out.println(p.getName());
            System.out.println(p.getPrice());
            p.getItems().forEach(i -> System.out.println("      " + i.getItems().getName()));
        }
        System.out.println("---------------------");
        System.out.println(CustomerOrderService.customerOrder.getFinalPrice());
        CustomerOrderDao.saveCustomerOrder(CustomerOrderService.customerOrder);
    }
}
