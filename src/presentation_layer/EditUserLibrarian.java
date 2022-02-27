package presentation_layer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import business_layer.*;
import data_layer.AddUserDAO;
import data_layer.DAOFactory;

@SuppressWarnings("serial")
public class EditUserLibrarian extends JPanel {
	
	private JButton butSearch,btnEditLibUser,btnDelLibUser;
	private JTextField txtUserSearch,txtName,txtPhone,txtPassord;
	private JLabel lblUserID,lblUserPhone,lblUserPass,lblUserName;

	String userID,updateUserName,updateUserPassword,userType;
    String  updateUserId, uId;
    
	private JTextField oldUserName,oldPhone,oldPass;
	private JPanel panel_1,panel_2;
	private JLabel lblAccountInformation,lblUpdateInformation;

    
	
	private AddUserDAO auDAO = DAOFactory.getAddUserDAO();
	private AddUserDAO adDAO = DAOFactory.getAddUserDAO();
	AddUsers ul;

	

	 public EditUserLibrarian() {
	
		this.intialize();
			userID = updateUserId;
	}
    
	private void intialize() {
		this.setBackground(new Color(135,206,235));
		ButtonGroup bg=new ButtonGroup();
	

		txtUserSearch = new JTextField("ID here");
		txtUserSearch.setBounds(140, 57, 189, 22);
		txtUserSearch.setColumns(40);
		
		txtUserSearch.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                txtUserSearch.setText("");
		     }
        });

		
		JPanel panel = new JPanel();
		JLabel lblNewLabel1 = new JLabel("EDIT USER");
		lblNewLabel1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel1.setForeground(new Color(255, 204,102 ));
		lblNewLabel1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 40));
		
		
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 710, 46);
		panel.add(lblNewLabel1);
		add(panel);


		butSearch = new JButton("VIEW   INFO");
		butSearch.setBounds(216, 90, 113, 25);
		setLayout(null);
		
	    lblUserID = new JLabel("ENTER USER ID");
		lblUserID.setBounds(22, 60, 107, 16);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		add(lblUserID);
		
		 lblUserName = new JLabel("NEW  USERNAME");
		lblUserName.setBounds(359, 180, 113, 14);
		add(lblUserName);
		
		txtName = new JTextField();
		txtName.setBounds(504, 177, 129, 20);
		add(txtName);
		txtName.setColumns(10);
		
		lblUserPhone = new JLabel("NEW  PHONE");
		lblUserPhone.setBounds(359, 209, 113, 14);
		add(lblUserPhone);
		
		 lblUserPass = new JLabel("NEW  PASSWORD ");
		lblUserPass.setBounds(359, 238, 113, 14);
		add(lblUserPass);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(504, 206, 129, 20);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		txtPassord = new JTextField();
		txtPassord.setBounds(504, 235, 129, 20);
		add(txtPassord);
		txtPassord.setColumns(10);
		
		btnEditLibUser = new JButton("UPDATE");
		btnEditLibUser.setBackground(Color.BLACK);
		btnEditLibUser.setForeground(Color.WHITE);
		btnEditLibUser.setBounds(545, 297, 89, 23);
		add(btnEditLibUser);
		
		
		
		btnDelLibUser = new JButton("Delete User");
        btnDelLibUser.setBounds(130, 280, 180, 37);
        btnDelLibUser.setBackground(Color.RED);
        btnDelLibUser.setForeground(Color.WHITE);
        btnDelLibUser.setMnemonic(KeyEvent.VK_D);

		this.add(txtUserSearch);
		this.add(butSearch);
		this.add(btnDelLibUser);
		btnDelLibUser.setVisible(false);

		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setBounds(47, 180, 90, 14);
		add(lblNewLabel);
		
		oldUserName = new JTextField();
		oldUserName.setBounds(155, 177, 119, 20);
		add(oldUserName);
		oldUserName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PHONE");
		lblNewLabel_1.setBounds(47, 209, 48, 14);
		add(lblNewLabel_1);
		
		oldPhone = new JTextField();
		oldPhone.setBounds(155, 206, 119, 20);
		add(oldPhone);
		oldPhone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setBounds(47, 238, 82, 14);
		add(lblNewLabel_2);
		
		oldPass = new JTextField();
		oldPass.setBounds(155, 235, 119, 20);
		add(oldPass);
		oldPass.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 204, 102));
		panel_1.setBounds(0, 131, 329, 245);
		add(panel_1);
		
		lblAccountInformation = new JLabel("USER INFORMATION");
		lblAccountInformation.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblAccountInformation);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 204, 102));
		panel_2.setBounds(333, 131, 364, 245);
		add(panel_2);
		
		lblUpdateInformation = new JLabel("UPDATE INFORMATION");
		lblUpdateInformation.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_2.add(lblUpdateInformation);
		
		//Get the value that user Enters
		 userID = txtUserSearch.getText();

		butSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(isValidOne()) {
				// Enter the Id here
				ul = auDAO.getAddUser(txtUserSearch.getText());
				// Get the iD data
				if (ul != null) 
				{
						String UserName = ul.getUserName();
						long phone = ul.getPhone();
						userType = ul.getUserType();
						String pass = ul.getUserPass();
						
						JOptionPane.showMessageDialog(null, "USERNAME : " +UserName +"\n" + "PHONE : " +phone +"\n" +"PASSWORD : " +pass +"\n" ," ACCOUNT INFORMATION",
								JOptionPane.INFORMATION_MESSAGE);
						
						oldUserName.setText(UserName);
						oldPhone.setText(Long.toString(phone));
						oldPass.setText(pass);
						
						btnDelLibUser.setVisible(true);
						btnDelLibUser.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								 int result = JOptionPane.showConfirmDialog(null, "Do you want to delete this user? ");
							      switch (result) {
							         case JOptionPane.YES_OPTION:

											uId = txtUserSearch.getText();
											adDAO.delUser(uId);
							        	 JOptionPane.showMessageDialog(null,"Succefully Deleted : " + uId +" this Account.." , "DELETE USER", JOptionPane.INFORMATION_MESSAGE);
							        	 btnEditLibUser.setVisible(false);

							         break;
							         case JOptionPane.NO_OPTION:
							        	 JOptionPane.showMessageDialog(null," Account.. not DELETED" , "DELETE USER", JOptionPane.INFORMATION_MESSAGE);
							        	 break;
							         case JOptionPane.CANCEL_OPTION:
							        	 JOptionPane.showMessageDialog(null,"CANCELLED.." , "EXIT ", JOptionPane.INFORMATION_MESSAGE);

							        	 break;
							         case JOptionPane.CLOSED_OPTION:
							        	 JOptionPane.showMessageDialog(null,"CANCELLED.." , "EXIT ", JOptionPane.INFORMATION_MESSAGE);

							         break;
							      }

								
							}
						});

						
						
					}
				}				
			}
		});
		
		
		btnEditLibUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
				 int resultSelect = JOptionPane.showConfirmDialog(null, "Do you want to update the Account Info.?");
			      switch (resultSelect) {
			         case JOptionPane.YES_OPTION:
			  
							if(isValidData()) {

								//Deletes the Users
								uId = txtUserSearch.getText();
								adDAO.delUser(uId);
								
								updateUserName = txtName.getText();
								updateUserPassword = txtPassord.getText();
								
								int studPhone = Integer.parseInt(txtPhone.getText().toString());
								
									
									AddUsers addUsers = new AddUsers(uId, updateUserName);
										
									
									if(addUsers != null)						
									{
										
										addUsers.setUserId(uId);
										addUsers.setUserType(userType);
										addUsers.setPhone(studPhone);
										addUsers.setUserPass(updateUserPassword);
										
											if (adDAO.addAddUser(addUsers)) 
											{
												String result = "User Id  :  " + uId + "\n" + "User Name  :  " + updateUserName + "\n" + "User Type  :  "
														+ userType + "\n" + "User Password  :  " + updateUserPassword;
												JOptionPane.showMessageDialog(null, "User profile is saved\n" + result, "USER PROFILE",
														JOptionPane.INFORMATION_MESSAGE);
											}
											else
											{
												JOptionPane.showMessageDialog(null, "User profile is not Saved\n", "USER PROFILE",
														JOptionPane.INFORMATION_MESSAGE);
											}
										}
									}	
			        	 break;
			         case JOptionPane.NO_OPTION:
			        
			        	 JOptionPane.showMessageDialog(null," Account.. not UPDATED" , "EDIT USER", JOptionPane.INFORMATION_MESSAGE);
						    			        	 
			        	 break;
			         case JOptionPane.CANCEL_OPTION:
			        	 JOptionPane.showMessageDialog(null,"CANCELLED.." , "EXIT ", JOptionPane.INFORMATION_MESSAGE);

			        	 
			        	 break;
			         case JOptionPane.CLOSED_OPTION:
			        	 JOptionPane.showMessageDialog(null,"CANCELLED.." , "EXIT ", JOptionPane.INFORMATION_MESSAGE);
			        
			        	 break;
			      }
				
			}
		});
		
	}
	public boolean isValidOne()
	{
		if(!Validator.isPresent(txtUserSearch,"User ID")) return false;
		return true;
	}
	public boolean isValidData() {
		if(!Validator.isPresent(txtName,"User Name ")) return false;
		if(!Validator.isPresent(txtPassord,"Password ")) return false;
		if(!Validator.isPresent(txtPhone,"Phone Number ")) return false;
		if (!Validator.isInteger(txtPhone, "Phone"))return false;
		if (!Validator.isLength(txtPhone, "Phone"))return false;		
		return true;
	}

}