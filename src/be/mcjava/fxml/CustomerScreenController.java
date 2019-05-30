package be.mcjava.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerScreenController {

    //checks if it is a string with only letters.
    public boolean isName(String name){
      return name.chars().allMatch(Character::isLetter);

    }
    //checks valid phone-number
    public boolean isPhoneNumber(String num){
        return true;
    }
    @FXML
    private void continuePressed(ActionEvent event) throws Exception{
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerScreen2.fxml"));
     Parent root1 = fxmlLoader.load();
     Stage stage = new Stage();
     stage.setScene(new Scene(root1));
     stage.show();
    }
}
