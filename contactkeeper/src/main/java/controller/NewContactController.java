package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import model.Gender;

public class NewContactController extends BaseController {

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
    private ChoiceBox<Gender> genderChoiceBox;

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
    
    private static final String PHONE_NUMBER_REGEX = "^\\d{3}-\\d{3}-\\d{4}$"; // e.g. 123-456-7890
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // e.g. example@example.com

    private boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            return true;
        }
        return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber);
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            return true;
        }
        return Pattern.matches(EMAIL_REGEX, email);
    }

    
    private void createAndSaveNewContact() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate birthday = null;
    	
    	String birthdayString = birthdayTextField.getText();
        if (!birthdayString.isEmpty()) {
            try {
                birthday = LocalDate.parse(birthdayString, formatter);
            } catch (DateTimeParseException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid date for the birthday (format: dd/MM/yyyy)");
                alert.showAndWait();
                return;
            }
        }

        String phoneNumber = phoneNumberTextField.getText();
        if (!validatePhoneNumber(phoneNumber)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Phone Number");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid phone number (e.g. 123-456-7890)");
            alert.showAndWait();
            return;
        }

        String email = emailTextField.getText();
        if (!validateEmail(email)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Email Address");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address (e.g. example@example.com)");
            alert.showAndWait();
            return;
        }
    	
    	// Comment set initially to ""
        Contact newContact = new Contact(
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                genderChoiceBox.getSelectionModel().getSelectedItem(),
                birthday, //LocalDate
                addressTextField.getText(),
                phoneNumber, // REGEX
                email,// REGEX 
                facebookTextField.getText(),
                linkedInTextField.getText(),
                instagramTextField.getText(),
                "");

        newContact.setAccount(getLoggedInAccount());
        contactRepository.save(newContact);
        
        System.out.println("New contact created");
    }
    
    private void navigateToContacts() {
    	navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) close.getScene().getWindow());
    }


    @FXML
    void initialize() {
        assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert birthdayTextField != null : "fx:id=\"birthdayTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert facebookTextField != null : "fx:id=\"facebookTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert genderChoiceBox != null : "fx:id=\"genderChoiceBox\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert instagramTextField != null : "fx:id=\"instagramTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert linkedInTextField != null : "fx:id=\"linkedInTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'NewContact.fxml'.";
        assert saveChanges != null : "fx:id=\"saveChanges\" was not injected: check your FXML file 'NewContact.fxml'.";
    
        genderChoiceBox.getItems().addAll(Gender.MALE, Gender.FEMALE);
    }

}
