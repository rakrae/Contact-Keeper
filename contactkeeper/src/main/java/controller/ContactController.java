package controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import application.ApplicationContext;
import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Contact;

public class ContactController extends BaseController {
	
	//Second of selectedContact to update the comment
	Contact selectedContact = ApplicationContext.getSelectedContact();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
    @FXML
    private Button addComment;

	@FXML
	private Label addressTextField;

	@FXML
	private Button back;
	
    @FXML
    private Label birthdayTextField;
	
	@FXML
    private Label commentLabel;

    @FXML
    private TextField commentTextField;
    
    @FXML
    private Button deleteComment;

	@FXML
	private Button editContact;

	@FXML
	private Label emailTextField;

	@FXML
	private Label facebookTextField;

	@FXML
	private Label firstNameTextField;
	
    @FXML
    private Label genderTextField;

	@FXML
	private Label instagramTextField;

	@FXML
	private Label lastNameTextField;

	@FXML
	private Label linkedInTextField;

	@FXML
	private Label phoneNumberTextField;
	
    @FXML
    private Button photo;
	
    @FXML
    void handleAddCommentPressed(ActionEvent event) {
    	//update the comment
    	updateComment();
    }

	@FXML
	void handleBackPressed(ActionEvent event) {
		navigateTo(PERSISTANCE_NAME_CONTACTS, (Stage) back.getScene().getWindow());
	}
	
    @FXML
    void handleDeleteCommentPressed(ActionEvent event) {
    	//set the comment to null
    	deleteComment(); 
    }

	@FXML
	void handleEditContactPressed(ActionEvent event) {
		navigateTo(PERSISTANCE_NAME_EDITCONTACT, (Stage) back.getScene().getWindow());
	}
	
    @FXML
    void handlePhotoPressed(ActionEvent event) {
    	navigateTo( PERSISTANCE_NAME_PHOTO, (Stage) back.getScene().getWindow());
    }

	@FXML
	void initialize() {
		   assert addComment != null : "fx:id=\"addComment\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert birthdayTextField != null : "fx:id=\"birthdayTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert commentLabel != null : "fx:id=\"commentLabel\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert commentTextField != null : "fx:id=\"commentTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert deleteComment != null : "fx:id=\"deleteComment\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert editContact != null : "fx:id=\"editContact\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert facebookTextField != null : "fx:id=\"facebookTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert genderTextField != null : "fx:id=\"genderTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert instagramTextField != null : "fx:id=\"instagramTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert linkedInTextField != null : "fx:id=\"linkedInTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'Contact.fxml'.";
	        assert photo != null : "fx:id=\"photo\" was not injected: check your FXML file 'Contact.fxml'.";
		
	        //First use of selectedContact for initialization 
		Contact selectedContact = ApplicationContext.getSelectedContact();
		if (selectedContact != null) {
			firstNameTextField.setText(selectedContact.getFirstName());
			lastNameTextField.setText(selectedContact.getLastName());
			genderTextField.setText(selectedContact.getGender().toString());
			 if (selectedContact.getBirthday() != null) {
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		            String birthdayString = selectedContact.getBirthday().format(formatter);
		            birthdayTextField.setText(birthdayString);
		        } else {
		            birthdayTextField.setText("");
		        }	
			phoneNumberTextField.setText(String.valueOf(selectedContact.getPhoneNumber()));
			emailTextField.setText(selectedContact.getEmail());
			addressTextField.setText(selectedContact.getAddress());
			facebookTextField.setText(selectedContact.getFacebook());
			instagramTextField.setText(selectedContact.getInstagram());
			linkedInTextField.setText(selectedContact.getLinkedIn());
			commentLabel.setText(selectedContact.getComment());
		} else {
	        birthdayTextField.setText("");
	    }
		
	}
	
	public void setContact(Contact contact) {
		if (contact != null) {
			firstNameTextField.setText(contact.getFirstName());
			lastNameTextField.setText(contact.getLastName());
			genderTextField.setText(contact.getGender().toString());;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String birthdayString = selectedContact.getBirthday().format(formatter);
	        birthdayTextField.setText(birthdayString);
			phoneNumberTextField.setText(String.valueOf(contact.getPhoneNumber()));
			emailTextField.setText(contact.getEmail());
			addressTextField.setText(contact.getAddress());
			facebookTextField.setText(contact.getFacebook());
			instagramTextField.setText(contact.getInstagram());
			linkedInTextField.setText(contact.getLinkedIn());
			commentLabel.setText(contact.getComment());
		}
	}

	public void updateComment() {
		
		if(!commentLabel.getText().isEmpty()){
			System.out.println("Delete first the comment");
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Delete first the current comment");
			alert.showAndWait();
		}
		
		if(commentTextField.getText().isEmpty()&& commentLabel.getText().isEmpty()) {
		System.out.println("Empty comment!");
			
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(null);
		alert.setContentText("Write a comment to add");
		alert.showAndWait();
		
		} else { Contact contact = ApplicationContext.getSelectedContact();
		if(!commentTextField.getText().isEmpty()) {
			contact.setComment(commentTextField.getText());
			commentLabel.setText(commentTextField.getText());
			
			contactRepository.update(contact);
			
			System.out.println("Comment added");
			commentTextField.setText("");
			
		} else {
			commentTextField.setText("");
			}
		}
	}
	
	public void deleteComment() {
		
		if(commentLabel.getText().isEmpty()) {
			System.out.println("Comment is empty");
			
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Comment is empty!");
			alert.showAndWait();
		} else {
		Contact contact = ApplicationContext.getSelectedContact();
		if(!commentLabel.getText().isEmpty()) {
			contact.setComment("");
			commentLabel.setText("");
			
			contactRepository.update(contact);
			
			commentTextField.setText("");
			
			System.out.println("Comment deleted");
		} else {
			commentTextField.setText("");
			}
		}
	}

}
	
