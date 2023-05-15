package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.ByteArrayInputStream;
import java.io.File;

import application.ApplicationContext;
import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Contact;
import model.Photo;

public class PhotoController extends BaseController {

	private Contact contact = ApplicationContext.getSelectedContact();
	private Photo photo = contact.getPhoto();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addPhoto;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private ImageView imageView;

    @FXML
    private Button updatePhoto;

    @FXML
    void handleAddPhotoPressed(ActionEvent event) {
    	addPhoto();
    }

    @FXML
    void handleBackPressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_CONTACT, (Stage) back.getScene().getWindow());
    }

    @FXML
    void handleDeletePressed(ActionEvent event) {
    	deletePhoto();
    	
    }

    @FXML
    void handleUpdatePhotoPressed(ActionEvent event) {
    	updatePhoto();
    	
    }

    @FXML
    void initialize() {
        assert addPhoto != null : "fx:id=\"addPhoto\" was not injected: check your FXML file 'Photo.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Photo.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'Photo.fxml'.";
        assert imageView != null : "fx:id=\"photo\" was not injected: check your FXML file 'Photo.fxml'.";
        assert updatePhoto != null : "fx:id=\"updatePhoto\" was not injected: check your FXML file 'Photo.fxml'.";


        updatePhotoView();
        
        }
    
    // Adds a photo
    private void addPhoto() {
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
			
			//using the method from Model class to add photo
			model.add(selectedFile, photoRepository, contact, photo, imageView);
		}
    }
    
 // Checks first if there is an image and after updateds
 	private void updatePhoto() {
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
 			
 			//using the method from Model class to update photo
 			model.update(selectedFile, photoRepository, contact, photo, imageView);
 		}
 	}
 	
 // Deletes the foto but first the user is asked to confirm
 	protected void deletePhoto() {
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
 			
 			//using the method from Model class to delete photo
 			model.delete(photoRepository, contact, photo, imageView);
 		}
 	}

    private void updatePhotoView() {
    	if (photo != null) {
    		photo = contact.getPhoto();
            ByteArrayInputStream bis = new ByteArrayInputStream(photo.getPhotoData());
            Image image = new Image(bis);
            imageView.setImage(image);
        } else {
        	
        }
    }
}
