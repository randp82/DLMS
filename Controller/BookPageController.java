package Controller;

import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import DB.Book;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

@SuppressWarnings("restriction")
public class BookPageController implements Initializable{

	@FXML
    private AnchorPane appPane;
    @FXML
    private JFXHamburger navButton;
    @FXML
    private Pane navBox;
    @FXML
    private JFXDrawer navDrawer;
    @FXML
    private Button btnOnline;
    
	static Book showBook = null;
	static int bookId = 0;
	AnchorPane pane;
	Scene scene;
	Label lbl00,lbl01,lbl02,lbl03,lbl04,lbl05,lbl10,lbl11,lbl12,lbl13,lbl14,lbl15,lbl16;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		navAnimation();
		btnOnline.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				btnOnline.setTooltip(new Tooltip("Read book online"));
			}
		});
	}
	
	//show navigation bar animation and start at start of scene
	public void navAnimation() {
		try {
			navBox = FXMLLoader.load(getClass().getResource("../libraryUI/NavigationBar.fxml"));
			navDrawer.setSidePane(navBox);
		} catch (IOException ex) {
			Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null,ex); 
		}
		
		navButton.addEventHandler(MouseEvent.MOUSE_PRESSED,(e) -> {
			
			if(navDrawer.isOpened()) {
				navDrawer.close();
				btnOnline.toFront();
			}else {
				navDrawer.open();
				btnOnline.toBack();
			}
		});
	}
	
	public void show(Book book) {
		showBook = book;
		bookId = showBook.getBookId();
		displayBook(showBook);
	}
	
	public void displayBook(Book showBook) {
		final GridPane grid =new GridPane();
		grid.setLayoutX(150);
		grid.setLayoutY(140);
		
		ColumnConstraints col1 = new ColumnConstraints(150);
		ColumnConstraints col2 = new ColumnConstraints(460);
		RowConstraints row1 = new RowConstraints(50);
		RowConstraints row2 = new RowConstraints(50);
		RowConstraints row3 = new RowConstraints(50);
		RowConstraints row4 = new RowConstraints(50);
		RowConstraints row5 = new RowConstraints(50);
		RowConstraints row6 = new RowConstraints(175);
		grid.getColumnConstraints().addAll(col1,col2);
		grid.getRowConstraints().addAll(row1,row2,row3,row4,row5,row6);
		
		lbl00 = new Label("Title:");
		lbl01 = new Label("Author:");
		lbl02 = new Label("Publication:");
		lbl03 = new Label("Edition:");
		lbl04 = new Label("Year:");
		lbl05 = new Label("Summary:");
		lbl10 = new Label();
		lbl11 = new Label();
		lbl12 = new Label();
		lbl13 = new Label();
		lbl14 = new Label();
		lbl15 = new Label();
		
		lbl10.setText(showBook.getBookTitle());
		lbl11.setText(showBook.getBookAuthor());
		lbl12.setText(showBook.getBookPublication());
		lbl13.setText(showBook.getBookEdition());
		lbl14.setText(String.valueOf(showBook.getBookYear()));
		lbl15.setText(showBook.getBookSummary());
		
		lbl10.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				lbl10.setTooltip(new Tooltip(showBook.getBookTitle()));
			}
		});
		lbl11.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				lbl11.setTooltip(new Tooltip(showBook.getBookAuthor()));
			}
		});
		lbl12.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				lbl12.setTooltip(new Tooltip(showBook.getBookPublication()));
			}
		});
		lbl13.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				lbl13.setTooltip(new Tooltip(showBook.getBookEdition()));
			}
		});
		lbl14.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				lbl14.setTooltip(new Tooltip(String.valueOf(showBook.getBookYear())));
			}
		});
		
		Tooltip tip = new Tooltip();
		tip.setText(showBook.getBookSummary());
		tip.setStyle("-fx-font-size: 11pt;" + "-fx-wrap-text:true;" + "-fx-label-padding: 0 0 0 10;");
		lbl15.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				lbl15.setTooltip(tip);
			}
		});
		
		
		grid.add(lbl00,0,0);
		grid.add(lbl01,0,1);
		grid.add(lbl02,0,2);
		grid.add(lbl03,0,3);
		grid.add(lbl04,0,4);
		grid.add(lbl05,0,5);
		grid.add(lbl10,1,0);
		grid.add(lbl11,1,1);
		grid.add(lbl12,1,2);
		grid.add(lbl13,1,3);
		grid.add(lbl14,1,4);
		grid.add(lbl15,1,5);
				
		try {
			pane =FXMLLoader.load(getClass().getResource("../libraryUI/BookPage.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pane.getChildren().add(grid);
		scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("../libraryUI/stylesheet.css").toExternalForm());
		MainApp.primaryStage.setScene(scene);
	}
	
	@FXML
	public void view(ActionEvent event) throws IOException, URISyntaxException {
		URI uri = new URI(showBook.getBookLink());
		Desktop desk = Desktop.getDesktop();
		desk.browse(uri);
	}	
}