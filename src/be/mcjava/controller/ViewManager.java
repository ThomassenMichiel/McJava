package be.mcjava.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        stage.setMinWidth(width);
        stage.setMaxWidth(width);
        stage.setWidth(width);
        stage.setMinHeight(height);
        stage.setMaxHeight(height);
        stage.setHeight(height);
    }

    public static void setStageWidth(double width){
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