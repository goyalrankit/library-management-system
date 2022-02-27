package data_layer;

import java.sql.*;
import java.util.ArrayList;

import business_layer.Books;
import business_layer.IssueBook;

public class BookDB {
	protected Connection conn = null;
	protected ResultSet rs = null;
	protected Statement stm = null;
	ArrayList<Books> bookList = new ArrayList<>();
	ArrayList<IssueBook> returnList = new ArrayList<>();

	public BookDB() throws SQLException, ClassNotFoundException {
		this.connect();
	}

	protected void connect() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// Add database connectivity here 
		String url = "";
		String username = "";
		String password = "";
		conn = DriverManager.getConnection(url, username, password);
	}

	public void disconnect() throws SQLException {
		if (!rs.isClosed()) {
			rs.close();
			conn.close();
		}
	}

	public void refresh() throws SQLException {
		String sql = "Select * From books";
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = stm.executeQuery(sql);
		rs.first();
	}

	public int addBook(Books b) throws SQLException {
		int count = 0;
		String query = "Insert into books values (?,?,?,?,?,?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1, b.getBookIsbn());
		preparedStmt.setString(2, b.getBookName());
		preparedStmt.setString(3, b.getPublisherName());
		preparedStmt.setString(4, b.getGenre());
		preparedStmt.setDouble(5, b.getPrice());
		preparedStmt.setInt(6, b.getQuantity());
		preparedStmt.executeUpdate();
		this.refresh();
		return count;
	}

	public int deleteBook(int id) throws SQLException {
		int count = 0;
		String sql = "delete from Books where BOOK_ISBN = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		this.refresh();
		return count;
	}

	public int updateBook(Books b) throws Exception {

		int count = 0;
		String query = "update BOOKS set BOOK_NAME=?,PUBLISHER_NAME=?,GENRE=?,PRICE=?,QUANTITY=? where BOOK_ISBN=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(6, b.getBookIsbn());
		preparedStmt.setString(1, b.getBookName());
		preparedStmt.setString(2, b.getPublisherName());
		preparedStmt.setString(3, b.getGenre());
		preparedStmt.setDouble(4, b.getPrice());
		preparedStmt.setInt(5, b.getQuantity());
		preparedStmt.executeUpdate();
		this.refresh();
		return count;
	}

	public Books SearchBookISBN(int bookId) throws Exception {
		String query = "Select * From books where BOOK_ISBN=" + bookId;
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = stm.executeQuery(query);
		Books b = null;
		while (rs.next()) {
			b = new Books();
			b.setBookIsbn(rs.getInt("BOOK_ISBN"));
			b.setBookName(rs.getString("BOOK_NAME"));
			b.setPublisherName(rs.getString("PUBLISHER_NAME"));
			b.setGenre(rs.getString("GENRE"));
			b.setPrice(rs.getInt("PRICE"));
			b.setQuantity(rs.getInt("QUANTITY"));

		}
		return b;
	}

	public Books SearchBookName(String bookName) throws Exception {
		String query = "Select * From books where LOWER(BOOK_NAME) LIKE?";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, bookName.toLowerCase());
		rs = preparedStmt.executeQuery();

		Books b = null;
		while (rs.next()) {
			b = new Books();
			b.setBookIsbn(rs.getInt("BOOK_ISBN"));
			b.setBookName(rs.getString("BOOK_NAME"));
			b.setPublisherName(rs.getString("PUBLISHER_NAME"));
			b.setGenre(rs.getString("GENRE"));
			b.setPrice(rs.getInt("PRICE"));
			b.setQuantity(rs.getInt("QUANTITY"));

		}
		return b;
	}

	public ArrayList<Books> SearchBookPublisherName(String b_pb_name) throws Exception {
		String query = "Select * From books where UPPER(PUBLISHER_NAME) LIKE?";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, b_pb_name.toUpperCase());

		rs = preparedStmt.executeQuery();

		Books b = null;

		while (rs.next()) {
			b = new Books(rs.getInt("BOOK_ISBN"), rs.getString("BOOK_NAME"), rs.getString("PUBLISHER_NAME"),
					rs.getString("GENRE"), rs.getInt("PRICE"), rs.getInt("QUANTITY"));
			bookList.add(b);
		}
		return bookList;
	}

	public ArrayList<Books> SearchBookGenre(String selectedBook) throws SQLException {
		String query = "Select * from books where (GENRE) LIKE?";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, selectedBook);

		ResultSet rs = preparedStmt.executeQuery();

		Books b = null;

		while (rs.next()) {
			b = new Books(rs.getInt("BOOK_ISBN"), rs.getString("BOOK_NAME"), rs.getString("PUBLISHER_NAME"),
					rs.getString("GENRE"), rs.getInt("PRICE"), rs.getInt("QUANTITY"));
			bookList.add(b);
		}
		return bookList;
	}

	public ArrayList<Books> SearchBookAll(ArrayList<Books> aB) throws Exception {
		String sql = "Select * From books";
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);
		Books b;
		while (rs.next()) {
			b = new Books(rs.getInt("BOOK_ISBN"), rs.getString("BOOK_NAME"), rs.getString("PUBLISHER_NAME"),
					rs.getString("GENRE"), rs.getInt("PRICE"), rs.getInt("QUANTITY"));
			bookList.add(b);
		}
		return bookList;
	}

	public int issueBook(IssueBook issue) throws SQLException {
		int count = 0;
		String query = "Insert into ISSUE_BOOK values (?,?,?,?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setInt(1, issue.getIsbn());
		preparedStmt.setString(2, issue.getUser());
		preparedStmt.setDate(3, issue.getIssueDate());
		preparedStmt.setDate(4, java.sql.Date.valueOf(issue.getReturnDate()));

		preparedStmt.executeUpdate();
		this.refresh();
		return count;
	}

	public IssueBook ReturnAllBook(String user, int bookId) throws SQLException {

		String sql = "Select * From ISSUE_BOOK where USER_ID LIKE?" + "and BOOK_ISBN=?";

		PreparedStatement preparedStmt = conn.prepareStatement(sql);
		preparedStmt.setString(1, user);
		preparedStmt.setInt(2, bookId);
		rs = preparedStmt.executeQuery();

		IssueBook iB = null;
		while (rs.next()) {
			iB = new IssueBook(rs.getInt("BOOK_ISBN"), rs.getString("USER_ID"), rs.getDate("ISSUE_DATE"),
					rs.getDate("RETURN_DATE").toLocalDate());

		}
		return iB;
	}

	public int deleteReturnBooks(String userId, int bookId) throws SQLException {
		int count = 0;
		String sql = "delete from ISSUE_BOOK where USER_ID=? and BOOK_ISBN=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userId);
		ps.setInt(2, bookId);
		ps.executeUpdate();
		this.refresh();
		return count;
	}

	public IssueBook ReturnAllUsersBook(String user) throws SQLException {

		String sql = "Select * From ISSUE_BOOK where USER_ID LIKE?";
		PreparedStatement preparedStmt = conn.prepareStatement(sql);
		preparedStmt.setString(1, user);
		rs = preparedStmt.executeQuery();

		IssueBook iB = null;
		while (rs.next()) {
			iB = new IssueBook(rs.getInt("BOOK_ISBN"), rs.getString("USER_ID"), rs.getDate("ISSUE_DATE"),
					rs.getDate("RETURN_DATE").toLocalDate());

		}
		return iB;
	}

	public Books getBook(int id) throws SQLException {
		String sql = "Select * From books where BOOK_ISBN=" + id;
		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = stm.executeQuery(sql);
		Books b = null;
		while (rs.next()) {
			b = new Books();
			b.setBookIsbn(rs.getInt("BOOK_ISBN"));
			b.setBookName(rs.getString("BOOK_NAME"));
			b.setPublisherName(rs.getString("PUBLISHER_NAME"));
			b.setGenre(rs.getString("GENRE"));
			b.setPrice(rs.getInt("PRICE"));
			b.setQuantity(rs.getInt("QUANTITY"));

		}
		return b;

	}

}
