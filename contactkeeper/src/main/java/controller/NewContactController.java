package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewContactController extends CommonProprietiesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addressTextField;

    @FXML
    private Button close;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField facebookTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField instagramTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField linkedInTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Button saveChanges;

    @FXML
    void handleClosePressed(ActionEvent event) {

    }

    @FXML
    void handleSaveChangesPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert facebookTextField != null : "fx:id=\"facebookTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert instagramTextField != null : "fx:id=\"instagramTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert linkedInTextField != null : "fx:id=\"linkedInTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert saveChanges != null : "fx:id=\"saveChanges\" was not injected: check your FXML file 'NewContact.fxml'.";

    }

}
