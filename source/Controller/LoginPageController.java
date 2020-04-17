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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class LoginPageController implements Initializable {
	@FXML
    private AnchorPane loginAP;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button userLoginBtn;
    @FXML
    private Button newUserBtn;
    @FXML
    private Button welcomeBtn;
    Scene sceneHP,sceneRP,sceneW;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		usernameTextField.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				usernameTextField.setTooltip(new Tooltip("Enter username"));
			}
		});
		passwordPasswordField.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				passwordPasswordField.setTooltip(new Tooltip("Enter password"));
			}
		});
		userLoginBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				userLoginBtn.setTooltip(new Tooltip("Sign in with Username and Password"));
			}
		});
		newUserBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				newUserBtn.setTooltip(new Tooltip("Register Here!!"));
			}
		});
		welcomeBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				welcomeBtn.setTooltip(new Tooltip("Back to Welcome Page"));
			}
		});
	}
	
	//go to register new user page 
	@FXML
    public void registerUser(ActionEvent event) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/Register.fxml"));
			sceneRP = new Scene(pane);
			MainApp.primaryStage.setScene(sceneRP);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	//login method as users
	@FXML
	public void loginAsUser(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
		Window loginStage = userLoginBtn.getScene().getWindow();
		String usr = usernameTextField.getText();
		String pass = passwordPasswordField.getText();
		
		if(usr.isEmpty()) {
			showAlert(Alert.AlertType.ERROR, loginStage, "Form Error!", "Please enter your username correctly");
			return;
		}
		if(pass.isEmpty()) {
			showAlert(Alert.AlertType.ERROR, loginStage, "Form Error!", "Please enter your password correctly");
			return;
		}
		
		SQLUtil sql = new SQLUtil();
		boolean check = sql.validate(usr, pass);
		if(!check) {
			display("Please enter correct credentials", null,"Failed");
		}else {
			display("Login Successful!", null, "Login");
			AnchorPane pane1 = FXMLLoader.load(getClass().getResource("../libraryUI/HomePage.fxml"));
			sceneHP = new Scene(pane1);
			MainApp.primaryStage.setScene(sceneHP);
		}
	}
	
	@FXML
    public void welcome(ActionEvent event) throws IOException {
		AnchorPane pane1 = FXMLLoader.load(getClass().getResource("../libraryUI/Welcome.fxml"));
		sceneW = new Scene(pane1);
		MainApp.primaryStage.setScene(sceneW);
    }

	//display confirmation message after login
	private void display(String string, Object object, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setContentText(string);
		alert.showAndWait();
	}

	//display error message in-process of login
	private void showAlert(Alert.AlertType alertError, Window loginStage, String string, String message) {
		Alert alert = new Alert(alertError);
		alert.setTitle(string);
		alert.setContentText(message);
		alert.initOwner(loginStage);
		alert.show();
	}
}