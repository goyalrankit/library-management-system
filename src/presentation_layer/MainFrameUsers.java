package presentation_layer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

import data_layer.*;
@SuppressWarnings("serial")
public class MainFrameUsers extends JFrame {

	private JMenuBar mBar;
	private JMenu mManageBook,mManageAccount;
	private JMenuItem mSearch, mUpdateAccount;
	Font font = new Font("Serif", Font.PLAIN, 18);
	
	BookDB db = null;

	private JDesktopPane desktop;

	public MainFrameUsers() {
		this.intialize();
		this.setTitle("User Module");
		this.setSize(400, 300);

	}

	private void intialize() {
		mBar = new JMenuBar();
		mBar.setBackground(Color.BLACK);
		mBar.add(Box.createRigidArea(new Dimension(0, 50)));
		desktop = new JDesktopPane();
		desktop.setBackground(new Color(135,206,235));
		this.setContentPane(desktop);

		mManageBook = new JMenu("MANAGE BOOKS");
		mManageBook.setForeground(new Color(255, 204,102));
		mManageBook.setMnemonic(KeyEvent.VK_M);
		mManageBook.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));
		
		mManageAccount = new JMenu("MANAGE ACCOUNT");
		mManageAccount.setForeground(new Color(255, 204,102));
		mManageAccount.setFont(new Font("Clarendon Lt BT", Font.BOLD, 30));
		mManageAccount.setMnemonic(KeyEvent.VK_A);

		mSearch = new JMenuItem("Search Book");
		mSearch.setMnemonic(KeyEvent.VK_S);
		mSearch.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		mSearch.addActionListener(new SearchEventHandler());
		
		
		mUpdateAccount = new JMenuItem("EDIT ACCOUNT");
		mUpdateAccount.setMnemonic(KeyEvent.VK_U);
		mUpdateAccount.setFont(new Font("Clarendon Lt BT", Font.BOLD, 14));
		mUpdateAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK));
		mUpdateAccount.addActionListener(new UpdateEventHandler());


		mManageBook.setFont(font);
		mManageBook.add(mSearch);
		
		
		mManageAccount.setFont(font);
		mManageAccount.add(mUpdateAccount);

		mBar.add(mManageBook);
		mBar.add(mManageAccount);
		this.setJMenuBar(mBar);
	}



	private class SearchEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new SearchBookInternalFrame(new SearchBook());
			pInternalFrame.setVisible(true);
			MainFrameUsers.this.desktop.add(pInternalFrame);

		}
	}

	
	
	
	private class UpdateEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame pInternalFrame = new EditUserInternalFrame(new EditUser());
			pInternalFrame.setVisible(true);
			MainFrameUsers.this.desktop.add(pInternalFrame);

		}
	}


}
