package be.mcjava.view;

import be.mcjava.service.MenuService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class MenuActionController {
    @FXML
    private GridPane maingrid;

    @FXML
    private Button btn;

    public MenuActionController() throws SQLException {
        MenuService menuService = new MenuService();
        menuService.populateMenus().forEach(p -> System.out.println(p.getName()));

        System.out.println();
        System.out.println("button -> " + btn.getText());
        System.out.println();

        FileInputStream input = null;
        try {
            input = new FileInputStream("resources/choco.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        //maingrid.add(imageView,1,0);
    }

    public void imageClicked(MouseEvent mouseEvent) {
        System.out.println(((ImageView)mouseEvent.getSource()).imageProperty().toString());
    }

    public void burgersClicked(MouseEvent mouseEvent) {
        System.out.println("burgers");

    }

    public void menusClicked(MouseEvent mouseEvent) {
        System.out.println("menus");
    }

    public void drinksClicked(MouseEvent mouseEvent) {
        System.out.println("drinks");
    }
}
