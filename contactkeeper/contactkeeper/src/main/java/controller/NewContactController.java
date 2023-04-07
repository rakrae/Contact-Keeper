package controller;

import java.net.URL;
import java.util.ResourceBundle;

import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;

public class NewContactController extends BaseController {

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
    	navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) close.getScene().getWindow());
    }

    @FXML
    void handleSaveChangesPressed(ActionEvent event) {
    	createAndSaveNewContact();
    	navigateToContacts();
    }

    private void createAndSaveNewContact() {
        Contact newContact = new Contact(
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                addressTextField.getText(),
                Integer.parseInt(phoneNumberTextField.getText()),
                emailTextField.getText(),
                facebookTextField.getText(),
                linkedInTextField.getText(),
                instagramTextField.getText());

        newContact.setAccount(getLoggedInAccount());
        contactRepository.save(newContact);
    }
    
    private void navigateToContacts() {
    	navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) close.getScene().getWindow());
    }


    @FXML
    void initialize() {
    	assertInjected();
    }
}
