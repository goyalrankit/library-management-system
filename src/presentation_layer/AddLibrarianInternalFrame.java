package presentation_layer;

import javax.swing.*;

public class AddLibrarianInternalFrame  extends JInternalFrame{

	private JPanel panel;

	public AddLibrarianInternalFrame(JPanel panel) 
	{
		this.panel = panel;
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Add Librarian");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(700, 400);
	}

}
