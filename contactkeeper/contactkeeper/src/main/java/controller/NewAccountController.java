package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;

public class NewAccountController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private Button cancelCreate;

    @FXML
    private Button createNewAccount;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField genderTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField reenterpasswordTextField;
    
    @FXML
    private Label errorMessageLabel;
    
    @FXML
    void handleCancelPressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_LOGIN, (Stage) userNameTextField.getScene().getWindow());
    }

    @FXML
    void handleCreateNewAccountPressed(ActionEvent event) {
        String userName = userNameTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String password = passwordTextField.getText();
        String reenterPassword = reenterpasswordTextField.getText();
        String gender = genderTextField.getText();
        String ageText = ageTextField.getText();

        validateAndCreateAccount(userName, firstName, lastName, password, reenterPassword, gender, ageText);
    }

    private void validateAndCreateAccount(String userName, String firstName, String lastName,
                                          String password, String reenterPassword, String gender, String ageText) {
        errorMessageLabel.setText("");

        if (userName.isEmpty()) {
            errorMessageLabel.setText("Username cannot be empty.");
            return;
        }

        if (firstName.isEmpty()) {
            errorMessageLabel.setText("First name cannot be empty.");
            return;
        }

        if (lastName.isEmpty()) {
            errorMessageLabel.setText("Last name cannot be empty.");
            return;
        }

        if (password.isEmpty()) {
            errorMessageLabel.setText("Password cannot be empty.");
            return;
        }

        if (reenterPassword.isEmpty()) {
            errorMessageLabel.setText("Re-entered password cannot be empty.");
            return;
        }

        if (gender.isEmpty()) {
            errorMessageLabel.setText("Gender cannot be empty.");
            return;
        }

        if (ageText.isEmpty()) {
            errorMessageLabel.setText("Age cannot be empty.");
            return;
        }

        if (!password.equals(reenterPassword)) {
            errorMessageLabel.setText("Passwords do not match.");
            return;
        }

        if (!isAgeValid(ageText)) {
            errorMessageLabel.setText("Age must be a positive number.");
            return;
        }

        createNewAccount(userName, firstName, lastName, password, gender, ageText);
    }

    private void createNewAccount(String userName, String firstName, String lastName, String password, String gender, String ageText) {
        Optional<Account> existingAccount = accountRepository.findByUserName(userName);
        if (existingAccount.isPresent()) {
            errorMessageLabel.setText("An account with this username already exists.");
            return;
        }

        int age = Integer.parseInt(ageText);
        Account newAccount = new Account(userName, password, firstName, lastName, gender, age);
        newAccount = accountRepository.save(newAccount);
        
        setLoggedInAccount(newAccount);
        navigateToAccount();
    }

    private boolean isAgeValid(String ageText) {
        try {
            int age = Integer.parseInt(ageText);
            return age > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private void navigateToAccount() {
        navigateTo(PERSISTANCE_NAME_ACCOUNT, (Stage) userNameTextField.getScene().getWindow());
    }
    
    @FXML
    void initialize() {
    	assertInjected();
    }
}
