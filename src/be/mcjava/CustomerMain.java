package be.mcjava;

import be.mcjava.controller.ErrorController;
import be.mcjava.controller.ViewManager;
import be.mcjava.dao.DaoConnector;
import be.mcjava.utils.PropertiesLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class CustomerMain extends Application {
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
    private static void checkDbCredentials() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        try {
            final String password = propertiesLoader.getString("password");
            if (Objects.isNull(password) || password.isEmpty()) {
                throw new IllegalArgumentException("The password has not been set!");
            }
            final String jdbcUrl = propertiesLoader.getString("jdbcUrl");
            if (Objects.isNull(jdbcUrl) || jdbcUrl.isEmpty()) {
                throw new IllegalArgumentException("The url has not been set!");
            }
            final String user = propertiesLoader.getString("user");
            if (Objects.isNull(user) || user.isEmpty()) {
                throw new IllegalArgumentException("The user has not been set!");
            }
    
            DaoConnector.setPASSWORD(password);
            DaoConnector.setURL(jdbcUrl);
            DaoConnector.setUSER(user);
        } catch (IllegalArgumentException e) {
            ErrorController.showError(e);
            Platform.exit();
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        ViewManager viewManager = new ViewManager();
        ViewManager.scene = scene;
        ViewManager.stage = stage;
        viewManager.displayFmxlScreen("/be/mcjava/view/CustomerLoginScreen.fxml");
        ViewManager.setStageDimensions(650.0, 450.0);
    
        checkDbCredentials();
        
        stage.setScene(scene);
        stage.show();
    }
}
