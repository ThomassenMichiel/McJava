package be.mcjava.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerScreenController {
    @FXML
    private void continuePressed(ActionEvent event) throws Exception{
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerScreen2.fxml"));
     Parent root1 = fxmlLoader.load();
     Stage stage = new Stage();
     stage.setScene(new Scene(root1));
     stage.show();
    }
}
