package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.ApplicationContext;
import common.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.Contact;

public class ContactsController extends BaseController {

	private ObservableList<Contact> contactsData;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button addContact;

	@FXML
	private Button back;

	@FXML
	private Button close;

	@FXML
	private TableView<Contact> contactsView;

	@FXML
	private TableColumn<Contact, String> firstNameColumn;

	@FXML
	private TableColumn<Contact, String> lastNameColumn;

	@FXML
	private TableColumn<Contact, Integer> phoneNumberColumn;

	@FXML
	private TableColumn<Contact, Void> deleteColumn;

	@FXML
	private TextField searchContactTextField;

	@FXML
	private Button searchButton;

	@FXML
	void handleSearchButtonPressed(ActionEvent event) {
		filterContacts();
	}

	@FXML
	void handleAddContactPressed(ActionEvent event) {
		navigateTo(PERSISTANCE_NAME_NEWCONTACT, (Stage) addContact.getScene().getWindow());
	}

	@FXML
	void handleBackPressed(ActionEvent event) {
		navigateTo(PERSISTANCE_NAME_ACCOUNT, (Stage) back.getScene().getWindow());
	}

	@FXML
	void handleClosePressed(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void initialize() {
		assertInjected();
		
		setUpTableViewColumns();

		List<Contact> contacts = contactRepository.findByUserName(getLoggedInAccount().getUserName());
		contactsData = FXCollections.observableArrayList(contacts);
		contactsView.setItems(contactsData);

		setDeleteButtonCellFactory();
		setDoubleClickRowFactory();
	}


	private void setUpTableViewColumns() {
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
	}

	private void setDeleteButtonCellFactory() {
		deleteColumn.setCellFactory(param -> new TableCell<>() {
			private final Button deleteButton = new Button("Delete");

			{
				deleteButton.setOnAction(event -> {
					Contact contact = getTableView().getItems().get(getIndex());
				    contactRepository.delete(contact);
				    contactsData.remove(contact);
				    filterContacts(); // Refresh the filtered view after deleting a contact
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(deleteButton);
				}
			}
		});
	}

	private void setDoubleClickRowFactory() {
		contactsView.setRowFactory(tv -> {
			TableRow<Contact> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					Contact clickedContact = row.getItem();
					ApplicationContext.setSelectedContact(clickedContact);
					navigateTo(PERSISTANCE_NAME_CONTACT, (Stage) back.getScene().getWindow());
				}
			});
			return row;
		});
	}

	private void filterContacts() {
	    String query = searchContactTextField.getText();
	    if (query == null || query.isEmpty()) {
	        contactsView.setItems(contactsData);
	    } else {
	        ObservableList<Contact> filteredData = FXCollections.observableArrayList();
	        for (Contact contact : contactsData) {
	            if (contact.getFirstName().toLowerCase().contains(query.toLowerCase())
	                    || contact.getLastName().toLowerCase().contains(query.toLowerCase())
	                    || String.valueOf(contact.getPhoneNumber()).contains(query)) {
	                filteredData.add(contact);
	            }
	        }
	        contactsView.setItems(filteredData);
	    }
	}
}