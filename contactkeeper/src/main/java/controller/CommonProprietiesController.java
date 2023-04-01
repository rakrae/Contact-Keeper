package controller;


import model.Account;
import model.Contact;
import repository.AccountRepository;
import repository.AccountRepositoryJPA;
import repository.ContactRepository;
import repository.ContactRepositoryJPA;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CommonProprietiesController {




	static final String PERSISTANCE_NAME_LOGIN = "/fxml/Login.fxml";
	static final String PERSISTANCE_NAME_ACCOUNT = "/fxml/Account.fxml";
	static final String PERSISTANCE_NAME_EDITACCOUNT = "/fxml/EditAccount.fxml";
	static final String PERSISTANCE_NAME_NEWACCOUNT = "/fxml/NewAccount.fxml";
	static final String PERSISTANCE_NAME_CONTACTS = "/fxml/Contacts.fxml";
	static final String PERSISTANCE_NAME_CONTACT = "/fxml/Contact.fxml";
	static final String PERSISTANCE_NAME_NEWCONTACT = "/fxml/NewContact.fxml";
	static final String PERSISTANCE_NAME_EDITCONTACT = "/fxml/EditContact.fxml";
	
	
//	Should be able to interract with the account the contacts in an account
	
//	// For the account 
//	static AccountRepository accountRepository = new AccountRepositoryJPA();
//	static ObservableList<Account> accountList = FXCollections.observableArrayList(accountRepository.readAll());
//	static ObjectProperty<Account> selectedAccount = new SimpleObjectProperty<Account>();
//	
//	
//	// For the contacts
//	static ContactRepository contactRepository = new ContactRepositoryJPA();
//	static ObservableList<Contact> contactList = FXCollections.observableArrayList(contactRepository.readAll());
//	static ObjectProperty<Contact> selectedContact = new SimpleObjectProperty<Contact>();


	
	public void openScene(String scene) {

		try { // TO open the next scene
			FXMLLoader mainLoader = new FXMLLoader();
			mainLoader.setLocation(getClass().getResource(scene));
			Parent root = (Parent) mainLoader.load();
			Scene mainScene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(mainScene);
			stage.show();
			stage.setOnHidden(null);
		} catch (Exception e) {
			System.err.println("Can not load");
			e.printStackTrace();
		}
	
	}
	
}
