package presentation_layer;


import javax.swing.*;

@SuppressWarnings("serial")
public class ReturnPageInternalFrame extends JInternalFrame {
	
	private JPanel panel;

	public ReturnPageInternalFrame (JPanel panel) 
	{
		this.panel = panel;
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Return Books");
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(700, 400);
	}
	
}