package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.VBox;

public class NavController implements Initializable {
	@FXML
    private VBox navPane;
    @FXML
    private Button homeNavBtn;
    @FXML
    private Button searchNavBtn;
    @FXML
    private Button contactNavBtn;
    @FXML
    private Button signOutNavBtn;
    @FXML
	public AnchorPane appPane;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
    	homeNavBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
    		public void handle(MouseEvent t) {
    			homeNavBtn.setTooltip(new Tooltip("Go to Home page"));
    		}
    	});
    	searchNavBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
    		public void handle(MouseEvent t) {
    			searchNavBtn.setTooltip(new Tooltip("Go to Search page"));
    		}
    	});
    	contactNavBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
    		public void handle(MouseEvent t) {
    			contactNavBtn.setTooltip(new Tooltip("Go to Contact page"));
    		}
    	});
    	signOutNavBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
    		public void handle(MouseEvent t) {
    			signOutNavBtn.setTooltip(new Tooltip("Sign Out"));
    		}
    	});
    	
	}
    
    @FXML
    public void goToHome(ActionEvent event) throws Exception{
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/HomePage.fxml"));
		Scene scene = new Scene(pane);
		MainApp.primaryStage.setScene(scene);
    }
    
	@FXML
    public void goToSearch(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/SearchPage.fxml"));
		Scene scene = new Scene(pane);
		MainApp.primaryStage.setScene(scene);
	}
    
    @FXML
    public void goToContact(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/ContactPage.fxml"));
		Scene scene = new Scene(pane);
		MainApp.primaryStage.setScene(scene);
	}
    
    @FXML
    public void signOut(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../libraryUI/SignedOut.fxml"));
    	AnchorPane pane = loader.load();
    	Scene scene = new Scene(pane);
    	MainApp.primaryStage.setScene(scene);
	}
}
