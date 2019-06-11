package be.mcjava;

import be.mcjava.controller.ViewManager;
import be.mcjava.dao.DaoConnector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class KitchenMain extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        DaoConnector.checkDbCredentials();
        
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        ViewManager viewManager = new ViewManager();
        ViewManager.scene = scene;
        ViewManager.stage = stage;
        viewManager.displayFmxlScreen("/be/mcjava/view/KitchenScreen.fxml");
        ViewManager.setStageDimensions(1280,720,1680,1050,1920,1080);
    
        
        
        stage.setScene(scene);
        stage.show();
    }
}

