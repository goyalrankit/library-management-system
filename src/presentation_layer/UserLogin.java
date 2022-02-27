package presentation_layer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import business_layer.*;
import data_layer.AddUserDAO;
import data_layer.DAOFactory;

@SuppressWarnings("serial")
public class UserLogin extends JFrame {

	private AddUserDAO auDAO = DAOFactory.getAddUserDAO();

	private JLabel lblExtra, lblUserId, lblUserType;
	private JLabel lblPasswd = new JLabel();
	private JTextField txtUsrID = new JTextField("ENTER USER ID");
	private JPasswordField txtPasswd = new JPasswordField("ENTER PASSWORD");
	private JButton btnLogin = new JButton("Login");
	private JCheckBox showPassword;

	private JRadioButton radio_Student, radio_Faculty;

	JPanel loginPanel;

	public UserLogin() {
		this.intialize();
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));

		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.addActionListener(new UserLoginEventHandler());
	}

	private void intialize() {
		loginPanel = new JPanel();

		lblExtra = new JLabel("");
		lblUserId = new JLabel();
		lblUserType = new JLabel();

		radio_Student = new JRadioButton("Student", true);
		radio_Faculty = new JRadioButton("Faculty");

		loginPanel.setBounds(0, 0, 600, 400);
		loginPanel.setBackground(new Color(135, 206, 235));
		getContentPane().add(loginPanel);
		loginPanel.setLayout(null);

		lblUserId.setBounds(121, 62, 60, 15);
		lblPasswd.setBounds(197, 105, 60, 15);
		lblUserType.setBounds(121, 157, 60, 15);

		txtUsrID.setBounds(270, 57, 192, 37);

		txtPasswd.setBounds(270, 105, 192, 36);

		radio_Student.setBounds(270, 174, 192, 25);
		radio_Faculty.setBounds(270, 199, 192, 25);

		btnLogin.setBounds(295, 238, 150, 40);

		loginPanel.add(lblUserId);
		loginPanel.add(txtUsrID);

		loginPanel.add(lblPasswd);
		loginPanel.add(txtPasswd);

		loginPanel.add(lblUserType);

		loginPanel.add(radio_Student);
		loginPanel.add(lblExtra);
		loginPanel.add(radio_Faculty);

		ButtonGroup bg = new ButtonGroup();
		bg.add(radio_Faculty);
		bg.add(radio_Student);

		loginPanel.add(btnLogin);

		lblUserId = new JLabel(new ImageIcon("images/user2.png"));
		loginPanel.add(lblUserId);
		lblUserId.setBounds(197, 57, 31, 37);

		lblPasswd = new JLabel(new ImageIcon("images/password.png"));
		loginPanel.add(lblPasswd);
		lblPasswd.setBounds(197, 105, 39, 36);

		lblUserType = new JLabel(new ImageIcon("images/options1.png"));
		loginPanel.add(lblUserType);
		lblUserType.setBounds(197, 180, 39, 39);

		JPanel panel = new JPanel();
		JLabel lblNewLabel1 = new JLabel("USER LOGIN");
		lblNewLabel1.setForeground(new Color(255, 204, 102));
		lblNewLabel1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));

		panel.setForeground(new Color(255, 204, 102));
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 700, 46);
		panel.add(lblNewLabel1);

		loginPanel.add(panel);
		txtPasswd.setEchoChar((char)0);

		
		

		txtUsrID.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                txtUsrID.setText("");
            }
        });
		
		txtPasswd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                txtPasswd.setText("");
				txtPasswd.setEchoChar('*');
            }
        });
		

		showPassword = new JCheckBox("SHOW PASSWORD");
		showPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		showPassword.setBackground(new Color(135, 206, 235));
		showPassword.setBounds(267, 140, 150, 23);
		loginPanel.add(showPassword);
		
		
		
		
		

		showPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (showPassword.isSelected()) {
					txtPasswd.setEchoChar((char) 0);
				} else {
					txtPasswd.setEchoChar('*');
				}

			}
		});

	}

	public boolean isValidData() {
		if (!Validator.isPresent(txtUsrID, "USER ID"))
			return false;
		if (!Validator.isPresent(txtPasswd, "USER Password"))
			return false;
		return true;
	}

	private class UserLoginEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (isValidData()) {
				String userID = txtUsrID.getText();
				String pass = String.valueOf(txtPasswd.getPassword());

				String userType = "Student";
				if (radio_Faculty.isSelected()) {
					userType = "Faculty";
				}

				AddUsers ul = auDAO.getAddUser(txtUsrID.getText());

				if (ul != null) {
					if (userID.equals(ul.getUserId()) && pass.equals(ul.getUserPass())
							&& userType.equals(ul.getUserType())) {

						MainFrameUsers frame = new MainFrameUsers();
						frame.setTitle("User Module");
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						frame.setSize(1000, 600);
					} else {
						JOptionPane.showMessageDialog(null, "Username or Password is incorrect", " Invalid User",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				if (ul == null) {
					JOptionPane.showMessageDialog(null, "Username & Password is incorrect", "Invalid User",
							JOptionPane.ERROR_MESSAGE);

				}
			}

		}
	}
}
