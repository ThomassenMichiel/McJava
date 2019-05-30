package be.mcjava.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerScreenController {

    //checks if it is a string with only letters.
    public boolean isName(String name){
      return name.chars().allMatch(Character::isLetter);

    }
    //checks valid phone-number
    public boolean isPhoneNumber(String num){
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(num);
        return (m.find() && m.group().equals(num));
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
