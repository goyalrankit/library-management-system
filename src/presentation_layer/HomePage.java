package presentation_layer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class HomePage extends JFrame {

	private JButton btnLibrarian = new JButton("LIBRARIAN LOGIN");
	private JButton btnUsers = new JButton("USER LOGIN");
	private JPanel homePanel;

	 
	public HomePage() {
		getContentPane().setBackground(new Color(0, 0, 0));

		setBackground(new Color(128, 0, 128));
		this.intialize();

		btnLibrarian.setBounds(89, 120, 341, 47);
		btnLibrarian.setBackground(Color.BLACK);
		btnLibrarian.setForeground(Color.WHITE);

		btnUsers.setBounds(89, 212, 341, 47);
		btnUsers.setBackground(Color.BLACK);
		btnUsers.setForeground(Color.WHITE);

		homePanel.add(btnUsers);

		JLabel imgLabel;
		imgLabel = new JLabel(new ImageIcon("images/home1.jpg"));
		imgLabel.setBackground(new Color(192, 192, 192));
		imgLabel.setBounds(10, 95, 285, 400);
		getContentPane().add(imgLabel);

		Panel panel = new Panel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 821, 92);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblNewLabel.setBounds(new Rectangle(177, 13, 404, 51));
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel.setAutoscrolls(true);
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(0, 0, 0));

		panel.add(lblNewLabel);
		btnUsers.addActionListener(new StudentEventHandler());
		btnLibrarian.addActionListener(new LibrarianEventHandler());
		
			
	}

	private void intialize() {
		getContentPane().setLayout(null);

		homePanel = new JPanel();
		homePanel.setBackground(new Color(255, 255, 224));
		homePanel.setBounds(296, 91, 525, 493);
		homePanel.setLayout(null);
		getContentPane().add(homePanel);
		homePanel.add(btnLibrarian);

		btnLibrarian.setMnemonic('L');
		btnLibrarian.setFont(new Font("Serif", Font.PLAIN, 30));
		btnLibrarian.setToolTipText("Librarian Login");

		btnUsers.setMnemonic('S');
		btnUsers.setFont(new Font("Serif", Font.PLAIN, 30));
		btnUsers.setToolTipText("Student Login");
	}

	private class LibrarianEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			LibrarianLogin frame = new LibrarianLogin();
			frame.setTitle("LIBRARIAN LOGIN");
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			frame.setSize(640, 350);
			frame.setIconImage(new ImageIcon("images/lm.png").getImage());	
		}
	}

	private class StudentEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UserLogin frame = new UserLogin();
			frame.setTitle("USER LOGIN");
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			frame.setSize(700, 350);
			frame.setIconImage(new ImageIcon().getImage());
			frame.setIconImage(new ImageIcon("images/lm.png").getImage());	

		}
	}

	public static void main(String[] args) {
		HomePage frame = new HomePage();
		frame.setTitle("Library Management System");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon("images/lm.png").getImage());	

	}
}