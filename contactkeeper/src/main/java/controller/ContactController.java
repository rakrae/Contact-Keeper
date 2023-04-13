package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.ApplicationContext;
import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Contact;

public class ContactController extends BaseController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label addressTextField;

	@FXML
	private Button back;

	@FXML
	private Button editContact;

	@FXML
	private Label emailTextField;

	@FXML
	private Label facebookTextField;

	@FXML
	private Label firstNameTextField;

	@FXML
	private Label instagramTextField;

	@FXML
	private Label lastNameTextField;

	@FXML
	private Label linkedInTextField;

	@FXML
	private Label phoneNumberTextField;

	@FXML
	void handleBackPressed(ActionEvent event) {
		navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) back.getScene().getWindow());
	}


	@FXML
	void handleEditContactPressed(ActionEvent event) {
		navigateTo(PERSISTANCE_NAME_EDITCONTACT, (Stage) back.getScene().getWindow());
	}

	@FXML
	void initialize() {
		assert addressTextField != null
				: "fx:id=\"addressTextField\" was not injected: check your FXML file 'Contact.fxml'.";
		assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Contact.fxml'.";
		assert editContact != null : "fx:id=\"editContact\" was not injected: check your FXML file 'Contact.fxml'.";
		assert emailTextField != null
				: "fx:id=\"emailTextField\" was not injected: check your FXML file 'Contact.fxml'.";
		assert facebookTextField != null
				: "fx:id=\"facebookTextField\" was not injected: check your FXML file 'Contact.fxml'.";
		assert firstNameTextField != null
				: "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'Contact.fxml'.";
		assert instagramTextField != null
				: "fx:id=\"instagramTextField\" was not injected: check your FXML file 'Contact.fxml'.";
		assert lastNameTextField != null
				: "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'Contact.fxml'.";
		assert linkedInTextField != null
				: "fx:id=\"linkedInTextField\" was not injected: check your FXML file 'Contact.fxml'.";
		assert phoneNumberTextField != null
				: "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'Contact.fxml'.";
		
		Contact selectedContact = ApplicationContext.getSelectedContact();
		if (selectedContact != null) {
			firstNameTextField.setText(selectedContact.getFirstName());
			lastNameTextField.setText(selectedContact.getLastName());			
			phoneNumberTextField.setText(String.valueOf(selectedContact.getPhoneNumber()));
			emailTextField.setText(selectedContact.getEmail());
			addressTextField.setText(selectedContact.getAddress());
			facebookTextField.setText(selectedContact.getFacebook());
			instagramTextField.setText(selectedContact.getInstagram());
			linkedInTextField.setText(selectedContact.getLinkedIn());
		}
		

	}
	
	public void setContact(Contact contact) {
		if (contact != null) {
			firstNameTextField.setText(contact.getFirstName());
			lastNameTextField.setText(contact.getLastName());
			phoneNumberTextField.setText(String.valueOf(contact.getPhoneNumber()));
			emailTextField.setText(contact.getEmail());
			addressTextField.setText(contact.getAddress());
			facebookTextField.setText(contact.getFacebook());
			instagramTextField.setText(contact.getInstagram());
			linkedInTextField.setText(contact.getLinkedIn());
		}
	}

}


