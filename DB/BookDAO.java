package DB;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookDAO {
	
	//search for books from the database by book title
	public ObservableList<Book> searchBook(String title) throws SQLException, ClassNotFoundException{
		String selectStmt = "SELECT * FROM books WHERE TITLE LIKE '%" + title + "%' OR AUTHOR LIKE '%" + title + "%' OR PUBLICATION LIKE '%" + title + "%'";
		
		try {
			SQLUtil sql = new SQLUtil();
			ResultSet rsBook = sql.dbExecuteQuery(selectStmt); 
			ObservableList<Book> bookList = getBookListFromResultSet(rsBook);
			return bookList;
		}catch(SQLException e) {
			System.out.println("While searching a books, an error occured:" + e);
			throw e;
		}
	}
	
	//get list of all books with details from database
	public ObservableList<Book> getBookListFromResultSet(ResultSet rs) throws SQLException{
		ObservableList<Book> bkList = FXCollections.observableArrayList();
		while(rs.next()) {
			Book bk = new Book();
			bk.setBookId(rs.getInt("BOOK_ID"));
			bk.setBookTitle(rs.getString("TITLE"));
			bk.setBookAuthor(rs.getString("AUTHOR"));
			bk.setBookPublication(rs.getString("PUBLICATION"));
			bk.setBookEdition(rs.getString("EDITION"));
			bk.setBookYear(rs.getInt("YEAR"));
			bk.setBookSummary(rs.getString("SUMMARY"));
			bk.setBookLink(rs.getString("LINK"));
			bkList.add(bk);
		}
		return bkList;
	}
}
