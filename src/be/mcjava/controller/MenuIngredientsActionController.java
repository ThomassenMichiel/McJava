package be.mcjava.controller;

import be.mcjava.dao.ProductsDao;
import be.mcjava.model.Product;
import be.mcjava.service.ChosenProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class MenuIngredientsActionController {
    @FXML
    private HBox headerhbox;

    @FXML
    private GridPane mainproductsgrid;

    @FXML
    public void initialize(){
        ProductsDao productsDao = new ProductsDao();
        List<Product> productList = productsDao.getProductsByPremadeMenuTitle(ChosenProductService.preMadeMenu.getName());
        VBox vBox = new VBox();
        for (Product product : productList) {
            Button button = new Button(product.getName());
            button.setOnMouseClicked(mouseEvent -> menuProductClicked(mouseEvent));
            vBox.getChildren().add(button);
            System.out.println(product.getName());
        }
        mainproductsgrid.add(vBox,0,0);
    }

    @FXML
    private void menuProductClicked(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        System.out.println("clicked on -> " + button.getText());
    }
}
