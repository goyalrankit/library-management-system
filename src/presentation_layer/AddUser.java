package presentation_layer;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

import javax.swing.*;

import business_layer.*;
import data_layer.*;

public class AddUser extends JPanel {

	private AddUserDAO adDAO = DAOFactory.getAddUserDAO();
	private JLabel lblName, lblUserType, lblPhone;
	private JTextField txtName, txtPhone;
	private JButton btnAdd;
	private JRadioButton radio_Student, radio_Faculty;

	String testID, testN, userPass, userID;

	public AddUser() 
	{
		this.sampleCode();
	}

	private void sampleCode() {
		setBackground(new Color(135, 206, 235));
		setLayout(null);


		txtName = new JTextField("ENTER NAME");
		txtName.setBounds(230, 76, 225, 37);
		add(txtName);
		txtName.setColumns(10);



		radio_Student = new JRadioButton("Student", true);
		radio_Faculty = new JRadioButton("Faculty");

		this.add(radio_Student);
		this.add(radio_Faculty);

		radio_Faculty.setBounds(230, 124, 225, 37);
		radio_Student.setBounds(230, 150, 225, 37);

		ButtonGroup bg = new ButtonGroup();
		bg.add(radio_Faculty);
		bg.add(radio_Student);

		txtPhone = new JTextField("ENTER PHONE NUMBER");
		txtPhone.setBounds(230, 194, 225, 37);
		add(txtPhone);
		txtPhone.setColumns(10);

		btnAdd = new JButton("Add User");
		btnAdd.setBounds(230, 252, 225, 37);
		btnAdd.setBackground(Color.black);
		btnAdd.setForeground(Color.WHITE);
		add(btnAdd);
		btnAdd.addActionListener(new AddStudentButtonHandler());
		
		

		

		lblName =    new JLabel(new ImageIcon("images/user2.png"));
		lblName.setToolTipText("Enter NAME");
		add(lblName);
		lblName.setBounds(150, 76, 39, 37);

		lblPhone =    new JLabel(new ImageIcon("images/mobile.png"));
		lblPhone.setToolTipText("Enter PHONE");
		add(lblPhone);
		lblPhone.setBounds(150, 194, 39, 36);
		
		
		lblUserType = new JLabel(new ImageIcon("images/options1.png"));
		add(lblUserType);
		lblUserType.setBounds(150, 140, 39, 37);


		
		
		
		
		JPanel panel = new JPanel();
		JLabel lblNewLabel1 = new JLabel("USER REGISTRATION");
		lblNewLabel1.setForeground(new Color(255, 204,102 ));
		lblNewLabel1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));
		
		panel.setForeground(new Color(255, 204, 102));		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 600, 46);
		panel.add(lblNewLabel1);

		add(panel);
		
		
		txtName.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                txtName.setText("");
		     }
        });
		
		
		txtPhone.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                txtPhone.setText("");
		     }
        });


	}

	private class AddStudentButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String ts = String.valueOf(System.currentTimeMillis()); ///14 dig
			String rand = UUID.randomUUID().toString(); // alpha 16
			String new_word = ts.substring(ts.length() - 3); // 
			String new_word1 = rand.substring(rand.length() - 3);

			String new_pass_gen = ts.substring(ts.length() - 1);
			String new_pass_gen1 = rand.substring(rand.length() - 2);

			userID = new_word + new_word1;

			String password = "U@" + new_pass_gen1 + "#" + new_pass_gen;
			int maxBooks = 0;

	if (isValidData()) 
		{
			String userName = txtName.getText();
			String userType = "Student";

			if (radio_Faculty.isSelected()) {
				userType = "Faculty";
				userID = "F" + userID;
				password = "F@" + new_pass_gen1 + "#" + new_pass_gen;

			}
			if (radio_Student.isSelected()) {
				userType = "Student";
				userID = "S" + userID;
				password = "S@" + new_pass_gen1 + "#" + new_pass_gen;
			}

		    try {
					long studPhone =Long.parseLong(txtPhone.getText());
	
				AddUsers addUsers = new AddUsers(userID, userName);

				addUsers.setUserType(userType);
				addUsers.setPhone(studPhone);
				addUsers.setUserPass(password);
				addUsers.setIssuedBooks(maxBooks);
				if (adDAO.addAddUser(addUsers)) 
				{
					String result = "User Id  :  " + userID + "\n" + "User Name  :  " + userName + "\n" + "User Type  :  "
							+ userType + "\n" + "User Password  :  " + password;
					JOptionPane.showMessageDialog(null, "User profile is saved\n" + result, "USER PROFILE",
							JOptionPane.INFORMATION_MESSAGE);
				}
			txtName.setText("");
			txtPhone.setText("");
			}
		 
            catch(Exception e1)
		    {System.out.println(e1);
            	JOptionPane.showMessageDialog(null, "Invalid Data","Add User",JOptionPane.INFORMATION_MESSAGE);}
			}

		}
	}

	public boolean isValidData() {

		if (!Validator.isString(txtName, "User Name"))
			return false;
		if (!Validator.isPresent(txtName, "User Name"))
			return false;
		if (!Validator.isPresent(txtPhone, "Phone Number"))
			return false;
	
		if (!Validator.isLength(txtPhone, "Phone"))
			return false;

		return true;
	}}
