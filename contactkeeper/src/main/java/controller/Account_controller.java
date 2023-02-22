package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Account_controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label accountText;

    @FXML
    private Button back;

    @FXML
    private Button close;

    @FXML
    private Button contacts;

    @FXML
    private Button deleteAccount;

    @FXML
    private Button editAccount;

    @FXML
    void handleBackPressed(ActionEvent event) {

    }

    @FXML
    void handleClosePressed(ActionEvent event) {

    }

    @FXML
    void handleContactsPressed(ActionEvent event) {

    }

    @FXML
    void handleDeleteAccountPressed(ActionEvent event) {

    }

    @FXML
    void handleEditAccountPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert accountText != null : "fx:id=\"accountText\" was not injected: check your FXML file 'Account.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Account.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Account.fxml'.";
        assert contacts != null : "fx:id=\"contacts\" was not injected: check your FXML file 'Account.fxml'.";
        assert deleteAccount != null : "fx:id=\"deleteAccount\" was not injected: check your FXML file 'Account.fxml'.";
        assert editAccount != null : "fx:id=\"editAccount\" was not injected: check your FXML file 'Account.fxml'.";

    }

}
