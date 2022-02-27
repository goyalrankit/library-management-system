package presentation_layer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import business_layer.*;
import data_layer.*;

@SuppressWarnings("serial")
public class LibrarianLogin extends JFrame {


	private AddLibrarianDAO pDao = DAOFactory.getLibrarianDAO();

	private JLabel lblLibId,lblPasswd;
	private JTextField txtLibID;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	JCheckBox showPassword;
	JPanel loginPanel;
	private final JPanel panel = new JPanel();

	
	
	public LibrarianLogin() {
			
		setBackground(new Color(51, 153, 102));
		getContentPane().setBackground(new Color(255, 204, 102));
		this.intialize();
		
		btnLogin.addActionListener(new LoginButtonHandler());

	}
	public boolean isValidData() {
		if(!Validator.isPresent(txtLibID,"Librarian ID")) return false;
		if(!Validator.isPresent(txtPassword,"Librarian Password")) return false;
		return true;
	}
	
	private void intialize() {
		txtLibID = new JTextField("");
		
		txtPassword = new JPasswordField("");
		txtPassword.setEchoChar((char)0);
		btnLogin = new JButton("Login");
		
		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 600, 400);
		loginPanel.setBackground(new Color(135, 206, 235));
		getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(null);
		
		
		txtLibID.setBounds(283, 67,199, 37);
		txtPassword.setBounds(283, 117,199, 37);
			
		
		btnLogin.setBounds(277, 205,150,40);
		
		lblLibId =   new JLabel(new ImageIcon("images/user2.png"));
		lblLibId.setBounds(222,67,30,37);
		lblPasswd =   new JLabel(new ImageIcon("images/password.png"));
		lblPasswd.setBounds(212,117,40,37);
		
		loginPanel.add(lblLibId);
		loginPanel.add(txtLibID);
		loginPanel.add(lblPasswd);
		loginPanel.add(txtPassword);
		loginPanel.add(btnLogin);
		
		
		JLabel lblNewLabel = new JLabel("LIBRARIAN LOGIN");
		lblNewLabel.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(255, 204, 102));
		panel.add(lblNewLabel);
		
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 669, 44);
		loginPanel.add(panel);
		
		
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(Color.BLACK);
		
		showPassword = new JCheckBox("SHOW PASSWORD");
		showPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		showPassword.setBackground(new Color(135, 206, 235));
		showPassword.setBounds(277, 163, 150, 23);
		loginPanel.add(showPassword);
		
		JLabel lblLibName = new JLabel("Librarian Name");
		lblLibName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLibName.setBounds(88, 67, 122, 31);
		loginPanel.add(lblLibName);
		
		JLabel lblPassword = new JLabel("Librarian Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(72, 126, 138, 16);
		loginPanel.add(lblPassword);
		

		
		txtLibID.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                txtLibID.setText("");
            }
        });
		
		txtPassword.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                txtPassword.setText("");
				txtPassword.setEchoChar('*');
            }
        });
		
		
		
		// SHOW OR  HIDE THE PASSWORD 
		showPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(showPassword.isSelected())
				{
					txtPassword.setEchoChar((char)0);
				}
				else
				{
					txtPassword.setEchoChar('*');
				}				
			}
		});
	}


	private class LoginButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
            
			if(isValidData()) {
			String libId = txtLibID.getText();
			String password = String.valueOf(txtPassword.getPassword());
			
			
			Librarian tes = pDao.getLibrarian(txtLibID.getText());
			
			if(tes!=null)
			{
					if(libId.equals(tes.getID()) && password.equals(tes.getPassword()))
					{
						MainFrame frame = new MainFrame();
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
						frame.setSize(1000,600);				
				
					}
					else
					{
						JOptionPane.showMessageDialog(null,  "Librarian Name or Password is incorrect","Invalid Librarian",
						JOptionPane.ERROR_MESSAGE);

					}
			}
			
			if(tes==null)
				{
					JOptionPane.showMessageDialog(null,  "Librarian Name and Password is incorrect","Invalid Librarian",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
	}
}