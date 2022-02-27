package presentation_layer;

import javax.swing.*;

@SuppressWarnings("serial")
public class IssuePageInternalFrame extends JInternalFrame{

		private JPanel panel;

		public IssuePageInternalFrame(JPanel panel) 
		{
			this.panel = panel;
			this.add(panel);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("ISSUE BOOKS");
			this.setIconifiable(true);
			this.setClosable(true);
			this.setSize(700, 500);
		}

}
