package UI;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainUI extends Application{

	public MainUI() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			 FXMLLoader loader = new FXMLLoader();
		        // Path to the FXML File
		        String fxmlDocPath = "Login.fxml";
		        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		         
		        // Create the Pane and all Details
		        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
		         
		        // Create the Scene
		        Scene scene = new Scene(root);
		        // Set the Scene to the Stage
		        primaryStage.setScene(scene);
		        // Set the Title to the Stage
		        primaryStage.setTitle("Classroom Management System");
		        // Display the Stage
		        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		Application.launch(args);

	}

}
