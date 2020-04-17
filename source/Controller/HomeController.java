package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

@SuppressWarnings("restriction")
public class HomeController implements Initializable{

	@FXML
    private AnchorPane appPane;
    @FXML
    private JFXHamburger navButton;
    @FXML
    private JFXDrawer navDrawer;
    @FXML
    private Pane navBox;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField searchTextField;
	Scene sceneSP;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		navAnimation();
		searchTextField.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				searchTextField.setTooltip(new Tooltip("Enter book name to search"));
			}
		});
		searchBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				searchBtn.setTooltip(new Tooltip("Click to search book"));
			}
		});
	}
	
	public void navAnimation() {
		try {
			navBox = FXMLLoader.load(getClass().getResource("../libraryUI/NavigationBar.fxml"));
			navDrawer.setSidePane(navBox);
		} catch (IOException ex) {
			Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null,ex); 
		}
		
		navButton.addEventHandler(MouseEvent.MOUSE_PRESSED,(e) -> {
			if(navDrawer.isOpened()) {
				navDrawer.close();
			}else
				navDrawer.open();
		});
	}
	
	@FXML
	public void bookSearch(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../libraryUI/SearchPage.fxml"));
		AnchorPane pane = loader.load();
		sceneSP = new Scene(pane);
		MainApp.primaryStage.setScene(sceneSP);
		
		SearchController sc = loader.getController();
		sc.setSearchText(searchTextField.getText());
		sc.searchBook(event);
	}
	
	public String getBookText() {
		return searchTextField.getText();
	}
}