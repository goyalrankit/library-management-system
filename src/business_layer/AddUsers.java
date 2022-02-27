package business_layer;

public class AddUsers {

	String userName,userType,userPass,userConfirmPass, userId;
	long phone;

// Recent Changes in Getter and Setter	
	String issuedBooksNames;
	int maxBooks;

	
	public AddUsers( String userID2,String userName) 
	{
		this.userId = userID2;
		this.userName = userName;

	}
	
	public AddUsers(String userName, String userType, String userPass, String userConfirmPass, String userId,
			long phone,int maxBooks) {
		this.userName = userName;
		this.userType = userType;
		this.userPass = userPass;
		this.userConfirmPass = userConfirmPass;
		this.userId = userId;
		this.phone = phone;
		this.maxBooks = maxBooks;
	}

	public AddUsers()
	{
	}

	public String getUserName() {
		return userName;
	}

	public String getUserType() {
		return userType;
	}

	public String getUserPass() {
		return userPass;
	}

	public String getUserConfirmPass() {
		return userConfirmPass;
	}

	public String getUserId() {
		return userId;
	}

	public long getPhone() {
		return phone;
	}

	public int getIssuedBooks()
	{
		return maxBooks;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public void setUserConfirmPass(String userConfirmPass) {
		this.userConfirmPass = userConfirmPass;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPhone(long userPhone) {
		this.phone = userPhone;
	}
	
	public void setIssuedBooks(int maxBooks)
	{
		this.maxBooks = maxBooks;
	}
		
}
