package be.mcjava.controller;

import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.dao.PreMadeOrderMenuDao;
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

    private List<PreMadeOrderMenu> preMadeOrderMenuList;

    private String productsPicturesPath = "resources/menutextandimages/";

    private double combinedImagesWidth = 0;

    @FXML
    public void initialize() throws FileNotFoundException{
        try {
            getMenuData();
            addMenusToGrid();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addMenusToGrid() throws FileNotFoundException {
        int columnPosition = 0;
        int rowPosition = 0;


        for (PreMadeOrderMenu preMadeOrderMenu : preMadeOrderMenuList) {
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
        ViewManager.setStageWidth(combinedImagesWidth);
    }

    private void getMenuData() throws SQLException {
        PreMadeOrderMenuDao preMadeOrderMenuDao = new PreMadeOrderMenuDao();
        preMadeOrderMenuList = preMadeOrderMenuDao.populatePreMadeOrderMenu();
    }

    @FXML
    private void menusClicked(MouseEvent mouseEvent) throws IOException {
        System.out.println("menus clicked");
        System.out.println("-------------");
        VBox vBox = (VBox) mouseEvent.getSource();
        Label label = (Label) vBox.getChildren().get(1);
        System.out.println(label.getText());

        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("../view/CustomerMenuIngredientsChoice.fxml");
    }

    @FXML
    private void drinksClicked(MouseEvent mouseEvent) {
        System.out.println("drinks");
    }
}
