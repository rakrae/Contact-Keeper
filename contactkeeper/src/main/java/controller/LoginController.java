package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends CommonProprietiesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField accountTextField;

    @FXML
    private Button close;

    @FXML
    private Button login;

    @FXML
    private Button newAccount;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    void handleClosePressed(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void handleLoginPressed(ActionEvent event) {
    	//opens account scene
    	openScene(PERSISTANCE_NAME_ACCOUNT);
    	
    	Stage primaryStage = (Stage) accountTextField.getScene().getWindow();
    	primaryStage.close();
    }

    @FXML
    void handleNewAccountPressed(ActionEvent event) {
    	//opens new account scene
    	openScene(PERSISTANCE_NAME_NEWACCOUNT);
    	
    	Stage primaryStage = (Stage) accountTextField.getScene().getWindow();
    	primaryStage.close();
    }

    @FXML
    void initialize() {
        assert accountTextField != null : "fx:id=\"accountTextField\" was not injected: check your FXML file 'Login.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Login.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'Login.fxml'.";
        assert newAccount != null : "fx:id=\"newAccount\" was not injected: check your FXML file 'Login.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'Login.fxml'.";

    }

}
