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
		assertInjected();
		
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
