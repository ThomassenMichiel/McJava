package be.mcjava.view;

import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.dao.PreMadeOrderMenuDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class MenuActionController {
    @FXML
    private GridPane maingrid;

    @FXML
    private VBox headingvbox;

    @FXML
    private VBox productsvbox;

    @FXML
    private VBox firstmenuvbox;

    @FXML
    private FlowPane flowpane;

    private List<PreMadeOrderMenu> preMadeOrderMenuList;

    @FXML
    public void initialize() throws FileNotFoundException{
        try {
            getMenuData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addMenusToGrid();
    }

    private void addMenusToGrid() throws FileNotFoundException {
        int columnPosition = 0;
        int rowPosition = 0;
        String path = "resources/menuTextAndImages/";
        headingvbox.setAlignment(Pos.CENTER);
        Label menuHeadingLabel = new Label("McMenus");
        menuHeadingLabel.setTextFill(Color.web("#ffd700", 1));
        headingvbox.getChildren().add(menuHeadingLabel);
        headingvbox.setBackground(Background.EMPTY);
        String greenStyleString = "-fx-background-color: rgba(39,116,45,1);";
        headingvbox.setStyle(greenStyleString);
        for (PreMadeOrderMenu preMadeOrderMenu : preMadeOrderMenuList) {
            VBox vBox = new VBox();
            vBox.getChildren().add(new ImageView(new Image(new FileInputStream(path + preMadeOrderMenu.getpictureName()))));
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

        Label productsLabel = new Label("Products");
        productsLabel.setTextFill(Color.web("#ffd700",1));
        productsvbox.setBackground(Background.EMPTY);
        productsvbox.setStyle(greenStyleString);
        productsvbox.setAlignment(Pos.CENTER);
        productsvbox.getChildren().add(productsLabel);
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
        //flowpane.getChildren().remove(0);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerMenuIngredientsChoice.fxml.fxml"));
        Parent root2 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.show();
    }

    @FXML
    private void drinksClicked(MouseEvent mouseEvent) {
        System.out.println("drinks");
    }
}
