package be.mcjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerMain extends Application {

    public static void main(String[] args) {
	    launch( args );
    }
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load( getClass().getResource( "mcJavaCustomerFXML/CustomerScreen.fxml" ) );
        Scene scene = new Scene( root, 650,450 );
        stage.setScene( scene );
        stage.show();
    }
}
