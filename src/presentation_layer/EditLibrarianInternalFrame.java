package presentation_layer;

import javax.swing.*;

@SuppressWarnings("serial")
public class EditLibrarianInternalFrame  extends JInternalFrame{

	@SuppressWarnings("unused")
	private JPanel panel;

	public EditLibrarianInternalFrame(JPanel panel) 
	{
		this.panel = panel;
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Edit Librarian");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(700, 400);
	}

}
