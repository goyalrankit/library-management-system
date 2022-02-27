package library_management;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

	public class ReturnPage extends JPanel {

		private JLabel  lblBookName,lblBookId,lblUserName,lblUserId,lblIssueDate,lblReturnDate,lblFines;
		private JTextField txtBookName,txtBookId,txtUserName,txtUserId,txtIssueDate,txtReturnDate,txtFines;
		private JPanel panel;
		private JButton btnReturn,btnCancel;
		
		public ReturnPage() 
		{
			this.sampleCode();
		}
		
		private void sampleCode()
		{
			
 			panel = new JPanel();
			setLayout(new GridLayout(8,2));		
			setBackground(Color.CYAN);


			lblIssueDate = new JLabel("ISSUE DATE");
			lblIssueDate.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblIssueDate.setBounds(357, 70, 90, 14);
			add(lblIssueDate);
			
			txtIssueDate = new JTextField();
			txtIssueDate.setBounds(492, 68, 138, 20);
			add(txtIssueDate);
			txtIssueDate.setColumns(10);
			
		
			lblBookId = new JLabel("BOOK ISBN");
			lblBookId.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblBookId.setBounds(357, 100, 90, 14);
			add(lblBookId);
			
			txtBookId = new JTextField();
			txtBookId.setBounds(492, 98, 138, 20);
			add(txtBookId);
			txtBookId.setColumns(10);

			
			lblBookName = new JLabel("BOOK NAME");
			lblBookName.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblBookName.setBounds(357, 130, 119, 14);
			add(lblBookName);
			
			txtBookName = new JTextField();
			txtBookName.setBounds(492, 128, 138, 20);
			add(txtBookName);
			txtBookName.setColumns(10);
			

			lblUserId = new JLabel("USER ID");
			lblUserId.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblUserId.setBounds(357, 160, 90, 14);
			add(lblUserId);
			
			txtUserId = new JTextField();
			txtUserId.setBounds(492, 158, 138, 20);
			add(txtUserId);
			txtUserId.setColumns(10);
			

			
			lblUserName = new JLabel("USERNAME");
			lblUserName.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblUserName.setBounds(357, 190, 90, 14);
			add(lblUserName);
			
			txtUserName = new JTextField();
			txtUserName.setBounds(492, 188, 138, 20);
			add(txtUserName);
			txtUserName.setColumns(10);

			
			lblReturnDate = new JLabel("RETURN DATE");
			lblReturnDate.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblReturnDate.setBounds(357, 220, 119, 14);
			add(lblReturnDate);
			
			txtReturnDate = new JTextField();
			txtReturnDate.setBounds(492, 218, 138, 20);
			add(txtReturnDate);
			txtReturnDate.setColumns(10);

					
			lblFines = new JLabel("FINE");
			lblFines.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblFines.setBounds(357, 250, 90, 14);
			add(lblFines);

			txtFines = new JTextField();
			txtFines.setBounds(492, 248, 138, 20);
			add(txtFines);
			txtFines.setColumns(10);

			btnReturn = new JButton("RETURN BOOK");
			btnReturn.setBackground(Color.black);
			btnReturn.setForeground(Color.WHITE);
			add(btnReturn);
			
			btnCancel = new JButton("CANCEL");
			btnCancel.setBackground(Color.RED);
			btnCancel.setForeground(Color.WHITE);
			add(btnCancel);
		}


	public static void main(String[] args) {

	}

}
