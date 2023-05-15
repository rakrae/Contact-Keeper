package model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JOptionPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import repository.PhotoRepository;

public class Model {

	// adding photo in PhotoController
	public void add(File selectedFile, PhotoRepository photoRepository, Contact contact, Photo photo,
			ImageView imageView) {

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
			System.out.println("Photo added");
		}
		updatePhotoView(imageView, contact, photo);
	}

	// update photo in PhotoController
	public void update(File selectedFile, PhotoRepository photoRepository, Contact contact, Photo photo,
			ImageView imageView) {
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
		updatePhotoView(imageView, contact, photo);
		System.out.println("Photo updated");
	}
	
	// delete photo in PhotoController
	public void delete(PhotoRepository photoRepository, Contact contact, Photo photo, ImageView imageView) {
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
	

	protected void updatePhotoView(ImageView imageView, Contact contact, Photo photo) {
		if (photo != null) {
			photo = contact.getPhoto();
			ByteArrayInputStream bis = new ByteArrayInputStream(photo.getPhotoData());
			Image image = new Image(bis);
			imageView.setImage(image);
		} else {

		}
	}

}
