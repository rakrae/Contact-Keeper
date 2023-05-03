package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.ByteArrayInputStream;
import application.ApplicationContext;
import common.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    	addPhoto(addPhoto, imageView);
    }

    @FXML
    void handleBackPressed(ActionEvent event) {
    	navigateTo(PERSISTANCE_NAME_CONTACT, (Stage) back.getScene().getWindow());
    }

    @FXML
    void handleDeletePressed(ActionEvent event) {
    	deletePhoto(imageView);
    }

    @FXML
    void handleUpdatePhotoPressed(ActionEvent event) {
    	updatePhoto(imageView, updatePhoto);
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
