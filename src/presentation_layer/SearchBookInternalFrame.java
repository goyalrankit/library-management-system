package presentation_layer;

import javax.swing.*;

@SuppressWarnings("serial")
public class SearchBookInternalFrame extends JInternalFrame {

	private JPanel panel;

	public SearchBookInternalFrame(JPanel panel) {

		this.panel = panel;
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Search Books");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(700,400);
	}

}
