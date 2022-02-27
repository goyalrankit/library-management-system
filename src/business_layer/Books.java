package business_layer;

public class Books {

	String bookName, publisherName, genre;
	int bookIsbn, quantity;
	double price;

	public Books() {
	}

	public Books(int isbn, String bookName1, String pbName, String genre1, double price1, int quantity1) {
		bookIsbn = isbn;
		bookName = bookName1;
		publisherName = pbName;
		genre = genre1;
		price = price1;
		quantity = quantity1;
	}

	public Books(int bookIsbn2) 
	{
		this.bookIsbn = bookIsbn2;		
	}
	
	public Books(String rName2) 
	{
		this.bookName = rName2;
		this.publisherName = rName2;
		this.genre = rName2;
	}


	public String getBookName() {
		return bookName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public int getBookIsbn() {
		return bookIsbn;
	}
	public String getGenre() {
		return genre;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}

	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public void setBookIsbn(int isbn) {
		bookIsbn = isbn;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	}