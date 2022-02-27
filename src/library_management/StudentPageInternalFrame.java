package library_management;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class StudentPageInternalFrame  extends JInternalFrame{

	private JPanel panel;

	public StudentPageInternalFrame(JPanel panel) 
	{
		this.panel = panel;
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("ISSUE BOOKS");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(600, 500);
	}

}
