package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import application.ApplicationContext;
import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import model.Gender; 

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

    private Contact selectedContact;
    
    private static final String PHONE_NUMBER_REGEX = "^\\d{3}-\\d{3}-\\d{4}$"; // e.g. 123-456-7890
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // e.g. example@example.com
    private static final DateTimeFormatter BIRTHDAY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
    
    @FXML
    void handleClosePressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) saveChanges.getScene().getWindow());
    }

    @FXML
    void handleSaveChangesPressed(ActionEvent event) {
    	
    	LocalDate birthday = null;

    	String birthdayString = birthdayTextField.getText();
        if (!birthdayString.isEmpty()) {
            try {
                birthday = LocalDate.parse(birthdayString, BIRTHDAY_FORMATTER);
            } catch (DateTimeParseException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid date for the birthday (format: dd/MM/yyyy)");
                alert.showAndWait();
                return;
            }
        }

        if (!validatePhoneNumber(phoneNumberTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid phone number (format: xxx-xxx-xxxx)");
            alert.showAndWait();
            return;
        }

        if (!validateEmail(emailTextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address");
            alert.showAndWait();
            return;
        }
    	
    
    	selectedContact.setBirthday(birthday);
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
        
        System.out.println("Contact edited");
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

        genderChoiceBox.getItems().addAll(Gender.MALE, Gender.FEMALE);
        
        selectedContact = ApplicationContext.getSelectedContact();
        
        if (selectedContact.getBirthday() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String birthdayString = selectedContact.getBirthday().format(formatter);
            birthdayTextField.setText(birthdayString);
        } else {
            birthdayTextField.setText("");
        }	
        firstNameTextField.setText(selectedContact.getFirstName());
        lastNameTextField.setText(selectedContact.getLastName());
        
        genderChoiceBox.setValue(selectedContact.getGender());

        phoneNumberTextField.setText(selectedContact.getPhoneNumber());
        emailTextField.setText(selectedContact.getEmail());
        addressTextField.setText(selectedContact.getAddress());
        facebookTextField.setText(selectedContact.getFacebook());
        instagramTextField.setText(selectedContact.getInstagram());
        linkedInTextField.setText(selectedContact.getLinkedIn());
    }

}
