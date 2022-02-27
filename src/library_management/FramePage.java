package library_management;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FramePage extends JFrame{

	/*
	public FramePage()
	{
		
		this.setTitle("RETURN_BOOKS");
		this.setLayout(new BorderLayout());
	//	this.add(new IssuePageInternalFrame(),BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,500);
		this.setResizable(false);

	}
*/

	
	public static void main(String[] args) {

		MainFrame mainframe = new MainFrame();
		mainframe.setVisible(true);
		mainframe.setSize(600,500);

	}

}
