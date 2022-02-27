package presentation_layer;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class EditUserInternalFrame extends JInternalFrame {

	private JPanel panel;

	public EditUserInternalFrame(JPanel panel) {

		this.panel = panel;
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Update Account");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(700,400);

	}
}
