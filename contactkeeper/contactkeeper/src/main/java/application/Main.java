package application;

import database.DatabaseCreator;
import database.PersistenceManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
	        DatabaseCreator.insertDummyData();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/Login.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets();
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		PersistenceManager.closeEntityManagerFactory();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
