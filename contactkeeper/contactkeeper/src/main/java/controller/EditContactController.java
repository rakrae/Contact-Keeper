package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.ApplicationContext;
import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;

public class EditContactController extends BaseController {

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
    
    private Contact selectedContact;

    @FXML
    void handleClosePressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) saveChanges.getScene().getWindow());
    }

    @FXML
    void handleSaveChangesPressed(ActionEvent event) {
    	selectedContact.setAddress(addressTextField.getText());
        selectedContact.setEmail(emailTextField.getText());
        selectedContact.setFacebook(facebookTextField.getText());
        selectedContact.setFirstName(firstNameTextField.getText());
        selectedContact.setInstagram(instagramTextField.getText());
        selectedContact.setLastName(lastNameTextField.getText());
        selectedContact.setLinkedIn(linkedInTextField.getText());
        selectedContact.setPhoneNumber(Integer.parseInt(phoneNumberTextField.getText()));
        contactRepository.update(selectedContact);
        
    	navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) saveChanges.getScene().getWindow());
    }

    @FXML
    void initialize() {
    	assertInjected();

        selectedContact = ApplicationContext.getSelectedContact();

        firstNameTextField.setText(selectedContact.getFirstName());
        lastNameTextField.setText(selectedContact.getLastName());
        phoneNumberTextField.setText(Integer.toString(selectedContact.getPhoneNumber()));
        emailTextField.setText(selectedContact.getEmail());
        addressTextField.setText(selectedContact.getAddress());
        facebookTextField.setText(selectedContact.getFacebook());
        instagramTextField.setText(selectedContact.getInstagram());
        linkedInTextField.setText(selectedContact.getLinkedIn());
    }

}
