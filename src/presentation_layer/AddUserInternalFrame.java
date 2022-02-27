package presentation_layer;

import javax.swing.*;

public class AddUserInternalFrame  extends JInternalFrame{

	private JPanel panel;

	public AddUserInternalFrame(JPanel panel) 
	{
		this.panel = panel;
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Add User");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(600, 500);
	}

}
