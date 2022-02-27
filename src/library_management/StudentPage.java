package library_management;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StudentPage extends JPanel {
	
		private JLabel lblName,lblId,lblAddress,lblPhone,lblEmail,lblPassword,lblConfirmPass;
		private JTextField txtName,txtId,txtAddress,txtPhone,txtEmail,txtPassword,txtConfirmPassword;
		private JPanel panel;
		private JButton btnAdd,btnCancel;
		

		StudentPage()
		{
			this.sampleCode();
		}
		
		
		private void sampleCode()
		{
 			panel = new JPanel();
			setLayout(new GridLayout(8,2));		
			setBackground(Color.CYAN);

					
			lblName = new JLabel("STUDENT NAME");
			lblName.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			add(lblName);
			
			
			txtName = new JTextField();
			txtName.setBounds(268, 70, 138, 20);
			add(txtName);
			txtName.setColumns(10);
						
			
			
			lblId = new JLabel("STUDENT ID");
			lblId.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblId.setBounds(114, 100, 126, 14);
			add(lblId);
			
			txtId = new JTextField();
			txtId.setBounds(268, 100, 138, 20);
			add(txtId);
			txtId.setColumns(10);

			
			lblAddress = new JLabel("ADDRESS");
			lblAddress.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblAddress.setBounds(114, 130, 90, 14);
			add(lblAddress);
			
			txtAddress = new JTextField();
			txtAddress.setBounds(268, 130, 138, 20);
			add(txtAddress);
			txtAddress.setColumns(10);
			

			lblPhone = new JLabel("PHONE");
			lblPhone.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblPhone.setBounds(114, 160, 90, 14);
			add(lblPhone);
			
			txtPhone = new JTextField();
			txtPhone.setBounds(268, 160, 138, 20);
			add(txtPhone);
			txtPhone.setColumns(10);

			
			lblEmail = new JLabel("EMAIL");
			lblEmail.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblEmail.setBounds(114, 190, 90, 14);
			add(lblEmail);
			
			txtEmail = new JTextField();
			txtEmail.setBounds(268, 190, 138, 20);
			add(txtEmail);
			txtEmail.setColumns(10);
			
			lblPassword = new JLabel("PASSWORD");
			lblPassword.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblPassword.setBounds(114, 220, 90, 14);
			add(lblPassword);
			
			txtPassword = new JTextField();
			txtPassword.setBounds(268, 220, 138, 20);
			add(txtPassword);
			txtPassword.setColumns(10);
			
			lblConfirmPass = new JLabel("CONFIRM PASS");
			lblConfirmPass.setFont(new Font("Century751 No2 BT", Font.BOLD, 14));
			lblConfirmPass.setBounds(114, 250, 144, 14);
			add(lblConfirmPass);
			
			txtConfirmPassword = new JTextField();
			add(txtConfirmPassword);
			txtConfirmPassword.setColumns(10);
			
			btnAdd = new JButton("Add User");
			btnAdd.setBackground(Color.black);
			btnAdd.setForeground(Color.WHITE);
			add(btnAdd);
			
			
			btnCancel = new JButton("CANCEL");
			btnCancel.setBackground(Color.RED);
			btnCancel.setForeground(Color.WHITE);
			add(btnCancel);
		
		
		}

	
	

	public static void main(String[] args) {

	}

}
