package DB;

public class Book {
	
	//book data members
	protected int bookId;
	protected String bookTitle;
	protected String bookAuthor;
	protected String bookPublication;
	protected String bookEdition;
	protected int bookYear;
	protected String bookSummary;
	protected String bookLink;
	
	//constructor
	public Book() {
		this.bookId = 0;
		this.bookTitle = "no";
		this.bookAuthor = "";
		this.bookPublication = "";
		this.bookEdition = "";
		this.bookYear = 0;
		this.bookSummary = "";
		this.bookLink = "";
	}
	
	public int getBookId() {
		return bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public String getBookPublication() {
		return bookPublication;
	}

	public String getBookEdition() {
		return bookEdition;
	}

	public int getBookYear() {
		return bookYear;
	}

	public String getBookSummary() {
		return bookSummary;
	}
	
	public String getBookLink() {
		return bookLink;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public void setBookPublication(String bookPublication) {
		this.bookPublication = bookPublication;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	public void setBookYear(int bookYear) {
		this.bookYear = bookYear;
	}

	public void setBookSummary(String bookSummary) {
		this.bookSummary = bookSummary;
	}
	
	public void setBookLink(String bookLink) {
		this.bookLink = bookLink;
	}

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

	public void setBook(Book setBook,Book book) {
		setBook.bookId = book.getBookId();
		setBook.bookTitle = book.getBookTitle();
		setBook.bookAuthor = book.getBookAuthor();
		setBook.bookPublication = book.getBookPublication();
		setBook.bookEdition = book.getBookEdition();
		setBook.bookYear = book.getBookYear();
		setBook.bookSummary = book.getBookSummary();
		setBook.bookLink = book.getBookLink();
	}
	
	public Book getBook() {
		Book bk = new Book();
		bk.bookId = bookId;
		bk.bookTitle = bookTitle;
		bk.bookAuthor = bookAuthor;
		bk.bookPublication = bookPublication;
		bk.bookEdition = bookEdition;
		bk.bookYear = bookYear;
		bk.bookSummary = bookSummary;
		bk.bookLink = bookLink; 
		return bk;
	}
	
	@Override
	public String toString() {
		return "Book Title =" + bookTitle + ", Author=" + bookAuthor + ", Publication=" + bookPublication
				+ ", Edition=" + bookEdition + ", Year=" + bookYear ;
	}
}