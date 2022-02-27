package presentation_layer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import business_layer.*;
import data_layer.BookDB;

@SuppressWarnings("serial")
public class AddBook<CProgram> extends JPanel {

	private JLabel lblBookName, lblPbName, lblGenre, lblPrice, lblQuantity;
	private JTextField txtBookIsbn, txtBookName, txtPbName;
	private JButton butAdd, butUpdate, btnEdit, btnDelete;
	private JSpinner spinnerPrice, spinnerQty;
	private String[] genre = { "Select Genre", "Fantasy", "History", "Math", "Biography", "Science", "Religion",
			"Adventure", "Romance", "Contemporary", "Dystopian" };
	private JComboBox comboBox = new JComboBox(genre);
	private SpinnerNumberModel m_priceSpinnerModel, m_qtySpinnerModel;

	double currentPrice = 80.0, minPrice = 50.00, maxPrice = 500.00, stepPrice = 10.00;
	int currentQty = 1, minQty = 1, maxQty = 20, stepQty = 1;

	private BookDB db = null;

	public AddBook() {
		this.intialize();
		try {
			db = new BookDB();
		} catch (ClassNotFoundException | SQLException sqle) {
			showException(sqle);
			System.exit(1);
		}
	}

	private void intialize() {

		JLabel lblBookIsbn = new JLabel("Book ISBN :");

		lblBookIsbn.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBookIsbn.setBounds(142, 74, 88, 28);
		add(lblBookIsbn);

		txtBookIsbn = new JTextField();
		txtBookIsbn.setBounds(327, 68, 213, 28);
		add(txtBookIsbn);
		txtBookIsbn.setColumns(10);

		lblBookName = new JLabel("Book Name :");
		lblBookName.setBounds(142, 113, 122, 28);
		lblBookName.setFont(new Font("Dialog", Font.BOLD, 14));

		txtBookName = new JTextField();
		txtBookName.setBounds(327, 109, 213, 28);

		lblPbName = new JLabel("Publisher Name :");
		lblPbName.setBounds(142, 152, 143, 28);
		lblPbName.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));

		txtPbName = new JTextField();
		txtPbName.setBounds(327, 150, 213, 28);

		lblGenre = new JLabel("Genre :");
		lblGenre.setBounds(142, 191, 133, 28);
		lblGenre.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));

		lblPrice = new JLabel("Price :");
		lblPrice.setBounds(142, 230, 122, 28);
		lblPrice.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));

		lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(142, 269, 122, 28);
		lblQuantity.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));

		butAdd = new JButton("Add");
		butAdd.setBounds(154, 347, 97, 33);
		butAdd.setBackground(Color.black);
		butAdd.setForeground(Color.WHITE);

		butUpdate = new JButton("Update");
		butUpdate.setBackground(Color.RED);
		butUpdate.setForeground(Color.WHITE);
		butUpdate.setBounds(280, 347, 97, 33);

		btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.BLACK);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBounds(406, 347, 97, 33);

		btnEdit = new JButton("EDIT");
		btnEdit.setBackground(Color.BLACK);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setBounds(560, 71, 89, 25);

		setLayout(null);

		this.add(lblBookName);
		this.add(txtBookName);

		this.add(lblPbName);
		this.add(txtPbName);

		this.add(lblGenre);
		this.add(comboBox);
		comboBox.setBounds(327, 191, 213, 28);
		this.add(lblPrice);

		this.add(lblQuantity);

		this.add(butAdd);
		this.add(butUpdate);

		add(btnDelete);
		add(btnEdit);

		this.setBackground(new Color(135, 206, 235));

		m_priceSpinnerModel = new SpinnerNumberModel(currentPrice, minPrice, maxPrice, stepPrice);
		spinnerPrice = new JSpinner(m_priceSpinnerModel);
		spinnerPrice.setBounds(327, 234, 213, 28);
		add(spinnerPrice);

		m_qtySpinnerModel = new SpinnerNumberModel(currentQty, minQty, maxQty, stepQty);
		spinnerQty = new JSpinner(m_qtySpinnerModel);
		spinnerQty.setBounds(327, 277, 213, 28);
		add(spinnerQty);

		JPanel panel = new JPanel();
		JLabel lblHeading = new JLabel("ADD OR UPDATE BOOK");
		lblHeading.setForeground(new Color(255, 204, 102));
		lblHeading.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblHeading.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 710, 46);
		panel.add(lblHeading);
		add(panel);

		butAdd.addActionListener(new AddButtonHandler());
		butUpdate.addActionListener(new UpdateButtonHandler());
		butUpdate.setVisible(false);
		btnDelete.setVisible(false);
		btnDelete.addActionListener(new DeleteButtonHandler());
		btnEdit.addActionListener(new EditButtonHandler());
	}

	private class AddButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (isValidData()) {
					int bookIsbn = Integer.parseInt(txtBookIsbn.getText());
					String bookName = txtBookName.getText();
					String pbName = txtPbName.getText();
					String genre = comboBox.getSelectedItem().toString();
					int quantity = Integer.parseInt(spinnerQty.getValue().toString());
					double price = Double.parseDouble(spinnerPrice.getValue().toString());
					Books b = new Books(bookIsbn, bookName, pbName, genre, price, quantity);
					db.addBook(b);
					String result = "Book Isbn = " + bookIsbn + "\nBook Name = " + bookName + "\nPublisher Name = "
							+ pbName + "\nGenre = " + genre + "\nPrice = " + price + "\nQuantity = " + quantity;
					JOptionPane.showMessageDialog(null, result, "Book is added", JOptionPane.INFORMATION_MESSAGE);

					txtBookIsbn.setText("");
					txtBookName.setText("");
					txtPbName.setText("");
					spinnerPrice.setValue(currentPrice);
					spinnerQty.setValue(currentQty);
				}
			} catch (NumberFormatException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "Book cannot be added");
			}
		}
	}

	private class EditButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				Books b;
				b = db.getBook(Integer.parseInt(txtBookIsbn.getText()));
				if (b != null) {
					showProgram(b);
					butUpdate.setVisible(true);
					btnDelete.setVisible(true);
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Book is not present");
			}
		}
	}

	private class DeleteButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			try {
				int id = Integer.parseInt(txtBookIsbn.getText());
				if (isValidData()) {
					db.deleteBook(id);
					JOptionPane.showMessageDialog(null, "Book is deleted");
					txtBookIsbn.setText("");
					txtBookName.setText("");
					txtPbName.setText("");
					comboBox.setSelectedIndex(0);
					spinnerPrice.setValue(currentPrice);
					spinnerQty.setValue(currentQty);
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Book is not present");
			}
		}
	}

	private class UpdateButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Books b = new Books(Integer.parseInt(txtBookIsbn.getText()), txtBookName.getText(), txtPbName.getText(),
					comboBox.getSelectedItem().toString(), Double.parseDouble(spinnerPrice.getValue().toString()),
					Integer.parseInt(spinnerQty.getValue().toString()));
			try {
				db.updateBook(b);
				String result = "Book Isbn = " + txtBookIsbn.getText() + "\nBook Name = " + txtBookName.getText()
						+ "\nPublisher Name = " + txtPbName.getText() + "\nGenre = "
						+ comboBox.getSelectedItem().toString() + "\nPrice ="
						+ Double.parseDouble(spinnerPrice.getValue().toString()) + "\nQuantity = "
						+ Integer.parseInt(spinnerQty.getValue().toString());

				JOptionPane.showMessageDialog(null, "Book Information is Updated\n" + result, "Book Updation",
						JOptionPane.INFORMATION_MESSAGE);
				txtBookIsbn.setText("");
				txtBookName.setText("");
				txtPbName.setText("");
				comboBox.setSelectedIndex(0);
				spinnerPrice.setValue(currentPrice);
				spinnerQty.setValue(currentQty);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Book is not Updated..");
			}
		}
	}

	private void showException(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void showProgram(Books b) {
		txtBookIsbn.setText("" + b.getBookIsbn());
		txtBookName.setText(b.getBookName());
		txtPbName.setText("" + b.getPublisherName());
		comboBox.setSelectedItem(b.getGenre());
		spinnerPrice.setValue(b.getPrice());
		spinnerQty.setValue(b.getQuantity());
	}

	public boolean isValidData() {
		if (!Validator.isPresent(txtBookIsbn, "Book Isbn"))
			return false;
		if (!Validator.isPresent(txtBookName, "Book Name"))
			return false;
		if (!Validator.isPresent(txtPbName, "Publisher Name"))
			return false;
		if (!Validator.isGenre(comboBox, "Genre"))
			return false;

		return true;
	}
}