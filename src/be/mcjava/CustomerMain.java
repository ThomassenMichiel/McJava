package be.mcjava;

import be.mcjava.controller.ViewManager;
import be.mcjava.model.*;
import be.mcjava.service.CustomerOrderService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.*;

public class CustomerMain extends Application {

    public static void main(String[] args) {
	    launch( args );
    }

    @Override
    public void start(Stage stage) throws Exception{
        dummies();
        /*Parent root = FXMLLoader.load( getClass().getResource( "view/CustomerLoginScreen.fxml" ) );
        Scene scene = new Scene( root, 650, 450 );
        stage.setScene( scene );
        stage.show();*/

        //Scene scene = new Scene(new StackPane());
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        ViewManager viewManager = new ViewManager();
        ViewManager.scene = scene;
        ViewManager.stage = stage;
        viewManager.displayFmxlScreen("../view/CustomerLoginScreen.fxml");
        ViewManager.setStageDimensions(650.0,450.0);

        stage.setScene(scene);
        stage.show();
    }
    
    private static void dummies() {
        Ingredient tomato = new Ingredient(1L, "Tomato", 5);
        Ingredient patty = new Ingredient(2L, "Patty", 20);
        Ingredient bun = new Ingredient(3L, "Bun", 20);
        Ingredient fries = new Ingredient(3L, "Fries", 20);
        Ingredient tropicana = new Ingredient(4L, "Tropicana", 20);
        
        Map<Ingredient, Integer> tropicanaIngredient = new HashMap<>();
        tropicanaIngredient.put(tropicana, 1);
        
        Map<Ingredient, Integer> happyMealMenu = new HashMap<>();
        happyMealMenu.put(tomato, 1);
        happyMealMenu.put(patty, 1);
        happyMealMenu.put(bun, 1);
        happyMealMenu.put(fries, 1);
        
        Product tropicanaProduct = new Product.Builder("Tropicana")
                .withId(1L)
                .withPrice(new BigDecimal("3.33"))
                .withIngredients(tropicanaIngredient)
                .build();
        
        Product happyMealProduct = new Product.Builder("Happy Meal")
                .withId(2L)
                .withIngredients(happyMealMenu)
                .withPrice(BigDecimal.valueOf(Double.MAX_VALUE))
                .build();
        
        SingleOrderItem singleTropicanaOrder = new SingleOrderItem.Builder(tropicanaProduct)
                .withId(1L)
                .withAmount(1)
                .withPrice(tropicanaProduct.getPrice())
                .build();
        
        SingleOrderItem happyMealOI = new SingleOrderItem.Builder(happyMealProduct)
                .withId(1L)
                .withAmount(1)
                .withPrice(tropicanaProduct.getPrice())
                .build();
        
        ArrayList<SingleOrderItem> happyMealMcMenu = new ArrayList<>();
        happyMealMcMenu.add(happyMealOI);
        happyMealMcMenu.add(singleTropicanaOrder);
        
        PreMadeOrderMenu happyMeal = new PreMadeOrderMenu.Builder()
                .withId(1L)
                .withAmount(1)
                .withPrice(new BigDecimal(10))
                .withName("Happy Meal")
                .withProduct(happyMealMcMenu)
                .build();
        
        List<AbstractOrderItem> order = new ArrayList<>();
        order.add(singleTropicanaOrder);
        order.add(happyMeal);
    
        CustomerOrderService.customerOrder = new CustomerOrder.Builder()
                .name("REEEEEEEEEEEEEEEEEEEEE")
                .telephoneNumber("0123456798")
                .withId(1L)
                .withItemsToOrder(order)
                .build();
    }
}
