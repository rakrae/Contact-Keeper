package controller;

import java.net.URL;
import java.util.ResourceBundle;

import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AccountController extends BaseController {

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
    	navigateTo(PERSISTANCE_NAME_LOGIN, (Stage) back.getScene().getWindow());
    }

    @FXML
    void handleClosePressed(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void handleContactsPressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) contacts.getScene().getWindow());
    }

    @FXML
    void handleDeleteAccountPressed(ActionEvent event) {
    	accountRepository.delete(getLoggedInAccount());
    	navigateTo(PERSISTANCE_NAME_LOGIN, (Stage) deleteAccount.getScene().getWindow());
    }

    @FXML
    void handleEditAccountPressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_EDITACCOUNT, (Stage) editAccount.getScene().getWindow());
    }

    @FXML
    void initialize() {
    	assertInjected();
    }

}
