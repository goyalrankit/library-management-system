package business_layer;

import java.sql.Date;
import java.time.LocalDate;
public class IssueBook {
	
	int isbn;
	String user_id;
	Date date1;
	LocalDate date2;

	public IssueBook(int isbn,String user, Date date,LocalDate ld) 
	{
		this.isbn =isbn;
		this.user_id = user;
		this.date1 =date;
		this.date2 = ld;
	}
	
	public IssueBook() {
	}

	public void setIsbn(int isbn) 
	{
		this.isbn=isbn;
	}
	
	public int getIsbn() {
		return isbn;
	}
	public void setUserId(String id) {
		user_id=id;
	}
    public String getUser() {
    	return user_id;
    }
    public void setIssueDate(Date date)
    {
    	date1=date;
    }
    public Date getIssueDate() {
    	return date1;
    }
    public void setReturnDate(LocalDate returnDate) {
    	date2=returnDate;
    }
  
	public LocalDate getReturnDate() 
	{	
	return date2;
	}
	
}