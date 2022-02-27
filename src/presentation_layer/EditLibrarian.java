package presentation_layer;

import data_layer.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import business_layer.*;

@SuppressWarnings("serial")
public class EditLibrarian extends JPanel {

	private JLabel lblLibID, lblLibPass, lblLibName;
	private JTextField txtLibPassword, txtLibName;
	private JButton btnEditLib, butSearch, btnDelLibrarian;
	private AddLibrarianDAO pDao = DAOFactory.getLibrarianDAO();
	String libID, globalLibId;

	private JTextField txtLibIdSearch;
	private JTextField oldLibName, oldPass;
	private JPanel panel_1, panel_2;
	private JLabel lblAccountInformation, lblUpdateInformation;

	public EditLibrarian() {
		setBackground(new Color(135, 206, 235));
		this.intialize();
		btnEditLib.addActionListener(new EditLibrarianHandler());
	}

	private void intialize() {

		txtLibName = new JTextField();
		txtLibName.setBounds(504, 177, 129, 20);

		txtLibPassword = new JPasswordField();
		txtLibPassword.setBounds(504, 236, 129, 20);
		;

		btnEditLib = new JButton("Edit Librarian");
		btnEditLib.setBounds(420, 280, 180, 37);
		btnEditLib.setBackground(Color.black);
		btnEditLib.setForeground(Color.WHITE);
		btnEditLib.setMnemonic(KeyEvent.VK_E);

		btnDelLibrarian = new JButton("Delete Librarian");
		btnDelLibrarian.setBounds(130, 280, 180, 37);
		btnDelLibrarian.setBackground(Color.RED);
		btnDelLibrarian.setForeground(Color.WHITE);
		btnDelLibrarian.setMnemonic(KeyEvent.VK_D);

		setLayout(null);

		this.add(txtLibName);
		this.add(txtLibPassword);
		this.add(btnEditLib);
		this.add(btnDelLibrarian);
		btnDelLibrarian.setVisible(false);

		JPanel panel = new JPanel();
		JLabel lblNewLabel1 = new JLabel("Edit Librarian");
		lblNewLabel1.setForeground(new Color(255, 204, 102));
		lblNewLabel1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));

		panel.setForeground(new Color(255, 204, 102));
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 700, 46);
		panel.add(lblNewLabel1);

		add(panel);
		lblLibID = new JLabel("ENTER LIBRARIAN ID");
		lblLibID.setBounds(22, 60, 129, 16);
		lblNewLabel1.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		add(lblLibID);

		txtLibIdSearch = new JTextField("LIBRARIAN ID here");
		txtLibIdSearch.setBounds(184, 57, 189, 22);
		txtLibIdSearch.setColumns(40);
		add(txtLibIdSearch);

		txtLibIdSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtLibIdSearch.setText("");
			}
		});

		butSearch = new JButton("VIEW   INFO");
		butSearch.setBounds(216, 90, 113, 25);
		setLayout(null);
		add(butSearch);

		lblLibName = new JLabel("NEW  LIBRARIAN");
		lblLibName.setBounds(359, 180, 113, 14);
		add(lblLibName);

		lblLibPass = new JLabel("NEW  PASSWORD ");
		lblLibPass.setBounds(359, 239, 113, 14);
		add(lblLibPass);

		JLabel lblNewLabel = new JLabel("LIBRARIAN");
		lblNewLabel.setBounds(47, 180, 90, 14);
		add(lblNewLabel);

		oldLibName = new JTextField("ENTER NAME");
		oldLibName.setBounds(155, 177, 119, 20);
		add(oldLibName);
		oldLibName.setColumns(10);

		oldLibName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				oldLibName.setText("");
			}
		});

		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setBounds(47, 239, 82, 14);
		add(lblNewLabel_2);

		oldPass = new JTextField("ENTER PASSWORD");
		oldPass.setBounds(155, 236, 119, 20);
		add(oldPass);
		oldPass.setColumns(10);

		oldPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				oldPass.setText("");
			}
		});

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 204, 102));
		panel_1.setBounds(0, 131, 329, 245);
		add(panel_1);

		lblAccountInformation = new JLabel("ACCOUNT INFORMATION");
		lblAccountInformation.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(lblAccountInformation);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 204, 102));
		panel_2.setBounds(333, 131, 364, 245);
		add(panel_2);

		lblUpdateInformation = new JLabel("UPDATE INFORMATION");
		lblUpdateInformation.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_2.add(lblUpdateInformation);

		butSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isValidOne()) {
					libID = txtLibIdSearch.getText();

					Librarian tes = pDao.getLibrarian(libID);

					if (tes != null) {
						String name = tes.getName();
						String password = tes.getPassword();

						oldLibName.setText(name);
						oldPass.setText(password);

						btnDelLibrarian.setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "Librarian does not exist", "Librarian Updation",
								JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		btnDelLibrarian.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "Do you want to delete this user? ");
				switch (result) {
				case JOptionPane.YES_OPTION:

					pDao.deleteLibrarian(txtLibIdSearch.getText());
					JOptionPane.showMessageDialog(null, "Successfully Deleted", "DELETE LIBRARIAN",
							JOptionPane.INFORMATION_MESSAGE);

					txtLibIdSearch.setText("");
					oldLibName.setText("");
					oldPass.setText("");

					break;
				case JOptionPane.NO_OPTION:
					JOptionPane.showMessageDialog(null, " Account.. not DELETED", "DELETE USER",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				case JOptionPane.CANCEL_OPTION:
					JOptionPane.showMessageDialog(null, "CANCELLED..", "EXIT ", JOptionPane.INFORMATION_MESSAGE);

					break;
				case JOptionPane.CLOSED_OPTION:
					JOptionPane.showMessageDialog(null, "CANCELLED..", "EXIT ", JOptionPane.INFORMATION_MESSAGE);

					break;
				}
			}
		});

	}

	private class EditLibrarianHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			pDao.getLibrarian(txtLibIdSearch.getText());

			pDao.deleteLibrarian(txtLibIdSearch.getText());
			if (isValidData()) {

				String name1 = txtLibName.getText();
				String password = txtLibPassword.getText();

				Librarian c = new Librarian(libID);
				c.setName(name1);
				c.setPassword(password);

				pDao.addLibrarian(c);
				String result = "\nLibrarian ID = " + globalLibId + "\nLibrarian Name = " + c.getName()
						+ "\nLibrarian Password = " + c.getPassword();
				JOptionPane.showMessageDialog(null, "Librarian  is Edited Successfully" + result, "Librarian Added",
						JOptionPane.INFORMATION_MESSAGE);
				txtLibPassword.setText("");
				txtLibName.setText("");
				txtLibIdSearch.setText("");
				oldLibName.setText("");
				oldPass.setText("");
			}
		}
	}
	public boolean isValidOne()
	{
		if (!Validator.isPresent(txtLibIdSearch, "Librarian ID"))
			return false;
		return true;
	}

	public boolean isValidData() {
		
		
		if (!Validator.isPresent(txtLibName, "Librarian ID"))
			return false;
		if (!Validator.isPresent(txtLibPassword, "Librarian Password"))
			return false;

		return true;

	}
}