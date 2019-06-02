package be.mcjava.view;

import be.mcjava.model.PreMadeOrderMenu;
import be.mcjava.service.MenuService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;


public class MenuActionController {
    @FXML
    private GridPane maingrid;

    @FXML
    private Button btn;

    private List<PreMadeOrderMenu> preMadeOrderMenuList;

    @FXML
    public void initialize()throws FileNotFoundException {
        int columnPosition = 0;
        int rowPosition = 0;
        String path = "resources/menuTextAndImages/";

        System.out.println("init");
        try {
            getMenuData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //preMadeOrderMenuList.forEach(s -> System.out.println("image: " + s.getpictureName()));
        //maingrid.add(new ImageView(new Image(new FileInputStream("resources/choco.png"))),0,2);

        for (PreMadeOrderMenu preMadeOrderMenu : preMadeOrderMenuList) {
            VBox vBox = new VBox();
            vBox.getChildren().add(new ImageView(new Image(new FileInputStream(path+preMadeOrderMenu.getpictureName()))));
            Label label = new Label(preMadeOrderMenu.getName());
            label.setTextAlignment(TextAlignment.CENTER);
            vBox.getChildren().add(label);
            maingrid.add(vBox,columnPosition++,rowPosition);
        }
    }

    private void getMenuData() throws SQLException {
        MenuService menuService = new MenuService();
        preMadeOrderMenuList = menuService.populateMenus();
    }


    @FXML
    private void imageClicked(MouseEvent mouseEvent) {
        System.out.println(((ImageView)mouseEvent.getSource()).imageProperty().toString());
    }

    @FXML
    private void burgersClicked(MouseEvent mouseEvent) {
        System.out.println("burgers");

    }

    @FXML
    private void menusClicked(MouseEvent mouseEvent) {
        System.out.println("menus");
    }

    @FXML
    private void drinksClicked(MouseEvent mouseEvent) {
        System.out.println("drinks");
    }
}
