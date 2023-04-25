package application;
	
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	boolean login = false;
	private boolean checkLogin(String username, String password) {
	    // Your implementation to check the username and password goes here
	    // For example, you could check if they match some hardcoded values or if they exist in a database
	    return username.equals("admin") && password.equals("admin123");
	}

	public void start(Stage primaryStage) throws Exception {
	    AnchorPane loginScreen = FXMLLoader.load(getClass().getResource("my_ui.fxml"));
	    Button loginbtn = (Button) loginScreen.lookup("#Dangnhap");
	    loginbtn.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	            // Get the username and password entered by the user
	            TextField usernameField = (TextField) loginScreen.lookup("#usernameField");
	            TextField passwordField = (TextField) loginScreen.lookup("#passwordField");
	            String username = usernameField.getText();
	            String password = passwordField.getText();

	            // Check if the username and password are correct
	            login = checkLogin(username, password);

	            if (login) {
	                // Create a new window
	                try {
	                    Stage newStage = new Stage();
	                    AnchorPane giaodienScreen;
	                    giaodienScreen = new FXMLLoader(getClass().getResource("Giaodien.fxml")).load();
	                    Scene newScene = new Scene(giaodienScreen);
	                    newStage.setScene(newScene);
	                    newStage.show();
	                    Button employeeButton = (Button) giaodienScreen.lookup("#Employee");
	             		 // Add an event handler to the button
	             		    employeeButton.setOnAction(new EventHandler<ActionEvent>() {
	             		     @Override
	             		     public void handle(ActionEvent event) {
	             		         employeeButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white;");
	             		     }
	             		});

	                    // Show login successful popup
	                    Alert alert = new Alert(AlertType.INFORMATION, "Login successful!");
	                    alert.showAndWait();

	                    // Wait for 2 seconds and then close the alert and current window
	                    Stage currentStage = (Stage) loginbtn.getScene().getWindow();
	                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), evt -> {
	                        alert.close();
	                        currentStage.close();
	                    }));
	                    timeline.play();
	                    
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            } else {
	                // Show login failed popup
	                Alert alert = new Alert(AlertType.ERROR, "Login failed! Sai mat khau hoac username");
	                alert.showAndWait();
	            }
	        }
	    });
	   
	    Scene scene = new Scene(loginScreen);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	}

	public static void main(String[] args) {
	    launch(args);
	}

}



