package presentation_layer;

import javax.swing.*;

@SuppressWarnings("serial")
public class AddBookInternalFrame extends JInternalFrame {

	private JPanel panel;

	public AddBookInternalFrame(JPanel panel) {
		
		this.panel = panel;
		this.add(panel);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Add Book");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(700, 500);
		
	}

}
