package library_management;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class ReturnPageInternalFrame extends JInternalFrame {

	
	private JPanel panel;

	public ReturnPageInternalFrame (JPanel panel) 
	{
		this.panel = panel;
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("REQUEST BOOKS");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(600, 500);
	}
}