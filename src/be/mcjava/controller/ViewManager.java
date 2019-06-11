package be.mcjava.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager {
    public static Scene scene;
    
    public static Stage stage;
    
    public void displayFmxlScreen(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
            scene.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        setStageDimensions(900.0, 510.0);
    }
    
    public static void setStageDimensions(double width, double height) {
        setStageDimensions(width, height, width, height, width, height);
    }
    
    public static void setStageDimensions(double minWidth, double minHeight, double prefWidth, double prefHeight, double maxWidth, double maxHeight) {
        stage.setMinHeight(minHeight);
        stage.setHeight(prefHeight);
        stage.setMaxHeight(maxHeight);
        
        stage.setMinWidth(minWidth);
        stage.setWidth(prefWidth);
        stage.setMaxWidth(maxWidth);
    }
    
    public static void setStageWidth(double width) {
        stage.setMinWidth(width);
        stage.setMaxWidth(width);
        stage.setWidth(width);
    }
    
    public static void setStageHeight(double height) {
        stage.setHeight(height);
        stage.setHeight(height);
        stage.setHeight(height);
    }
}