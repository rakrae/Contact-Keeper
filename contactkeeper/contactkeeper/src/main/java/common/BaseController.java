package common;

import repository.AccountRepository;
import repository.ContactRepository;

import java.io.IOException;
import java.lang.reflect.Field;

import application.ApplicationContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.Account;

public class BaseController {
	private static final String FXML_BASE_PATH = "/fxml/";

    public static final String PERSISTANCE_NAME_LOGIN = FXML_BASE_PATH + "Login.fxml";
    public static final String PERSISTANCE_NAME_ACCOUNT = FXML_BASE_PATH + "Account.fxml";
    public static final String PERSISTANCE_NAME_EDITACCOUNT = FXML_BASE_PATH + "EditAccount.fxml";
    public static final String PERSISTANCE_NAME_NEWACCOUNT = FXML_BASE_PATH + "NewAccount.fxml";
    public static final String PERSISTANCE_NAME_CONTACTS = FXML_BASE_PATH + "Contacts.fxml";
    public static final String PERSISTANCE_NAME_CONTACT = FXML_BASE_PATH + "Contact.fxml";
    public static final String PERSISTANCE_NAME_NEWCONTACT = FXML_BASE_PATH + "NewContact.fxml";
    public static final String PERSISTANCE_NAME_EDITCONTACT = FXML_BASE_PATH + "EditContact.fxml";

    protected final AccountRepository accountRepository;
    protected final ContactRepository contactRepository;

    public BaseController() {
        accountRepository = ApplicationContext.getAccountRepository();
        contactRepository = ApplicationContext.getContactRepository();
    }
    
    protected void navigateTo(String fxmlPath, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected Account getLoggedInAccount() {
    	return ApplicationContext.getLoggedInAccount();
    }
    
    public void setLoggedInAccount(Account account) {
        ApplicationContext.setLoggedInAccount(account);
    }
    
    @FXML
    protected void assertInjected() {
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(FXML.class) 
            		&& (Node.class.isAssignableFrom(field.getType()) 
            		|| MenuItem.class.isAssignableFrom(field.getType()))) {
                try {
                    field.setAccessible(true);
                    if (field.get(this) == null) {
                        String fieldName = field.getName();
                        String className = this.getClass().getSimpleName();
                        String message = "fx:id=\"" + fieldName + "\" was not injected: check your FXML file in " + className + ".";
                        throw new AssertionError(message);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
