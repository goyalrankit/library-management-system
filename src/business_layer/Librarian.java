package business_layer;

public class Librarian {
	
	private String libID;
	private String name;
	private String password;
	
	public Librarian(String libID2) 
	{
		libID = libID2;
	
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String p) {
		password = p;
	}
	public void setID(String librarianId) {
		libID=librarianId;	
	}
	public String getID() {
		return libID;
	}

}