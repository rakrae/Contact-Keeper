package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewAccount_controller extends CommonProprietiesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField accountTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private Button cancelCreate;

    @FXML
    private Button createNewAccount;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField genderTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField reenterpasswordTextField;

    @FXML
    void handleCancelPressed(ActionEvent event) {

    }

    @FXML
    void handleCreateNewAccountPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert accountTextField != null : "fx:id=\"accountTextField\" was not injected: check your FXML file 'NewAccount.fxml'.";
        assert ageTextField != null : "fx:id=\"ageTextField\" was not injected: check your FXML file 'NewAccount.fxml'.";
        assert cancelCreate != null : "fx:id=\"cancelCreate\" was not injected: check your FXML file 'NewAccount.fxml'.";
        assert createNewAccount != null : "fx:id=\"createNewAccount\" was not injected: check your FXML file 'NewAccount.fxml'.";
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'NewAccount.fxml'.";
        assert genderTextField != null : "fx:id=\"genderTextField\" was not injected: check your FXML file 'NewAccount.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'NewAccount.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'NewAccount.fxml'.";
        assert reenterpasswordTextField != null : "fx:id=\"reenterpasswordTextField\" was not injected: check your FXML file 'NewAccount.fxml'.";

    }

}
