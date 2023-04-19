package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

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
	private Photo photo = ApplicationContext.getSelectedContact().getPhoto();
	private File selectedFile;

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
    private ImageView photoView;

    @FXML
    private Button updatePhoto;

    @FXML
    void handleAddPhotoPressed(ActionEvent event) {

    	
    	 FileChooser fileChooser = new FileChooser();

         fileChooser.getExtensionFilters().addAll(
                 new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
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
             } else {
                 photo.setPhotoData(fileContent);
                 photo.setFilename(selectedFile.getName());
             }
             updatePhotoView();
         }
    }

    @FXML
    void handleBackPressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_CONTACT, (Stage) back.getScene().getWindow());
    }

    @FXML
    void handleDeletePressed(ActionEvent event) {
    	if(photo != null ) {
    		photoRepository.delete(photo);
    		photo = null;
    		contact.deletePhoto();
    	}
    	updatePhotoView();
    }

    @FXML
    void handleUpdatePhotoPressed(ActionEvent event) {
    	 if (photo != null && selectedFile != null) {
             byte[] fileContent = null;
             try {
                 fileContent = Files.readAllBytes(Path.of(selectedFile.getAbsolutePath()));
             } catch (IOException e) {
                 e.printStackTrace();
             }
             Contact contact = ApplicationContext.getSelectedContact();
             photo.setPhotoData(fileContent);
             photo.setFilename(selectedFile.getName());
             photoRepository.update(photo);
             contact.updatePhoto(fileContent, "Updated photo");
             contact.setPhoto(photo);
         }
    	 updatePhotoView();
    }

    @FXML
    void initialize() {
        assert addPhoto != null : "fx:id=\"addPhoto\" was not injected: check your FXML file 'Photo.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Photo.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'Photo.fxml'.";
        assert photoView != null : "fx:id=\"photo\" was not injected: check your FXML file 'Photo.fxml'.";
        assert updatePhoto != null : "fx:id=\"updatePhoto\" was not injected: check your FXML file 'Photo.fxml'.";

        
        updatePhotoView();
        
        }

    private void updatePhotoView() {
    	if (photo != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(photo.getPhotoData());
            Image image = new Image(bis);
            photoView.setImage(image);
        } else {
            photoView.setImage(new Image("C:\\Users\\bihun\\git\\Contact-Keeper\\contactkeeper\\src\\main\\resources\\images"));
        }
    }
}
