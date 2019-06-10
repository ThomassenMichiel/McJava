package be.mcjava.controller;

import be.mcjava.model.CustomerOrder;
import be.mcjava.service.CustomerOrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerScreenController {
    @FXML
    TextField customername;

    @FXML
    TextField customerphonenumber;

    //checks if it is a string with only letters.
    public boolean isName(String name) {
        return Pattern.matches( "[a-zA-Z]+", name );
    }

    //checks valid phone-number
    public boolean isPhoneNumber(String customerPhoneNumber) {
        return Pattern.matches( "0[0-9]{8,9}", customerPhoneNumber );
    }

    @FXML
    private void continueFromLoginToMenuPressed(ActionEvent event) throws Exception {
        //if(isName(customername.getText()) && isPhoneNumber(customerphonenumber.getText())) {

        CustomerOrderService.startNewCustomerOrder(customername.getText(),customerphonenumber.getText());

        ViewManager viewManager = new ViewManager();
        viewManager.displayFmxlScreen("/be/mcjava/view/CustomerMainMenuOverview.fxml");
    }
}
