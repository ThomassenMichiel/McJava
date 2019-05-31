package be.mcjava.view;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class MenuActionController {
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
