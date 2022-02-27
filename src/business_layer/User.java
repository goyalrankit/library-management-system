package business_layer;

public class User {
	String userName, password;

	public User() {
	}

	public User(String u, String p) {
		userName = u;
		password = p;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String u) {
		userName = u;
	}

	public void setPassword(String p) {
		password = p;
	}
}
