package presentation_layer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import business_layer.*;
import java.sql.SQLException;
import java.time.LocalDate;
import data_layer.AddUserDAO;
import data_layer.BookDB;
import data_layer.DAOFactory;

@SuppressWarnings("serial")

public class ReturnPage extends JPanel {

	BookDB db=null;
	private JLabel lblBookId;
	private JTextField txtBookIsbn;

	private JButton btnReturn, btnCancel;
	private JTextField txtUserId;
	
	private AddUserDAO auDAO = DAOFactory.getAddUserDAO();

	AddUsers ul;


	public ReturnPage() {
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

		lblBookId = new JLabel("BOOK ISBN");
		lblBookId.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBookId.setBounds(72, 149, 98, 34);
		add(lblBookId);

		txtBookIsbn = new JTextField("ENTER BOOK-ISBN HERE");
		txtBookIsbn.setBounds(204, 155, 213, 27);
		add(txtBookIsbn);
		txtBookIsbn.setColumns(10);
		
		JLabel lblUserId = new JLabel("USER ID");
		lblUserId.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUserId.setBounds(72, 91, 73, 33);
		add(lblUserId);
		
		txtUserId = new JTextField("ENTER USER ID ");
		txtUserId.setBounds(204, 98, 213, 27);
		add(txtUserId);
		txtUserId.setColumns(10);

		btnReturn = new JButton("RETURN BOOK");
		btnReturn.setBounds(88, 229, 153, 34);
		btnReturn.setBackground(Color.black);
		btnReturn.setForeground(Color.WHITE);
		add(btnReturn);

		btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(253, 229, 140, 34);
		btnCancel.setBackground(Color.RED);
		btnCancel.setForeground(Color.WHITE);
		add(btnCancel);

		JPanel panel = new JPanel();
		JLabel lblNewLabel1 = new JLabel("RETURN BOOK");
		lblNewLabel1.setForeground(new Color(255, 204, 102));
		lblNewLabel1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));

		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 710, 46);
		panel.add(lblNewLabel1);
		add(panel);
		
		
		txtBookIsbn.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
               txtBookIsbn.setText("");
		     }
        });
		
		txtUserId.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
               txtUserId.setText("");
		     }
        });
		
		btnReturn.addActionListener(new ReturnButtonHandler());

		btnCancel.addActionListener(new CancelButtonHandler()); 
		
		
}
	
	
	
	private class ReturnButtonHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
	    	Books b = null;
			int maxBooksIssued;
			
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			LocalDate localDate = LocalDate.now();
			LocalDate rDate,returnDate = null;



			int bookId = Integer.parseInt(txtBookIsbn.getText()); 

			// Gets the User ID
			String userId = txtUserId.getText();
			
			// Find he USER
			ul = auDAO.getAddUser(userId);   		
			
			// Will add user later on using Constructor
		try{	
			if (ul != null)   
			{
				AddUsers addUsers = new AddUsers(ul.getUserId(), ul.getUserName());			
				
				 maxBooksIssued = ul.getIssuedBooks(); 
				 
				if(maxBooksIssued >0)
				{
					IssueBook  issue1;
					issue1 = db.ReturnAllBook(userId,bookId);
					
					if(issue1 != null)
					{
						b = db.getBook(bookId);       
						if (b != null) 
						{ 
							rDate= issue1.getReturnDate();
							System.out.println(rDate +""+localDate);
							
							if( rDate == localDate)
							{
								
								int quantity = b.getQuantity(); 
								
								try {
									db.deleteReturnBooks(userId, bookId);
									quantity++;
									b.setQuantity(quantity);
									try 
									{
										db.updateBook(b);  
										
										auDAO.delUser(ul.getUserId());
										int newMaxBooks = maxBooksIssued -1; 
										
										addUsers.setUserId(ul.getUserId());
										addUsers.setUserType(ul.getUserType());
										addUsers.setPhone(ul.getPhone());
										addUsers.setUserPass(ul.getUserPass());
										addUsers.setIssuedBooks(newMaxBooks);
										auDAO.addAddUser(addUsers);						
									} 
									catch (Exception e1) 
									{e1.printStackTrace();
									
									issue1.setIsbn(bookId);
									issue1.setUserId(userId);
									issue1.setIssueDate(date);
									issue1.setReturnDate(returnDate);
		
									db.issueBook(issue1);
									JOptionPane.showMessageDialog(null, "User update eror");
									}
								}
								catch(Exception e1) { e1.printStackTrace(); } 
							}
							else if( rDate.isAfter(localDate))    //  24  12
								{	
									int quantity = b.getQuantity(); 
								try {
									db.deleteReturnBooks(userId, bookId);
									quantity++;
									b.setQuantity(quantity);
									try 
									{
										db.updateBook(b);
										
										auDAO.delUser(ul.getUserId());
										int newMaxBooks = maxBooksIssued -1; 
										
										addUsers.setUserId(ul.getUserId());
										addUsers.setUserType(ul.getUserType());
										addUsers.setPhone(ul.getPhone());
										addUsers.setUserPass(ul.getUserPass());
										addUsers.setIssuedBooks(newMaxBooks);
										auDAO.addAddUser(addUsers);						
									} 
									catch (Exception e1) 
									{e1.printStackTrace();
									
									issue1.setIsbn(bookId);
									issue1.setUserId(userId);
									issue1.setIssueDate(date);
									issue1.setReturnDate(returnDate);
		
									db.issueBook(issue1);
									JOptionPane.showMessageDialog(null, "User update eror");
									}
								}
								catch(Exception e1) { e1.printStackTrace(); } 
								

								}
							else 
							{
								
								int quantity = b.getQuantity(); 
							try {
								db.deleteReturnBooks(userId, bookId);
								quantity++;
								b.setQuantity(quantity);
								try 
								{
									db.updateBook(b);
									
									auDAO.delUser(ul.getUserId());
									int newMaxBooks = maxBooksIssued -1; 
									
									addUsers.setUserId(ul.getUserId());
									addUsers.setUserType(ul.getUserType());
									addUsers.setPhone(ul.getPhone());
									addUsers.setUserPass(ul.getUserPass());
									addUsers.setIssuedBooks(newMaxBooks);
									auDAO.addAddUser(addUsers);						
								} 
								catch (Exception e1) 
								{e1.printStackTrace();
								
								issue1.setIsbn(bookId);
								issue1.setUserId(userId);
								issue1.setIssueDate(date);
								issue1.setReturnDate(returnDate);
	
								db.issueBook(issue1);
								JOptionPane.showMessageDialog(null, "User update eror");
								}
							}
							catch(Exception e1) { e1.printStackTrace(); } 
							JOptionPane.showMessageDialog(null, "FINE");

						}
						
						}else {System.out.println("Book not found");}
					
					}else {JOptionPane.showMessageDialog(null, "INVALID ENTRY");}
					
				}else {JOptionPane.showMessageDialog(null, "No book is issued to you");}
				
			}else {JOptionPane.showMessageDialog(null, "USER NOT FOUND");}			
			
		}catch(Exception e1) {};
	}
}
	
	private class CancelButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
						
			JOptionPane.showMessageDialog(null, "Return Book Operation is Cancelled", "Issue Book",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void showException(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	public boolean isValidData() {
		if (!Validator.isPresent(txtBookIsbn, "Book ID"))
			return false;
		if (!Validator.isPresent(txtUserId, "User ID"))
			return false;
		return true;
	}
}
