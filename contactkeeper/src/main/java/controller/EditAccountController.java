package controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.mindrot.jbcrypt.BCrypt;

import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;

public class EditAccountController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ageTextField;

    @FXML
    private Button cancel;

    @FXML
    private Label errorLabelMessages;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private PasswordField newPasswordTextField;

    @FXML
    private PasswordField oldPasswordTextField;

    @FXML
    private PasswordField reEnterPasswordTextField;

    @FXML
    private Button saveChanges;

    @FXML
    void handleCancelPressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_ACCOUNT, (Stage) cancel.getScene().getWindow());
    }

    @FXML
    void handleSaveChangesPressed(ActionEvent event) {
    	Account loggedInAccount = getLoggedInAccount();

		loggedInAccount.setFirstName(firstNameTextField.getText());
		loggedInAccount.setLastName(lastNameTextField.getText());
		loggedInAccount.setAge(Integer.parseInt(ageTextField.getText()));
		loggedInAccount.setGender(genderChoiceBox.getSelectionModel().getSelectedItem());

		String oldPassword = oldPasswordTextField.getText();
		String newPassword = newPasswordTextField.getText();
		String reEnteredPassword = reEnterPasswordTextField.getText();

		if (!oldPassword.isEmpty() || !newPassword.isEmpty() || !reEnteredPassword.isEmpty()) {
			
			// checks if the new password also matches regex
			if(!newPassword.matches(PASSWORD_REGEX)) {
				alertPassword();
	            return;
			}
			
			// checks the validity of the entered passwords and it hashes the new one
            if (validatePasswordChange(oldPassword, newPassword, reEnteredPassword, loggedInAccount)) {
            	String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                loggedInAccount.setPassword(newHashedPassword);
            } else {
                errorLabelMessages.setText("Password change validation failed.");
                return;
            }
        }

		accountRepository.update(loggedInAccount);
		navigateTo(PERSISTANCE_NAME_ACCOUNT, (Stage) saveChanges.getScene().getWindow());
	}

    // checks the validity of the password with the one from database hashed
	private boolean validatePasswordChange(String oldPassword, String newPassword, String reEnteredPassword,
			Account loggedInAccount) {
		if (oldPassword.isEmpty() || newPassword.isEmpty() || !newPassword.equals(reEnteredPassword)) {
            return false;
		}
		
		String hashedPassword = loggedInAccount.getPassword();
		
		if (!BCrypt.checkpw(oldPassword, hashedPassword)) {
	        return false;
	    }

		return true;
	}

    @FXML
    void initialize() {
        assert ageTextField != null : "fx:id=\"ageTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'EditAccount.fxml'.";
        assert errorLabelMessages != null : "fx:id=\"errorLabelMessages\" was not injected: check your FXML file 'EditAccount.fxml'.";
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
        assert genderChoiceBox != null : "fx:id=\"genderChoiceBox\" was not injected: check your FXML file 'EditAccount.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
        assert newPasswordTextField != null : "fx:id=\"newPasswordTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
        assert oldPasswordTextField != null : "fx:id=\"oldPasswordTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
        assert reEnterPasswordTextField != null : "fx:id=\"reEnterPasswordTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
        assert saveChanges != null : "fx:id=\"saveChanges\" was not injected: check your FXML file 'EditAccount.fxml'.";
        
        genderChoiceBox.getItems().addAll("Male", "Female");
        
     // Initialize the loggedInAccount fields.
     		Account loggedInAccount = getLoggedInAccount();

     		// set the values of the text fields
     		firstNameTextField.setText(loggedInAccount.getFirstName());
     		lastNameTextField.setText(loggedInAccount.getLastName());
     		ageTextField.setText(Integer.toString(loggedInAccount.getAge()));

     		
     		String selectedGender = loggedInAccount.getGender();
     		if(selectedGender != null) {
     			genderChoiceBox.setValue(selectedGender);
     		}
     		
     		// clear the password fields
     		oldPasswordTextField.setText("");
     		newPasswordTextField.setText("");
     		reEnterPasswordTextField.setText("");
    }

}
