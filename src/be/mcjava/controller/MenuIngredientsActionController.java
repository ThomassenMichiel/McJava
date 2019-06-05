package be.mcjava.controller;

import be.mcjava.dao.ProductsDao;
import be.mcjava.model.Product;
import be.mcjava.service.ChosenProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        System.out.println(ChosenProductService.preMadeMenu.getName());

        ProductsDao productsDao = new ProductsDao();
        List<Product> productList = productsDao.getIngredientsByPremadeMenuTitle(ChosenProductService.preMadeMenu.getName());
        VBox vBox = new VBox();
        for (Product product : productList) {
            Button label = new Button(product.getName());
            vBox.getChildren().add(label);
            System.out.println(product.getName());
        }
        mainproductsgrid.add(vBox,0,0);
    }
}
