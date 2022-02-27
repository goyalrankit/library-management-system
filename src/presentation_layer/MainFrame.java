package presentation_layer;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JMenuBar mBar;
	private JMenu mManageBook, mManageUsers;
	private JMenuItem mAdd, mSearch, mAddStudent, mIssueBook, mReturnBook, mAddLibrarian, mEditLib, mEditUser;

	private JDesktopPane desktop;

	public MainFrame() {
		this.intialize();
	}

	private void intialize() {
		mBar = new JMenuBar();
		mBar.setBackground(Color.BLACK);
		mBar.add(Box.createRigidArea(new Dimension(0, 50)));
		desktop = new JDesktopPane();
		desktop.setBackground(new Color(135, 206, 235));
		this.setContentPane(desktop);

		mAdd = new JMenuItem("Add or Update Book");
		mAdd.setMnemonic(KeyEvent.VK_A);
		mAdd.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
		mAdd.addActionListener(new AddEventHandler());

		mSearch = new JMenuItem("Search Book");
		mSearch.setMnemonic(KeyEvent.VK_S);
		mSearch.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		mSearch.addActionListener(new SearchEventHandler());

		mReturnBook = new JMenuItem("Return Book");
		mReturnBook.setMnemonic(KeyEvent.VK_T);
		mReturnBook.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mReturnBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));
		mReturnBook.addActionListener(new ReturnBookEventHandler());

		mIssueBook = new JMenuItem("Issue Book");
		mIssueBook.setMnemonic(KeyEvent.VK_I);
		mIssueBook.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mIssueBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
		mIssueBook.addActionListener(new IssueBookEventHandler());

		mAddStudent = new JMenuItem("Add Users");
		mAddStudent.setMnemonic(KeyEvent.VK_D);
		mAddStudent.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mAddStudent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
		mAddStudent.addActionListener(new AddUsersEventHandler());

		mAddLibrarian = new JMenuItem("Add Librarian");
		mAddLibrarian.setMnemonic(KeyEvent.VK_L);
		mAddLibrarian.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mAddLibrarian.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
		mAddLibrarian.addActionListener(new AddLibrarianEventHandler());

		mEditLib = new JMenuItem("Edit Librarian");
		mEditLib.setMnemonic(KeyEvent.VK_E);
		mEditLib.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mEditLib.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
		mEditLib.addActionListener(new EditLibrarianEventHandler());

		mEditUser = new JMenuItem("Edit User");
		mEditUser.setMnemonic(KeyEvent.VK_R);
		mEditUser.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mEditUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
		mEditUser.addActionListener(new EditUserEventHandler());

		mManageBook = new JMenu("Manage Books");
		mManageBook.setForeground(new Color(255, 215, 0));
		mManageBook.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mManageBook.setMnemonic(KeyEvent.VK_B);

		mManageUsers = new JMenu("Manage Users");
		mManageUsers.setForeground(new Color(255, 215, 0));
		mManageUsers.setMnemonic(KeyEvent.VK_U);
		mManageUsers.setFont(new Font("Segoe UI", Font.PLAIN, 22));

		mManageBook.add(mAdd);
		mManageBook.add(mSearch);

		mManageBook.add(mIssueBook);
		mManageBook.add(mReturnBook);

		mManageUsers.add(mAddStudent);
		mManageUsers.add(mAddLibrarian);
		mManageUsers.add(mEditLib);
		mManageUsers.add(mEditUser);

		mBar.add(mManageBook);
		mBar.add(mManageUsers);

		this.setJMenuBar(mBar);
	}

	private class EditLibrarianEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new EditLibrarianInternalFrame(new EditLibrarian());
			pInternalFrame.setVisible(true);
			MainFrame.this.desktop.add(pInternalFrame);

		}
	}

	private class AddEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JInternalFrame pInternalFrame = new AddBookInternalFrame(new AddBook());
			pInternalFrame.setVisible(true);
			MainFrame.this.desktop.add(pInternalFrame);

		}
	}

	private class SearchEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new SearchBookInternalFrame(new SearchBook());
			pInternalFrame.setVisible(true);
			MainFrame.this.desktop.add(pInternalFrame);

		}
	}

	private class ReturnBookEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new ReturnPageInternalFrame(new ReturnPage());
			pInternalFrame.setVisible(true);
			MainFrame.this.desktop.add(pInternalFrame);

		}
	}

	private class IssueBookEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new IssuePageInternalFrame(new IssuePage());
			pInternalFrame.setVisible(true);
			MainFrame.this.desktop.add(pInternalFrame);

		}
	}

	private class AddUsersEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = (JInternalFrame) new AddUserInternalFrame(new AddUser());
			pInternalFrame.setVisible(true);
			MainFrame.this.desktop.add(pInternalFrame);

		}
	}

	private class AddLibrarianEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new AddLibrarianInternalFrame(new AddLibrarian());
			pInternalFrame.setVisible(true);
			MainFrame.this.desktop.add(pInternalFrame);

		}
	}

	private class EditUserEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new EditUserInternalFrame(new EditUserLibrarian());
			pInternalFrame.setVisible(true);

			MainFrame.this.desktop.add(pInternalFrame);

		}
	}

}
