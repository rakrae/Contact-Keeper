package common;

import repository.AccountRepository;
import repository.ContactRepository;
import repository.PhotoRepository;
import java.io.IOException;
import java.lang.reflect.Field;
import application.ApplicationContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Account;
import model.Model;

public class BaseController {
	
	// Regular expression used for passwords
	public static final String PASSWORD_REGEX = "^(?!.*\\\\s)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	private static final String FXML_BASE_PATH = "/fxml/";

	public static final String PERSISTANCE_NAME_LOGIN = FXML_BASE_PATH + "Login.fxml";
	public static final String PERSISTANCE_NAME_ACCOUNT = FXML_BASE_PATH + "Account.fxml";
	public static final String PERSISTANCE_NAME_EDITACCOUNT = FXML_BASE_PATH + "EditAccount.fxml";
	public static final String PERSISTANCE_NAME_NEWACCOUNT = FXML_BASE_PATH + "NewAccount.fxml";
	public static final String PERSISTANCE_NAME_CONTACTS = FXML_BASE_PATH + "Contacts.fxml";
	public static final String PERSISTANCE_NAME_CONTACT = FXML_BASE_PATH + "Contact.fxml";
	public static final String PERSISTANCE_NAME_NEWCONTACT = FXML_BASE_PATH + "NewContact.fxml";
	public static final String PERSISTANCE_NAME_EDITCONTACT = FXML_BASE_PATH + "EditContact.fxml";
	public static final String PERSISTANCE_NAME_PHOTO = FXML_BASE_PATH + "Photo.fxml";

	protected final AccountRepository accountRepository;
	protected final ContactRepository contactRepository;
	protected final PhotoRepository photoRepository;
	protected final Model model;

	public BaseController() {
		accountRepository = ApplicationContext.getAccountRepository();
		contactRepository = ApplicationContext.getContactRepository();
		photoRepository = ApplicationContext.getPhotoRepository();
		model = ApplicationContext.getSelectedModel();
	}

	// Method used in the controllers to get to the next scene or back
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

	// Alert for the password, it checks if it respects the regex
	public void alertPassword() {
		System.out.println("Password must contain at least one lowercase letter, one uppercase letter, one number, one special character, and be at least 8 characters long.");

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);

		// Create a scrollable text area for displaying the error message
		TextArea textArea = new TextArea(
				"Password must contain at least one lowercase letter, one uppercase letter, one number, one special character, and be at least 8 characters long.");
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);

		// Add the text area to the dialog box's content pane
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);
		GridPane contentPane = new GridPane();
		contentPane.setMaxWidth(Double.MAX_VALUE);
		contentPane.add(textArea, 0, 0);
		alert.getDialogPane().setContent(contentPane);

		// Add an OK button to the dialog box
		ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton);

		// Show the dialog box and wait for the user to click the OK button
		alert.showAndWait();
	}

	public void contactDataAllert() {

	}

	@FXML
	protected void assertInjected() {
		Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(FXML.class) && (Node.class.isAssignableFrom(field.getType())
					|| MenuItem.class.isAssignableFrom(field.getType()))) {
				try {
					field.setAccessible(true);
					if (field.get(this) == null) {
						String fieldName = field.getName();
						String className = this.getClass().getSimpleName();
						String message = "fx:id=\"" + fieldName + "\" was not injected: check your FXML file in "
								+ className + ".";
						throw new AssertionError(message);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
