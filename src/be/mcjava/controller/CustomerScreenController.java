package be.mcjava.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerScreenController {
    @FXML
    TextField customername;

    @FXML
    TextField customerphonenumber;

    //checks if it is a string with only letters.
    public boolean isName(String name) {
        return name.chars().allMatch(Character::isLetter);
    }

    //checks valid phone-number
    public boolean isPhoneNumber(String customerPhoneNumber) {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(customerPhoneNumber);
        return (m.find() && m.group().equals(customerPhoneNumber));
    }

    @FXML
    private void continueFromLoginToMenuPressed(ActionEvent event) throws Exception {
        //if(isName(customername.getText()) && isPhoneNumber(customerphonenumber.getText())) {

        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("../view/CustomerMainMenuOverview.fxml");
        //ViewManager.setSceneDimensions(1000.0,500.0);
    }
}
