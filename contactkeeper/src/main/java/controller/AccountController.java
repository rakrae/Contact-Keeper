package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccountController extends CommonProprietiesController {

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

    }

    @FXML
    void handleClosePressed(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void handleContactsPressed(ActionEvent event) {
    	// opens the list of contacts
    	openScene(PERSISTANCE_NAME_CONTACTS);
    	Stage primaryStage = (Stage) contacts.getScene().getWindow();
    	primaryStage.close();
    }

    @FXML
    void handleDeleteAccountPressed(ActionEvent event) {
    	//after deleteing the account it should return to the login scene
    	openScene(PERSISTANCE_NAME_LOGIN);
    	Stage primaryStage = (Stage) contacts.getScene().getWindow();
    	primaryStage.close();
    }

    @FXML
    void handleEditAccountPressed(ActionEvent event) {
    	//opening the edit account scene
    	openScene(PERSISTANCE_NAME_EDITCONTACT); 
    	Stage primaryStage = (Stage) contacts.getScene().getWindow();
    	primaryStage.close();
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
