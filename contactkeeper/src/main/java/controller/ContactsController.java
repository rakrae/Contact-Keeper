package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ContactsController extends CommonProprietiesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addContact;

    @FXML
    private Button back;

    @FXML
    private Button close;

    @FXML
    private TableColumn<?, ?> contactsColumn;

    @FXML
    private TableView<?> contactsView;

    @FXML
    private TableColumn<?, ?> deleteColumn;

    @FXML
    void handleAddContactPressed(ActionEvent event) {
    	System.out.println("bla bla");
    	//open the add contact scene
    	openScene(PERSISTANCE_NAME_NEWCONTACT);
    	Stage primaryStage = (Stage)back.getScene().getWindow();
    	primaryStage.close();
    	
    }

    @FXML
    void handleBackPressed(ActionEvent event) {
    	//going back to Account
    	openScene(PERSISTANCE_NAME_ACCOUNT);
    	Stage primaryStage = (Stage)addContact.getScene().getWindow();
    	primaryStage.close();
    }

    @FXML
    void handleClosePressed(ActionEvent event) {
    	//Closes the program
    	System.exit(0);
    }

    @FXML
    void initialize() {
        assert addContact != null : "fx:id=\"addContact\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert contactsColumn != null : "fx:id=\"contactsColumn\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert contactsView != null : "fx:id=\"contactsView\" was not injected: check your FXML file 'Contacts.fxml'.";
        assert deleteColumn != null : "fx:id=\"deleteColumn\" was not injected: check your FXML file 'Contacts.fxml'.";

    }

}
