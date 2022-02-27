package business_layer;

import javax.swing.*;
import javax.swing.text.JTextComponent;


public class Validator  
{
	
	public static boolean isGenre(JComboBox c,String title) {
		if(c.getSelectedIndex()==0)
		{
			showMessage(c,title + " is a required field.\n Please re-enter.");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	private static void showMessage(JComboBox c, String message) {
		JOptionPane.showMessageDialog(c,message,"Invalid Entry",JOptionPane.ERROR_MESSAGE);
		
	}

	public static boolean isString(JTextField c, String title) {
		try {
			c.getText();
			return true;
		}
		catch(NumberFormatException e) {
			showMessage(c,title+" must be String.\nPlease re-enter.");
			c.requestFocusInWindow();
			return false;
		}
	}

	public static boolean isPresent(JTextComponent c,String title) {
		if(c.getText().length()==0)
		{
			showMessage(c,title + " is a required field.\n Please re-enter.");
			c.requestFocusInWindow();
			return false;
		}
		return true;
	}

	public static boolean isInteger(JTextComponent c, String title) {
		try {
			int i=Integer.parseInt(c.getText());
			return true;
		}
		catch(NumberFormatException e) {
			showMessage(c,title+" must be integer.\nPlease re-enter.");
			c.requestFocusInWindow();
			return false;
		}
	}
	
	public static boolean isDouble(JTextComponent c,String title) {
		try {
			double i=Double.parseDouble(c.getText());
			return true;
		}
		catch(NumberFormatException e) {
			showMessage(c,title+"must be Double.\nPlease re-enter.");
			c.requestFocusInWindow();
			return false;
		}
	}
	
	public static boolean isLength(JTextComponent c, String title) {
	
			if((c.getText().length())==10) 
			{
			return true;
			}
	else {
			showMessage(c,title+" must be of 10 digits.\nPlease re-enter.");
			c.requestFocusInWindow();
			return false;
		}
	}
	
	public static boolean isMatch(JTextComponent c,JTextComponent b, String title) {

			if(c.getText()==b.getText()) 
            return true;
		else{
			showMessage(c,title+" must match\nPlease re-enter.");
			c.requestFocusInWindow();
			return false;
		}
	
	}
	
	public static void showMessage(JTextComponent c, String message) {
		JOptionPane.showMessageDialog(c,message,"Invalid Entry",JOptionPane.ERROR_MESSAGE);
	}


	
}
