package common;

import repository.AccountRepository;
import repository.ContactRepository;
import repository.PhotoRepository;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JOptionPane;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Account;
import model.Contact;
import model.Photo;

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

	public BaseController() {
		accountRepository = ApplicationContext.getAccountRepository();
		contactRepository = ApplicationContext.getContactRepository();
		photoRepository = ApplicationContext.getPhotoRepository();
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

	// Used in photoController to add a photo
	protected void addPhoto(Button addPhoto, ImageView imageView) {
		Contact contact = ApplicationContext.getSelectedContact();
		Photo photo = contact.getPhoto();
		
		if (photo != null) {
			System.out.println("Please update!");
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Please update!");
			alert.showAndWait();
		} else {
			FileChooser fileChooser = new FileChooser();

			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(addPhoto.getScene().getWindow());

			if (selectedFile != null) {
				byte[] fileContent = null;
				try {
					fileContent = Files.readAllBytes(Path.of(selectedFile.getAbsolutePath()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				photo = new Photo(fileContent, selectedFile.getName());

				Photo newPhoto = new Photo(fileContent, contact.getFirstName());
				newPhoto.setContact(contact);
				photoRepository.save(newPhoto);
				contact.setPhoto(newPhoto);
			}
			System.out.println("Photo added");
			updatePhotoView(imageView, contact, photo);
		}

	}

	// Checks first if there is an image and after updateds
	protected void updatePhoto(ImageView imageView, Button updatePhoto) {
		Contact contact = ApplicationContext.getSelectedContact();
		Photo photo = contact.getPhoto();
		
		if (photo == null) {
			System.out.println("Photo is empty!");
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Add photo first in order to update");
			alert.showAndWait();
		} else {

			FileChooser fileChooser = new FileChooser();

			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(updatePhoto.getScene().getWindow());

			if (selectedFile != null) {
				byte[] fileContent = null;
				try {
					fileContent = Files.readAllBytes(Path.of(selectedFile.getAbsolutePath()));
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (fileContent.equals(selectedFile)) {
					photoRepository.delete(photo);
					contact.deletePhoto();
					
					photo = new Photo(fileContent, selectedFile.getName());

					Photo updatedPhoto = new Photo(fileContent, contact.getFirstName());
					updatedPhoto.setContact(contact);
					photoRepository.update(updatedPhoto);
					contact.updatePhoto(fileContent, "Updated photo");
					
					photo = contact.getPhoto();
					ByteArrayInputStream bis = new ByteArrayInputStream(photo.getPhotoData());
					Image image = new Image(bis);
					imageView.setImage(image);
				} else {
					photo.setPhotoData(fileContent);
					photo.setFilename(selectedFile.getName());
				}
			}
			System.out.println("Photo updated");
			updatePhotoView(imageView, contact, photo);
		}

	}

	// Deletes the foto but first the user is asked to confirm
	protected void deletePhoto(ImageView imageView) {
		Contact contact = ApplicationContext.getSelectedContact();
		Photo photo = contact.getPhoto();
		if (photo == null) {
			System.out.println("Photo is empty!");
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("Photo is empty!");
			alert.showAndWait();
		} else {
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete the photo ?", "Confirmation",
					JOptionPane.YES_NO_OPTION);

			if (choice == JOptionPane.YES_OPTION) {
				if (photo != null) {
					photoRepository.delete(photo);
					contact.deletePhoto();
					// Solve here to set the imageview after deleting the image !
					String imagePath = "C:\\Users\\bihun\\git\\Contact-Keeper\\contactkeeper\\src\\main\\resources\\images";
					File file = new File(imagePath);
					Image image = new Image(file.toURI().toString());
					imageView.setImage(image);
				}
				photo = null;
				imageView.setImage(null);
				System.out.println("User clicked Yes");
			} else if (choice == JOptionPane.NO_OPTION) {
				System.out.println("User clicked No");
			} else {
				System.out.println("User clicked Cancel");
			}
			System.out.println("Photo deleted");
		}
	}

	// Updates ImageView in PhotoController
	protected void updatePhotoView(ImageView imageView, Contact contact, Photo photo) {
		if (photo != null) {
			photo = contact.getPhoto();
			ByteArrayInputStream bis = new ByteArrayInputStream(photo.getPhotoData());
			Image image = new Image(bis);
			imageView.setImage(image);
		} else {

		}
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
