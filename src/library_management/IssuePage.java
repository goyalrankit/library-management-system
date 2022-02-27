package library_management;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IssuePage extends JPanel {

		private JLabel  lblBookName,lblBookId,lblUserName,lblUserId,lblIssueDate,lblReturnDate;
		private JTextField txtBookName,txtBookId,txtUserName,txtUserId,txtIssueDate,txtReturnDate;
		private JPanel panel;
		private JButton btnIssue,btnCancel;
		
		public IssuePage() 
		{
			this.sampleCode();
		}
		
		private void sampleCode()
		{
 			panel = new JPanel();
			setLayout(new GridLayout(8,2));		
			setBackground(Color.CYAN);

					lblIssueDate = new JLabel("			ISSUE DATE");
					lblIssueDate.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
					add(lblIssueDate);
					
					txtIssueDate = new JTextField();
					add(txtIssueDate);
					txtIssueDate.setColumns(10);

			
					lblBookId = new JLabel("			BOOK ISBN");
					lblBookId.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
					add(lblBookId);
			
					txtBookId = new JTextField();
					add(txtBookId);
					txtBookId.setColumns(10);
			
					lblBookName = new JLabel("			BOOK NAME");
					lblBookName.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
					add(lblBookName);
			
					txtBookName = new JTextField();
					add(txtBookName);
					txtBookName.setColumns(10);
					
					lblUserId = new JLabel("			USER ID");
					lblUserId.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
					add(lblUserId);
			
			
					txtUserId = new JTextField();
					add(txtUserId);
					txtUserId.setColumns(10);
			
					lblUserName = new JLabel("			USERNAME");
					lblUserName.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
					add(lblUserName);
			

					txtUserName = new JTextField();
					add(txtUserName);
					txtUserName.setColumns(10);
					
					lblReturnDate = new JLabel("		RETURN DATE");
					lblReturnDate.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
					add(lblReturnDate);
					

					txtReturnDate = new JTextField();
					add(txtReturnDate);
					txtReturnDate.setColumns(10);
					
					
					btnIssue = new JButton("ISSUE BOOK");
					btnIssue.setBackground(Color.black);
					btnIssue.setForeground(Color.WHITE);
					btnIssue.setBounds(357, 317, 119, 23);
					add(btnIssue);
					
					
					btnCancel = new JButton("CANCEL");
					btnCancel.setBackground(Color.RED);
					btnCancel.setForeground(Color.WHITE);
					btnCancel.setBounds(357, 317, 119, 23);
					add(btnCancel);



		}
}
