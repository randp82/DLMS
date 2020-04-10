package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application implements Initializable {
	@FXML
    private AnchorPane welcomeAP;
    @FXML
    private Button loginBtn;
    @FXML
    private Button guestLoginBtn;
    @FXML
    private Button welcomeBtn;
    
	static Stage primaryStage;
	Scene sceneW,sceneLP;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loginBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				loginBtn.setTooltip(new Tooltip("Click for Sign-in/Sign-up"));
			}
		});
		guestLoginBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				guestLoginBtn.setTooltip(new Tooltip("Check out our Library as Guest"));
			}
		});
	}
	
	@Override
	public void start(Stage primaryStage) {
		MainApp.primaryStage = primaryStage;
		primaryStage.setTitle("Digital Library Management System");
		showMain();
	}
	
	public void showMain() {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/Welcome.fxml"));
			sceneW = new Scene(pane);
			primaryStage.setScene(sceneW);
			MainApp.primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error while loading Welcome page:" + e);
		}
	}
	
	//go to login page for signin/signup
	@FXML
	public void login(ActionEvent event) throws IOException{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/LoginPage.fxml"));
		sceneLP = new Scene(pane);
		MainApp.primaryStage.setScene(sceneLP);
	}
	
	//login method as guest
	@FXML
	public void loginAsGuest(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/HomePage.fxml"));
		sceneLP = new Scene(pane);
		MainApp.primaryStage.setScene(sceneLP);
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
