package presentation_layer;

import data_layer.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

import javax.swing.*;

import business_layer.*;

@SuppressWarnings("serial")
public class AddLibrarian extends JPanel {

	private JLabel lblLibName, lblLibPassword;
	private JTextField txtLibName, txtLibPassword;
	private JButton btnAddLib;
	private AddLibrarianDAO pDao = DAOFactory.getLibrarianDAO();
	String librarianID;

	private void intialize() {

		lblLibName = new JLabel(new ImageIcon("images/user2.png"));
		add(lblLibName);
		lblLibName.setBounds(185, 89, 39, 37);

		lblLibPassword = new JLabel(new ImageIcon("images/password.png"));
		add(lblLibPassword);
		lblLibPassword.setBounds(185, 152, 39, 36);

		txtLibName = new JTextField("");
		txtLibName.setBounds(270, 89, 225, 40);

		txtLibPassword = new JPasswordField("");
		txtLibPassword.setBounds(270, 148, 225, 40);
		((JPasswordField) txtLibPassword).setEchoChar((char) 0);

		btnAddLib = new JButton("Add Librarian");
		btnAddLib.setBounds(246, 213, 225, 37);
		btnAddLib.setBackground(Color.black);
		btnAddLib.setForeground(Color.WHITE);

		btnAddLib.setMnemonic(KeyEvent.VK_A);
		setLayout(null);
		this.add(lblLibName);
		this.add(txtLibName);
		this.add(lblLibPassword);
		this.add(txtLibPassword);
		this.add(btnAddLib);

		JPanel panel = new JPanel();
		JLabel lblNewLabel1 = new JLabel("LIBRARIAN REGISTRATION");
		lblNewLabel1.setForeground(new Color(255, 204, 102));
		lblNewLabel1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));

		panel.setForeground(new Color(255, 204, 102));
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 700, 46);
		panel.add(lblNewLabel1);

		add(panel);
		
		JLabel lblName = new JLabel("Librarian Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(65, 89, 144, 29);
		add(lblName);
		
		JLabel lblPass = new JLabel("Librarian Password");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPass.setBounds(65, 152, 119, 37);
		add(lblPass);
	
		txtLibPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				((JPasswordField) txtLibPassword).setEchoChar('*');

			}
		});
	}

	public AddLibrarian() {
		setBackground(new Color(135, 206, 235));

		this.intialize();
		btnAddLib.addActionListener(new AddLibrarianHandler());

	}

	private class AddLibrarianHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String ts = String.valueOf(System.currentTimeMillis()); /// 14 dig
			String rand = UUID.randomUUID().toString(); // alpha 16
			String new_word = ts.substring(ts.length() - 3); //
			String new_word1 = rand.substring(rand.length() - 3);
			librarianID = new_word + new_word1;

			librarianID = "L" + String.valueOf(librarianID);
			if (isValidData()) {

				String name = txtLibName.getText();
				String password = txtLibPassword.getText();

				Librarian c = new Librarian(librarianID);
				c.setID(librarianID);
				c.setName(name);
				c.setPassword(password);

				pDao.addLibrarian(c);
				String result = "\nLibrarian ID = " + c.getID() + "\nLibrarian Name = " + c.getName()
						+ "\nLibrarian Password = " + c.getPassword();
				JOptionPane.showMessageDialog(null, "Librarian  is Added Successfully" + result, "Librarian Added",
						JOptionPane.INFORMATION_MESSAGE);
				txtLibName.setText("");
				txtLibPassword.setText("");
			}
		}
	}

	public boolean isValidData() {
		if (!Validator.isPresent(txtLibName, "Librarian Name"))
			return false;
		if (!Validator.isPresent(txtLibPassword, "Librarian Password"))
			return false;
		return true;

	}
}