package controller;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import java.io.ByteArrayInputStream;
import application.ApplicationContext;
import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Contact;
import model.Photo;

public class PhotoController extends BaseController {
	
	Contact contact = ApplicationContext.getSelectedContact();
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
    
    /*
     * Adds a photo only if the photo object is null
     * otherwise the user is asked to update the current one
     */
    public void addPhoto() {
    	if(photo != null ) {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
        	alert.setTitle("Warning");
        	alert.setHeaderText(null);
        	alert.setContentText("Please update!");
        	alert.showAndWait();
        } else {
    	FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(addPhoto.getScene().getWindow());

        if (selectedFile != null) {
            byte[] fileContent = null;
            try {
                fileContent = Files.readAllBytes(Path.of(selectedFile.getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photo == null) {
                photo = new Photo(fileContent, selectedFile.getName());

                Photo newPhoto = new Photo(fileContent,contact.getFirstName());
                newPhoto.setContact(contact);
                photoRepository.save(newPhoto);
                contact.setPhoto(newPhoto);
            } else {
                photo.setPhotoData(fileContent);
                photo.setFilename(selectedFile.getName());
            }
            updatePhotoView();
        }
      }
   }
    
    // Checks first if there is an image and after updateds
    private void updatePhoto() {
    	
        if(photo == null ) {
        	Alert alert = new Alert(Alert.AlertType.WARNING);
        	alert.setTitle("Warning");
        	alert.setHeaderText(null);
        	alert.setContentText("Add photo first in order to update");
        	alert.showAndWait();
        } else {
    	
    	FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(addPhoto.getScene().getWindow());

        	if(selectedFile != null ) {
            byte[] fileContent = null;
            try {
                fileContent = Files.readAllBytes(Path.of(selectedFile.getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if(fileContent.equals(selectedFile)) {
            	photoRepository.delete(photo);
            	contact.deletePhoto();
            }
            	
            if (fileContent.equals(selectedFile) && photo == null) {
                photo = new Photo(fileContent, selectedFile.getName());

                Photo updatedPhoto = new Photo(fileContent,contact.getFirstName());
                updatedPhoto.setContact(contact);
                photoRepository.update(updatedPhoto);
                contact.updatePhoto(fileContent, "Updated photo");
            } else {
                photo.setPhotoData(fileContent);
                photo.setFilename(selectedFile.getName());
            }
            updatePhotoView();
        }
      }
 	
   }
    
    // Deletes the foto but first the user is asked to confirm 
    private void deletePhoto() {
    	if(photo == null ) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
        	alert.setTitle("Warning");
        	alert.setHeaderText(null);
        	alert.setContentText("Photo is empty!");
        	alert.showAndWait();
    	} else {
    		int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete the photo ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if (choice == JOptionPane.YES_OPTION) {
            	if(photo != null ) {
        			photoRepository.delete(photo);
        			contact.deletePhoto();
        			// Solve here to set the imageview after deleting the image !
        	        	String imagePath = "C:\\Users\\bihun\\git\\Contact-Keeper\\contactkeeper\\src\\main\\resources\\images"; 
        	        	File file = new File(imagePath);
        	        	Image image = new Image(file.toURI().toString());
        	        	imageView.setImage(image);
        	        }photo = null;
                System.out.println("User clicked Yes");
            } else if (choice == JOptionPane.NO_OPTION) {
                System.out.println("User clicked No");
            } else {
                System.out.println("User clicked Cancel");
            }
	
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
