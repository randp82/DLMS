package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.SQLUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class RegisterController implements Initializable{
    @FXML
    private AnchorPane newUserAP;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passPasswordField;
    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    Scene sceneHP,sceneLP;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
    	nameTextField.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				nameTextField.setTooltip(new Tooltip("Enter your Full Name."));
			}
		});
    	emailTextField.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				emailTextField.setTooltip(new Tooltip("Please enter a valid email-address"));
			}
		});
    	userTextField.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				userTextField.setTooltip(new Tooltip("Create a username for sign-in"));
			}
		});
    	passPasswordField.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				passPasswordField.setTooltip(new Tooltip("Please create a strong password"));
			}
		});
    	registerBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				registerBtn.setTooltip(new Tooltip("Register with above credentials"));
			}
		});
    	loginBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				loginBtn.setTooltip(new Tooltip("Back to Login Page"));
			}
		});
	}
    
    @FXML
    public void registerUser(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	Window registerStage = registerBtn.getScene().getWindow();
    	String name = nameTextField.getText();
    	String email = emailTextField.getText();
    	String user = userTextField.getText();
		String pass = passPasswordField.getText();
    	
		if(name.isEmpty()) {
			showAlert(Alert.AlertType.ERROR, registerStage, "Form Error!", "Please enter your full name");
			return;
		}
		if(email.isEmpty()) {
			showAlert(Alert.AlertType.ERROR, registerStage, "Form Error!", "Please enter a valid email address");
			return;
		}
		if(user.isEmpty()) {
			showAlert(Alert.AlertType.ERROR, registerStage, "Form Error!", "Please enter a username");
			return;
		}
		if(pass.isEmpty()) {
			showAlert(Alert.AlertType.ERROR, registerStage, "Form Error!", "Please enter a password");
			return;
		}
		
		SQLUtil sql = new SQLUtil();
		String check = sql.checkRegister(name, email, user, pass);
		if(check == "Already Exist") {
			display("User Already Exist", null,"Failed");
		}else if(check == "Username Exist") {
			display("Please choose different username", null,"Failed");
		}else if(check == "Registered"){
			display("Successfully Registered", null, "Login");
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/HomePage.fxml"));
			sceneHP = new Scene(pane);
			MainApp.primaryStage.setScene(sceneHP);
		}
    }
    
	//display confirmation message after login
	private void display(String string, Object object, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setContentText(string);
		alert.showAndWait();
	}	
		
  //display error message in-process of login
  	private void showAlert(Alert.AlertType alertError, Window registerStage, String string, String message) {
  		Alert alert = new Alert(alertError);
  		alert.setTitle(string);
  		alert.setContentText(message);
  		alert.initOwner(registerStage);
  		alert.show();
  	}

  	@FXML
    public void userLogin(ActionEvent event) throws IOException {
  		AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/LoginPage.fxml"));
		sceneLP = new Scene(pane);
		MainApp.primaryStage.setScene(sceneLP);
    }
}