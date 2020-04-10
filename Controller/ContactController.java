package Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

@SuppressWarnings("restriction")
public class ContactController implements Initializable {
    @FXML
    private AnchorPane appPane;
    @FXML
    private JFXHamburger navButton;
    @FXML
    private Pane navBox;
    @FXML
    private JFXDrawer navDrawer;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    
    
	public void initialize(URL arg0, ResourceBundle arg1) {
		navAnimation();
		email.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				email.setTooltip(new Tooltip("Click to mail s"));
			}
		});
		phone.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				phone.setTooltip(new Tooltip("Call us for assistance"));
			}
		});
	}

	public void navAnimation() {
		try {
			navBox = FXMLLoader.load(getClass().getResource("../libraryUI/NavigationBar.fxml"));
			navDrawer.setSidePane(navBox);
		} catch (IOException ex) {
			Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null,ex); 
		}
		
		navButton.addEventHandler(MouseEvent.MOUSE_PRESSED,(e) -> {
			if(navDrawer.isOpened()) {
				navDrawer.close();
			}else
				navDrawer.open();
		});
	}
	
	@FXML
    public void sendMail(MouseEvent event) throws IOException, URISyntaxException {
		Desktop desk = Desktop.getDesktop();
		desk.mail(new URI("mailto:contactus@dlms.ca"));
    }
}