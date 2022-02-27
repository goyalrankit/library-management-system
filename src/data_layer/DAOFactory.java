package data_layer;

import data_layer.*;
import business_layer.*;

public class DAOFactory {


		public static AddLibrarianDAO getLibrarianDAO() {
			AddLibrarianDAO pDao=new AddLibrarianText();
			return pDao;
		}
	
	
	public static AddUserDAO getAddUserDAO()
	{
		AddUserDAO adDAO = new AddUserText();
		return adDAO;
	}
	
	public static AddUserDAO getUserDAO() {
		
		AddUserDAO pDAO=new AddUserText();
		return pDAO;
	}
	
}
