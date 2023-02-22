package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Contacts_controller {

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

    }

    @FXML
    void handleBackPressed(ActionEvent event) {

    }

    @FXML
    void handleClosePressed(ActionEvent event) {

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
