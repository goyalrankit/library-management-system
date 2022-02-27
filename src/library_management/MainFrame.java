package library_management;


	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;

	public class MainFrame extends JFrame{

	    private JMenuBar mBar;
	    private JMenu mFile;
	    private JMenu mShow;
	    private JMenuItem mItemExit;
	    private JMenuItem mItemPerson;
	    private JDesktopPane desktopPane;

	    public MainFrame(){
	        initialize();
	        this.setTitle("Library Management");
	        this.setSize(550,450);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    }

	    public void initialize(){
	        mBar = new JMenuBar();
	        desktopPane = new JDesktopPane();
	        this.setContentPane(desktopPane);

	        mFile = new JMenu("File");

	        mItemExit = new JMenuItem("Exit");
	        mItemExit.addActionListener(new ExitEventHandler());

	        mShow = new JMenu("Student");

	        mItemPerson = new JMenuItem("Issue");
	        mItemPerson.addActionListener(new PersonEventHandler());

	        mFile.add(mItemExit);
	        mShow.add(mItemPerson);

	        mBar.add(mFile);
	        mBar.add(mShow);
	        this.setJMenuBar(mBar);

	    }

	    private class ExitEventHandler implements ActionListener{
	        public void actionPerformed(ActionEvent e){
	            System.exit(0);
	        }
	    }
	    private class PersonEventHandler implements ActionListener{
	        public void actionPerformed(ActionEvent e){
	            JInternalFrame pInternalFrame = new IssuePageInternalFrame((new IssuePage()));
	            pInternalFrame.setVisible(true);
	            MainFrame.this.desktopPane.add(pInternalFrame);
	        }
	    }
	
}
