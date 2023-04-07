package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;

public class LoginController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Button close;

    @FXML
    private Button login;

    @FXML
    private Button newAccount;

    @FXML
    private PasswordField passwordTextField;
    
    @FXML
	private Label invalidCredentialsLabel;
    
    @FXML
    void handleClosePressed(ActionEvent event) {
    	System.exit(0);
    }

	@FXML
	void handleLoginPressed(ActionEvent event) {
		String userName = userNameTextField.getText();
		String password = passwordTextField.getText();

		if (validateLoginInput(userName, password)) {
			attemptLogin(userName, password);
		} else {
			invalidCredentialsLabel.setText("Username and password must not be empty.");
		}
	}

	private boolean validateLoginInput(String userName, String password) {
		return !userName.isEmpty() && !password.isEmpty();
	}

	private void attemptLogin(String userName, String password) {
		Optional<Account> optAccount = accountRepository.findByUserName(userName);
		if (optAccount.isPresent() && optAccount.get().getPassword().equals(password)) {
			setLoggedInAccount(optAccount.get());
			navigateTo(PERSISTANCE_NAME_ACCOUNT, (Stage) userNameTextField.getScene().getWindow());
		} else {
			invalidCredentialsLabel.setText("Invalid username or password.");
		}
	}

    @FXML
    void handleNewAccountPressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_NEWACCOUNT, (Stage) userNameTextField.getScene().getWindow());
    }

    @FXML
    void initialize() {
    	assertInjected();
    }
}
