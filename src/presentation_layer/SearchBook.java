package presentation_layer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import business_layer.*;
import data_layer.BookDB;


@SuppressWarnings("serial")
public class SearchBook<Bookdb> extends JPanel {
	private JLabel lblHeading;
	private JTextField txtBookSearch;
	private JRadioButton rdBookId,rdBookName,rdSelectAll;
	private JButton butSearch;
	
	private String[] genre= {"","Fantasy","Adventure","Romance","Contemporary","Dystopian"};
	private JComboBox bookCategory=new JComboBox(genre);
	
	private String[] searchType= {"Select from the List Below :","BOOK_ISBN","BOOK_NAME","PUBLISHER_NAME","GENRE","SELECT ALL"};
	private JComboBox bookSearchMethod=new JComboBox(searchType);

	String selectedBook;
	private BookDB db = null;
	
	DefaultTableModel model ;
	static JTable table;
	String[] columnNames = {"BOOK_ISBN","BOOK_NAME","PUBLISHER_NAME","GENRE","PRICE","QUANTITY"};
	private JScrollPane scroll;
	

	public SearchBook() 
	{
		this.intialize();
		
		try 
		{	
			db = new BookDB();
		} 
		catch (ClassNotFoundException  | SQLException e)
		{
			showException(e);
			System.exit(1);	
		}
	}
    
	private void intialize() {
		this.setBackground(new Color(135,206,235));
		lblHeading=new JLabel("Search By :");
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHeading.setBounds(41, 102, 129, 25);
		
		rdBookId=new JRadioButton("Book ID");
		rdBookId.setBounds(176, 105, 107, 25);
		
		rdBookName=new JRadioButton("Book Name");
		rdBookName.setBounds(295, 105, 107, 25);
		
		
		rdSelectAll=new JRadioButton("Select All Books");
		rdSelectAll.setBounds(256, 133, 146, 25);

		
		ButtonGroup bg=new ButtonGroup();
	
		bg.add(rdBookId);
		bg.add(rdBookName);
		bg.add(rdSelectAll);
	

		txtBookSearch = new JTextField();
		txtBookSearch.setBounds(176, 160, 226, 22);
		txtBookSearch.setColumns(40);
		
		JPanel panel = new JPanel();
		JLabel lblNewLabel1 = new JLabel("SEARCH BOOK");
		lblNewLabel1.setForeground(new Color(255, 204,102 ));
		lblNewLabel1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));
		
		
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 710, 46);
		panel.add(lblNewLabel1);
		add(panel);

		butSearch = new JButton("Search");
		butSearch.setBounds(408, 159, 90, 25);
		butSearch.setBackground(Color.WHITE);
		butSearch.setForeground(Color.BLACK);

		setLayout(null);
        
		this.add(lblHeading);
		bookCategory.setBackground(Color.WHITE);
		bookCategory.setForeground(Color.BLACK);
		bookCategory.setModel(new DefaultComboBoxModel(new String[] {"Select Genre Type...", "Fantasy", "Adventure", "Romance", "Contemporary", "Dystopian"}));
		

		bookCategory.setSize(100, 25);
		bookCategory.setBounds(176, 160, 226, 22);
		bookCategory.setVisible(false);
		
		bookSearchMethod.setSize(226, 25);
		bookSearchMethod.setLocation(180, 105);
		bookSearchMethod.setSelectedItem("Please Select from the List Below :");
		bookSearchMethod.setForeground(Color.BLUE);
		bookSearchMethod.setFont(new Font("Arial", Font.BOLD, 14));
		
		this.add(bookSearchMethod);
		this.add(bookCategory);
