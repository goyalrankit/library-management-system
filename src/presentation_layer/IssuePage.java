package presentation_layer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.time.LocalDate;


import javax.swing.*;

import business_layer.*;
import data_layer.AddUserDAO;
import data_layer.BookDB;
import data_layer.DAOFactory;

@SuppressWarnings("serial")
public class IssuePage extends JPanel {

	private JLabel lblBookId, lblUserId;
	private JTextField txtBookId, txtUserId;

	private JButton btnIssue, btnCancel;
	private BookDB db = null;
	private AddUserDAO auDAO = DAOFactory.getAddUserDAO();

	AddUsers ul;

	public IssuePage() {
		this.sampleCode();
		try {
			db = new BookDB();

		} catch (ClassNotFoundException | SQLException sqle) {
			showException(sqle);
			System.exit(1);
		}
	}

	private void sampleCode() {
		setBackground(new Color(135, 206, 235));
		setLayout(null);

		lblBookId = new JLabel("BOOK ID");
		lblBookId.setBounds(79, 172, 122, 54);
		lblBookId.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
		add(lblBookId);

		txtBookId = new JTextField();
		txtBookId.setBounds(230, 180, 313, 40);
		add(txtBookId);
		txtBookId.setColumns(10);

		lblUserId = new JLabel("USER ID");
		lblUserId.setBounds(79, 91, 122, 54);
		lblUserId.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
		add(lblUserId);

		txtUserId = new JTextField();
		txtUserId.setBounds(230, 99, 313, 40);
		add(txtUserId);
		txtUserId.setColumns(10);

		btnIssue = new JButton("ISSUE BOOK");
		btnIssue.setBackground(Color.black);
		btnIssue.setForeground(Color.WHITE);
		btnIssue.setBounds(108, 331, 176, 54);
		add(btnIssue);
		btnIssue.addActionListener(new IssueButtonHandler());

		btnCancel = new JButton("CANCEL");
		btnCancel.setBackground(Color.RED);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBounds(350, 331, 176, 54);
		add(btnCancel);
		btnCancel.addActionListener(new CancelButtonHandler());

		JPanel panel = new JPanel();
		JLabel lblNewLabel1 = new JLabel("ISSUE BOOK");
		lblNewLabel1.setForeground(new Color(255, 204, 102));
		lblNewLabel1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));

		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 710, 46);
		panel.add(lblNewLabel1);
		add(panel);
	}

	private class IssueButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			IssueBook issue = new IssueBook();
			Books b = null;
			String userType;
			int maxBooksIssued;
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			LocalDate localDate = LocalDate.now();
			LocalDate returnDate = null;

			int bookId = Integer.parseInt(txtBookId.getText()); 

			// Gets the User ID
			String userId = txtUserId.getText();
			
			// Find he USER
			ul = auDAO.getAddUser(userId);   		
			
			// Will add user later on using Constructor
		try{	
			if (ul != null)   
			{
				
				AddUsers addUsers = new AddUsers(ul.getUserId(), ul.getUserName());			
				
				// Gets the Type of User
				 userType = ul.getUserType(); 
				
				// Get the Books User have
				 maxBooksIssued = ul.getIssuedBooks(); 
				
				if (userType.equalsIgnoreCase("Student"))
				{
					if(maxBooksIssued >= 5)  
					{
						JOptionPane.showMessageDialog(null, "Maximum Books issued to Students is 5" +"\n"+ "Return Books to get New Book", "Issue Book",
								JOptionPane.WARNING_MESSAGE);
					} 
					else 
					{					
						try {
							// Get the books which is enter by USER
							
							b = db.getBook(bookId);       
							if (b != null) 
							{           
								// Gets the quantity
								int quantity = b.getQuantity(); 
								
								if (quantity > 0) 
								 {
									quantity--;      //19
									b.setQuantity(quantity); //19
									try 
									{
										db.updateBook(b);										
									} 
									catch (Exception e1) 
									{e1.printStackTrace();}
									
									returnDate = localDate.plusDays(7);
									
									issue.setIsbn(bookId);
									issue.setUserId(userId);
									issue.setIssueDate(date);
									issue.setReturnDate(returnDate);
		
									db.issueBook(issue);
									
									auDAO.delUser(ul.getUserId());
									int newMaxBooks = maxBooksIssued +1; 
									try
									{
										addUsers.setUserId(ul.getUserId());
										addUsers.setUserType(ul.getUserType());
										addUsers.setPhone(ul.getPhone());
										addUsers.setUserPass(ul.getUserPass());
										addUsers.setIssuedBooks(newMaxBooks);
										auDAO.addAddUser(addUsers);
									}
									catch( Exception e1)
									{
										b.setQuantity(quantity+1);
										db.updateBook(b);
										
										JOptionPane.showMessageDialog(null, "Book cannot be Issued");
									}
								
									String result = "Book Isbn = " + bookId + "\nUser ID = " + userId + "\nIssue Date = "
											+ date + "\nReturn Date = " + returnDate;
									JOptionPane.showMessageDialog(null, result, "Book is issued",
											JOptionPane.INFORMATION_MESSAGE);
									txtBookId.setText("");
									txtUserId.setText("");

								}
								else{JOptionPane.showMessageDialog(null, "Book is currently not available." + "\n All the Books with this ID were ISSUED");}				
							}
							else{JOptionPane.showMessageDialog(null, "Book ISBN does not match with our Record.");}				
							
						}catch(Exception e1) 
						{JOptionPane.showMessageDialog(null, "Book ISBN does not match with our Record.");}
					}
				}
				else if (userType.equalsIgnoreCase("Faculty")) 
				{
					if(maxBooksIssued >= 15)
					{
						JOptionPane.showMessageDialog(null, "Maximum Books issued to Faculty are 15" +"\n"+ "Return Books to get New Book", "Issue Book",
								JOptionPane.WARNING_MESSAGE);
					} 
					else 
					{					
						try {
							// Get the books which is enter by USER
							
							b = db.getBook(bookId);       
							if (b != null) 
							{            
								// Gets the quantity
								int quantity = b.getQuantity(); 
								
								if (quantity > 0) 
								 {
									quantity--;      //19
									b.setQuantity(quantity); //19
									try 
									{
										db.updateBook(b);										
									} 
									catch (Exception e1) 
									{e1.printStackTrace();}

									returnDate = localDate.plusDays(15);
									
									issue.setIsbn(bookId);
									issue.setUserId(userId);
									issue.setIssueDate(date);
									issue.setReturnDate(returnDate);
		
									db.issueBook(issue);
									
									auDAO.delUser(ul.getUserId());
									int newMaxBooks = maxBooksIssued +1; 
									try
									{
										addUsers.setUserId(ul.getUserId());
										addUsers.setUserType(ul.getUserType());
										addUsers.setPhone(ul.getPhone());
										addUsers.setUserPass(ul.getUserPass());
										addUsers.setIssuedBooks(newMaxBooks);
										auDAO.addAddUser(addUsers);
									}
									catch( Exception e1)
									{	
										// If the Book quantity is changed but any error occures. Then the quantity reset code.
										b.setQuantity(quantity+1);
										db.updateBook(b);	
										JOptionPane.showMessageDialog(null, "Book cannot be Issued");
									}
									
									String result = "Book Isbn = " + bookId + "\nUser ID = " + userId + "\nIssue Date = "
											+ date + "\nReturn Date = " + returnDate;
									JOptionPane.showMessageDialog(null, result, "Book is issued",JOptionPane.INFORMATION_MESSAGE);
									txtBookId.setText("");
									txtUserId.setText("");
								}
								else{JOptionPane.showMessageDialog(null, "Book is currently not available." + "\n All the Books with this ID were ISSUED");}				
							}
							else{JOptionPane.showMessageDialog(null, "Book ISBN does not match with our Record.");}				
							
						}catch(Exception e1) 
						{JOptionPane.showMessageDialog(null, "Book ISBN does not match with our Record.");}
					}
				}
			}else {
			JOptionPane.showMessageDialog(null, "User does not match in our record", "Issue Book",JOptionPane.WARNING_MESSAGE);}
		}
		catch(NumberFormatException e1)
		{JOptionPane.showMessageDialog(null, "User does not match in our record", "Issue Book",JOptionPane.WARNING_MESSAGE);}
	}
	
}

	private class CancelButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Issue Book Operation is Cancelled", "Issue Book",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void showException(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}

	public boolean isValidData() {

		if (!Validator.isPresent(txtUserId, "User ID"))
			return false;
		if (!Validator.isPresent(txtBookId, "Book ID"))
			return false;

		return true;
	}
}