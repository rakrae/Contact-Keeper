package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.ApplicationContext;
import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private TextField birthdayTextField;

    @FXML
    private Button close;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField facebookTextField;

    @FXML
    private TextField firstNameTextField;
    
    @FXML
    private ChoiceBox<String> genderChoiceBox;

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
    	
    	selectedContact.setBirthday(birthdayTextField.getText());
    	selectedContact.setAddress(addressTextField.getText());
        selectedContact.setEmail(emailTextField.getText());
        selectedContact.setFacebook(facebookTextField.getText());
        selectedContact.setFirstName(firstNameTextField.getText());
        selectedContact.setGender(genderChoiceBox.getSelectionModel().getSelectedItem());
        selectedContact.setInstagram(instagramTextField.getText());
        selectedContact.setLastName(lastNameTextField.getText());
        selectedContact.setLinkedIn(linkedInTextField.getText());
        selectedContact.setPhoneNumber(phoneNumberTextField.getText());
        contactRepository.update(selectedContact);
        
    	navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) saveChanges.getScene().getWindow());
    }

    @FXML
    void initialize() {
        assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert birthdayTextField != null : "fx:id=\"birthdayTextField\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert facebookTextField != null : "fx:id=\"facebookTextField\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert genderChoiceBox != null : "fx:id=\"genderChoiceBox\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert instagramTextField != null : "fx:id=\"instagramTextField\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert linkedInTextField != null : "fx:id=\"linkedInTextField\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'EditContact.fxml'.";
        assert saveChanges != null : "fx:id=\"saveChanges\" was not injected: check your FXML file 'EditContact.fxml'.";

        genderChoiceBox.getItems().addAll("Male", "Female");
        
        selectedContact = ApplicationContext.getSelectedContact();
        
        birthdayTextField.setText(selectedContact.getBirthday());
        firstNameTextField.setText(selectedContact.getFirstName());
        lastNameTextField.setText(selectedContact.getLastName());
        
        String selectedGender = selectedContact.getGender();
        if(selectedGender != null) {
        	genderChoiceBox.setValue(selectedGender);
        }
        
        phoneNumberTextField.setText(selectedContact.getPhoneNumber());
        emailTextField.setText(selectedContact.getEmail());
        addressTextField.setText(selectedContact.getAddress());
        facebookTextField.setText(selectedContact.getFacebook());
        instagramTextField.setText(selectedContact.getInstagram());
        linkedInTextField.setText(selectedContact.getLinkedIn());
    }

}
