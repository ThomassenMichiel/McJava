package be.mcjava.controller;

import be.mcjava.service.CustomerOrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class CustomerScreenController {
    @FXML
    private TextField customername;

    @FXML
    private TextField customerphonenumber;

    //checks if it is a string with only letters.
    private boolean isName(String name) {
        return Pattern.matches( "[a-zA-Z ]+", name );
    }

    //checks valid phone-number
    private boolean isPhoneNumber(String customerPhoneNumber) {
        return Pattern.matches( "0[1-9]{1,2}[/|\\-|0-9]{7,8}", customerPhoneNumber );
    }

    @FXML
    private void continueFromLoginToMenuPressed(ActionEvent event) {
        String telephoneNumbersOnly = customerphonenumber.getText().replaceAll("\\D+", "");
        if(isName(customername.getText()) && isPhoneNumber(telephoneNumbersOnly)) {
    
            CustomerOrderService.startNewCustomerOrder(customername.getText(), customerphonenumber.getText());
    
            ViewManager viewManager = new ViewManager();
            viewManager.displayFmxlScreen("/be/mcjava/view/CustomerMainMenuOverview.fxml");
        }
    }
}
