package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import DB.Book;
import DB.BookDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

@SuppressWarnings({"restriction","unchecked","rawtypes"})
public class SearchController implements Initializable{
    @FXML
    private AnchorPane appPane;
    @FXML
    private JFXHamburger navButton;
    @FXML
    private Pane navBox;
	@FXML
    private JFXDrawer navDrawer;
    @FXML
    private Button searchBtnSP;
    @FXML
    private TextField searchTextFieldSP;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> publicationColumn;
    @FXML
    private TableColumn<Book, Integer> yearColumn;
    
    Book cellBook = null;
    
    //initialize/call navigation bar animation method and table column
	@Override
	public void initialize(URL location, ResourceBundle resources) {			
		navAnimation();
		addViewColumnAndButton();
		titleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Book,String>, ObservableValue<String>>(){
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Book,String> i){
				return new ReadOnlyObjectWrapper(i.getValue().getBookTitle());
			}
		});
		authorColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Book,String>, ObservableValue<String>>(){
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Book,String> i){
				return new ReadOnlyObjectWrapper(i.getValue().getBookAuthor());
			}
		});
		publicationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Book,String>, ObservableValue<String>>(){
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Book,String> i){
				return new ReadOnlyObjectWrapper(i.getValue().getBookPublication());
			}
		});
		yearColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Book,Integer>, ObservableValue<Integer>>(){
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Book,Integer> i){
				return new ReadOnlyObjectWrapper(i.getValue().getBookYear());
			}
		});
		searchTextFieldSP.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				searchTextFieldSP.setTooltip(new Tooltip("Search for Book by title, author or publisher"));
			}
		});
		searchBtnSP.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				searchBtnSP.setTooltip(new Tooltip("Click to search"));
			}
		});
	}
	
	public void addViewColumnAndButton() {
		TableColumn<Book, Void> colBtn = new TableColumn("View Book");
		
		Callback<TableColumn<Book,Void>, TableCell<Book,Void>> cellFactory = new Callback<TableColumn<Book,Void>, TableCell<Book,Void>>(){
			@Override
			public TableCell<Book, Void> call(TableColumn<Book, Void> param) {
				final TableCell<Book,Void> cell = new TableCell<Book, Void>(){
					private final Button btn = new Button("View");
				
					{btn.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent t) {
								int selectedIndex = getTableRow().getIndex();
								cellBook = getTableView().getItems().get(selectedIndex);
								
								BookPageController bpc = new BookPageController();
								bpc.show(cellBook);
							}
						});
					}
					{
						btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
							public void handle(MouseEvent t) {
								btn.setTooltip(new Tooltip("View Book Details"));
							}
						});
					}
					
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty) {
							setGraphic(btn);
						}else {
							setGraphic(null);
						}
					}					
				};
				return cell;
			}};
			colBtn.setCellFactory(cellFactory);
			bookTable.getColumns().add(colBtn);
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
			}else {
				navDrawer.open();
			}
		});
	}

	//search for a book	
	@FXML
	public void searchBook(ActionEvent event) throws ClassNotFoundException, SQLException{		
		try {
			BookDAO bookData = new BookDAO();
			ObservableList<Book> bookList = bookData.searchBook(searchTextFieldSP.getText());
			populateBooks(bookList);
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error occurred while getting book info from DB.\n");
			throw e;
		}
	}
	
	//display all books in table
	@FXML
	public void populateBooks(ObservableList<Book> book) throws ClassNotFoundException{
		bookTable.setItems(book);
	}
	
	//get search book name from search bar
	public String getSearchText() {
		return searchTextFieldSP.getText();
	}
	
	//set search book name to search bar
	public void setSearchText(String name) {
		this.searchTextFieldSP.setText(name);
	}
}