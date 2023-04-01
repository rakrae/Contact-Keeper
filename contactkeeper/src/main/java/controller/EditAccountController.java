package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditAccountController extends CommonProprietiesController {

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
	void handleCancelPressed(ActionEvent event) {
		//return to account
		openScene(PERSISTANCE_NAME_ACCOUNT);
		Stage primaryStage = (Stage) cancel.getScene().getWindow();
		primaryStage.close();
	}

	@FXML
	void handleSaveChangesPressed(ActionEvent event) {
		//returning to account after editing 
		openScene(PERSISTANCE_NAME_ACCOUNT);
		Stage primaryStage = (Stage) cancel.getScene().getWindow();
		primaryStage.close();
	}

	@FXML
	void initialize() {
		assert ageTextField != null
				: "fx:id=\"ageTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
		assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'EditAccount.fxml'.";
		assert firstNameTextField != null
				: "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
		assert genderTextField != null
				: "fx:id=\"genderTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
		assert lastNameTextField != null
				: "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
		assert newPasswordTextField != null
				: "fx:id=\"newPasswordTextField\" was not injected: check your FXML file 'EditAccount.fxml'.";
		assert oldPasswordTextfield != null
				: "fx:id=\"oldPasswordTextfield\" was not injected: check your FXML file 'EditAccount.fxml'.";
		assert reEnterPassword != null
				: "fx:id=\"reEnterPassword\" was not injected: check your FXML file 'EditAccount.fxml'.";
		assert saveChanges != null : "fx:id=\"saveChanges\" was not injected: check your FXML file 'EditAccount.fxml'.";

	}

}
