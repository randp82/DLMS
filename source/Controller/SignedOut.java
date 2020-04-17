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

public class SignedOut implements Initializable{
    @FXML
    private Button backToBeginBtn;
    Scene sceneW;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		backToBeginBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				backToBeginBtn.setTooltip(new Tooltip("GO TO WELCOME PAGE"));
			}
		});
	}

	//to go welcome page
	@FXML
	public void backToBegin(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("../libraryUI/Welcome.fxml"));
		sceneW = new Scene(pane);
		MainApp.primaryStage.setScene(sceneW);
	}
}