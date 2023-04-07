package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<?, ?> contactColumn;

    @FXML
    private TableView<?> contactView;

    @FXML
    private Button contacts;

    @FXML
    private Button deleteAccount;

    @FXML
    private Button editAccount;

    @FXML
    private TextField searchTextField;

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
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to delete this account?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
    	accountRepository.delete(getLoggedInAccount());
    	navigateTo(PERSISTANCE_NAME_LOGIN, (Stage) deleteAccount.getScene().getWindow());
        }
    }

    @FXML
    void handleEditAccountPressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_EDITACCOUNT, (Stage) editAccount.getScene().getWindow());
    }

    @FXML
    void initialize() {
        assert accountText != null : "fx:id=\"accountText\" was not injected: check your FXML file 'Account.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Account.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Account.fxml'.";
        assert contactColumn != null : "fx:id=\"contactColumn\" was not injected: check your FXML file 'Account.fxml'.";
        assert contactView != null : "fx:id=\"contactView\" was not injected: check your FXML file 'Account.fxml'.";
        assert contacts != null : "fx:id=\"contacts\" was not injected: check your FXML file 'Account.fxml'.";
        assert deleteAccount != null : "fx:id=\"deleteAccount\" was not injected: check your FXML file 'Account.fxml'.";
        assert editAccount != null : "fx:id=\"editAccount\" was not injected: check your FXML file 'Account.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'Account.fxml'.";

    }

}
