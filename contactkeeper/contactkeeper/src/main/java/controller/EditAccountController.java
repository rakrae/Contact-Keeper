package controller;

import java.net.URL;
import java.util.ResourceBundle;

import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private TextField firstNameTextField;

	@FXML
	private TextField genderTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField newPasswordTextField;

	@FXML
	private TextField oldPasswordTextfield;

	@FXML
	private TextField reEnterPassword;

	@FXML
	private Button saveChanges;
	
	@FXML
	private Label errorLabelMessages;
	
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
		loggedInAccount.setGender(genderTextField.getText());

		String oldPassword = oldPasswordTextfield.getText();
		String newPassword = newPasswordTextField.getText();
		String reEnteredPassword = reEnterPassword.getText();

		if (!oldPassword.isEmpty() || !newPassword.isEmpty() || !reEnteredPassword.isEmpty()) {
            if (validatePasswordChange(oldPassword, newPassword, reEnteredPassword, loggedInAccount)) {
                loggedInAccount.setPassword(newPassword);
            } else {
                errorLabelMessages.setText("Password change validation failed.");
                return;
            }
        }

		accountRepository.update(loggedInAccount);
		navigateTo(PERSISTANCE_NAME_ACCOUNT, (Stage) saveChanges.getScene().getWindow());
	}

	private boolean validatePasswordChange(String oldPassword, String newPassword, String reEnteredPassword,
			Account loggedInAccount) {
		if (oldPassword.isEmpty() || newPassword.isEmpty() || !newPassword.equals(reEnteredPassword)) {
            return false;
		}

		return loggedInAccount.getPassword().equals(oldPassword);
	}

	@FXML
	void initialize() {
		assertInjected();

		// Initialize the loggedInAccount fields.
		Account loggedInAccount = getLoggedInAccount();

		// set the values of the text fields
		firstNameTextField.setText(loggedInAccount.getFirstName());
		lastNameTextField.setText(loggedInAccount.getLastName());
		ageTextField.setText(Integer.toString(loggedInAccount.getAge()));
		genderTextField.setText(loggedInAccount.getGender());

		// clear the password fields
		oldPasswordTextfield.setText("");
		newPasswordTextField.setText("");
		reEnterPassword.setText("");
	}

}