//		this.add(rdSelectAll);
		
		this.add(txtBookSearch);
		this.add(butSearch);
		
		butSearch.addActionListener(new SearchHandler());
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		
		model= new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		table = new JTable();
		table.setBackground(SystemColor.textHighlight);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		table.setForeground(SystemColor.textHighlightText);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		
		scroll = new JScrollPane(table);
		scroll.setSize(520, 150);
		scroll.setLocation(41, 200);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		
		
		
		bookSearchMethod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(bookSearchMethod.getSelectedItem()== "GENRE")
				{
					bookCategory.setVisible(true);
					txtBookSearch.setVisible(false);
					
					int rows = model.getRowCount();
					if(rows > 0)
					{
					    for (int i = rows - 1; i > -1; i--) 
					    {
					    	model.removeRow(i);				
					    }
					}
				}
				else if(bookSearchMethod.getSelectedItem()== "SELECT ALL")
				{
					
					bookCategory.setVisible(false);
					txtBookSearch.setVisible(false);
					
					int rows = model.getRowCount();
					if(rows > 0)
					{
					    for (int i = rows - 1; i > -1; i--) 
					    {
					    	model.removeRow(i);				
					    }
					}
				}	
				else 
				{
					bookCategory.setVisible(false);
					txtBookSearch.setVisible(true);
					
					int rows = model.getRowCount();
					if(rows > 0)
					{
					    for (int i = rows - 1; i > -1; i--) 
					    {
					    	model.removeRow(i);				
					    }
					}
				}	
			}
		});
    }
	
	
    private class SearchHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			int rows = model.getRowCount();
			if(rows > 0)
			{
			    for (int i = rows - 1; i > -1; i--) 
			    {
			    	model.removeRow(i);				
			    }
			}
			
					String selectedType = (String) bookSearchMethod.getSelectedItem();
				
					switch(selectedType)
					{
					case "BOOK_ISBN":
						showBookIsbn();
						break;
						
					case "BOOK_NAME":
						showBookName();
						break;
						
					case "PUBLISHER_NAME":
						showBookPublisherName();
						break;
						
					case "GENRE":
						showBookGenre();
						break;
						
					case "SELECT ALL":
						try {showBookAll();}
						catch (Exception e1) {e1.printStackTrace();}
						break;
						
					default :
						JOptionPane.showMessageDialog(null, "Select some Option");						
						break;
			}					
		}
    }
    
    private void showBookIsbn()
    {
    	Books b ;
		if(txtBookSearch.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Enter Book ISBN to Search");
		}
		else
		{
			try {
				b=db.SearchBookISBN(Integer.parseInt(txtBookSearch.getText()));
					if(b!=null)
					{
						int table_b_id = b.getBookIsbn();
						String table_b_name = b.getBookName();
						String table_b_pb_name = b.getPublisherName();
						String table_b_genre = b.getGenre();
						double table_b_price = b.getPrice();
						int table_b_quantity = b.getQuantity();
						
						model.addRow(new Object[]{table_b_id,table_b_name,table_b_pb_name,table_b_genre,table_b_price,table_b_quantity});
						txtBookSearch.setText("");
					}
						else		
							{JOptionPane.showMessageDialog(null, "BOOK ISBN not found in our Record");}
				}		
			catch (Exception e1) 
			{	JOptionPane.showMessageDialog(null, "BOOK ISBN not found in our Record");  }
		}    	
    }
		
    private void showBookName()
    {
    	Books bName;
		if(txtBookSearch.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Enter BOOK NAME to Search");
		}
		else
		{	String bookNames = txtBookSearch.getText();
			try {
				bName = db.SearchBookName(bookNames);
					if(bName!=null)
					{
						int table_b_id = bName.getBookIsbn();
						String table_b_name = bName.getBookName();
						String table_b_pb_name = bName.getPublisherName();
						String table_b_genre = bName.getGenre();
						double table_b_price = bName.getPrice();
						int table_b_quantity = bName.getQuantity();
						model.addRow(new Object[]{table_b_id,table_b_name,table_b_pb_name,table_b_genre,table_b_price,table_b_quantity});
						txtBookSearch.setText("");
					}
						else
						{	JOptionPane.showMessageDialog(null, "BOOK NAME not found in our Record");}
			} catch (Exception e1)
			{JOptionPane.showMessageDialog(null, "BOOK NAME not found in our Record");}
		}    	
    }

    private void showBookPublisherName()
    {
    	ArrayList<Books> b;					
    	Object[] row = null;
    	if(txtBookSearch.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Enter PUBLISHER NAME to Search");
		}
		else
		{
			String publisherName = txtBookSearch.getText();		
			try {
					b = db.SearchBookPublisherName(publisherName);
					if(b!=null)
					{	
						System.out.println(b);				
						  row = new Object[6];
							 for(int i=0;i<b.size();i++)
					        {
					            row[0]=b.get(i).getBookIsbn();
					            row[1]=b.get(i).getBookName();
					            row[2]=b.get(i).getPublisherName();
					            row[3]=b.get(i).getGenre();
					            row[4]=b.get(i).getPrice();
					            row[5]=b.get(i).getQuantity();
					            model.addRow(row);             
					        }
						}

						else
						{JOptionPane.showMessageDialog(null, "PUBLISHER NAME not found in our Record");}
				b.clear();
			}
			catch (Exception e1)
			{JOptionPane.showMessageDialog(null, "PUBLISHER NAME not found in our Record");}
		}    	
    }
       
    private void showBookGenre() 
    {
    	ArrayList<Books> b;
    	selectedBook =(String) bookCategory.getSelectedItem();				
			try {
				 b = db.SearchBookGenre(selectedBook);
				if(b!=null)
					{ 
					System.out.println(b);
					
						 Object[] row = new Object[6];
							 for(int i=0;i<b.size();i++)
					        {
					            row[0]=b.get(i).getBookIsbn();
					            row[1]=b.get(i).getBookName();
					            row[2]=b.get(i).getPublisherName();
					            row[3]=b.get(i).getGenre();
					            row[4]=b.get(i).getPrice();
					            row[5]=b.get(i).getQuantity();
					            	model.addRow(row);             
					        }
					}					

						else
						{
							JOptionPane.showMessageDialog(null, "BOOKS with these GENRE not found in our Record");
						}
				b.clear();

			} catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, "BOOKS with these GENRE not found in our Record");
			}
		}    	

    private void showBookAll() throws Exception
    {
    	ArrayList<Books> b;
    	try {
			 b = db.SearchBookAll(null);
    		if(b!=null)
				{ 		
					 Object[] row = new Object[6];
				        for(int i=0;i<b.size();i++)
				        {
				            row[0]=b.get(i).getBookIsbn();
				            row[1]=b.get(i).getBookName();
				            row[2]=b.get(i).getPublisherName();
				            row[3]=b.get(i).getGenre();
				            row[4]=b.get(i).getPrice();
				            row[5]=b.get(i).getQuantity();
				            model.addRow(row);
				        }
				}
				else
				{JOptionPane.showMessageDialog(null, "BOOKS not found in our Record");}			
    		b.clear();
    		} 
    	catch (Exception e1)
	{JOptionPane.showMessageDialog(null, "BOOKS not found in our Record");}

	
    }
 
	private void showException(Exception e)
	{
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
	}

    public boolean isValidData() {
		if (!Validator.isPresent(txtBookSearch, "Book Information"))
			return false;
	
		return true;
	}
}