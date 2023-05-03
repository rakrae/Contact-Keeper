package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import application.ApplicationContext;
import common.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;

import model.Contact;

public class ContactsController extends BaseController {
	
	private ObservableList<Contact> contactsData;
	private List<String> filterList = new ArrayList<>();

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
    private Button searchButton;

    @FXML
    private TextField searchContactTextField;

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
    void handleSearchButtonPressed(ActionEvent event) {
    	   filterList.clear();
    	    String query = searchContactTextField.getText().toLowerCase();
    	    if (!query.isEmpty()) {
    	        filterList.addAll(Arrays.asList(query.split(" ")));
    	    }
    	    filterContacts();
    	    setDeleteButtonCellFactory();
    }

    @FXML
    void initialize() {
    	  assert addContact != null : "fx:id=\"addContact\" was not injected: check your FXML file 'Contacts.fxml'.";
          assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Contacts.fxml'.";
          assert close != null : "fx:id=\"close\" was not injected: check your FXML file 'Contacts.fxml'.";
          assert contactsView != null : "fx:id=\"contactsView\" was not injected: check your FXML file 'Contacts.fxml'.";
          assert deleteColumn != null : "fx:id=\"deleteColumn\" was not injected: check your FXML file 'Contacts.fxml'.";
          assert firstNameColumn != null : "fx:id=\"firstNameColumn\" was not injected: check your FXML file 'Contacts.fxml'.";
          assert lastNameColumn != null : "fx:id=\"lastNameColumn\" was not injected: check your FXML file 'Contacts.fxml'.";
          assert phoneNumberColumn != null : "fx:id=\"phoneNumberColumn\" was not injected: check your FXML file 'Contacts.fxml'.";
          assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'Contacts.fxml'.";
          assert searchContactTextField != null : "fx:id=\"searchContactTextField\" was not injected: check your FXML file 'Contacts.fxml'.";
       
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
	
	//Allerts first the user if it want to delete the specific contact first
	private void setDeleteButtonCellFactory() {
		deleteColumn.setCellFactory(param -> new TableCell<>() {
			private final Button deleteButton = new Button("Delete");

			{
				deleteButton.setOnAction(event -> {
					Contact contact = getTableView().getItems().get(getIndex());
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
	                        "Are you sure you want to delete " + contact.getFirstName() + "?",
	                        ButtonType.YES, ButtonType.NO);
	                alert.setHeaderText("Confirm Deletion");
	                alert.showAndWait().ifPresent(buttonType -> {
	                    if (buttonType == ButtonType.YES) {
				    contactRepository.delete(contact);
				    contactsData.remove(contact);
				    filterContacts(); // Refresh the filtered view after deleting a contact
	                    }
	                });
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
	    String query = searchContactTextField.getText().toLowerCase();
	    if (query == null || query.isEmpty()) {
	        contactsView.setItems(contactsData);
	    } else {
	        List<Contact> filteredList = contactsData.filtered(contact ->
	                filterList.contains(contact.getFirstName().toLowerCase()) ||
	                filterList.contains(contact.getLastName().toLowerCase()) ||
	                filterList.contains(contact.getPhoneNumber().toLowerCase())
	        );
	        contactsView.setItems(FXCollections.observableArrayList(filteredList));
	    }
	}

}
