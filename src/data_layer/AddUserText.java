package data_layer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import business_layer.*;
import data_layer.*;
public class AddUserText implements AddUserDAO{
	
	 private File addUserFile = null;
	
	 public AddUserText() {
		 addUserFile = new File(ADD_USERS_FILENAME_TEXT);
	 }

	 private void checkFile() throws IOException
	 {
		 if(!addUserFile.exists())
			 addUserFile.createNewFile();
	 }
	 
	 
	private boolean saveAddUsers(ArrayList<AddUsers> addUsers)
	{
		PrintWriter out = null;	
		try {
			this.checkFile();
			out = new PrintWriter(new BufferedWriter(new FileWriter(addUserFile)));
			for(AddUsers ad : addUsers)
			{
				out.print(ad.getUserId() + FIELD_SEP);
				out.print(ad.getUserName() + FIELD_SEP);
				out.print(ad.getUserType() + FIELD_SEP);
				out.print(ad.getPhone() + FIELD_SEP);
//old one				out.println(ad.getUserPass());
// new one					
				out.print(ad.getUserPass() + FIELD_SEP);
				out.println(ad.getIssuedBooks());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			this.close(out);
		}
		return true;
	}
	 
	private void close(Closeable stream) {
		try {
				if(stream!= null)
				 stream.close();
			} catch (IOException e){
				e.printStackTrace();
			}
	}
	

	@Override
	public AddUsers getAddUser(String userID) {
		
		ArrayList<AddUsers> addUsers =this.getAddUsers();
		
		for(AddUsers ad : addUsers)
		{
			if(ad.getUserId().equals(userID))
				return ad;
		}
		return null;
	}
	
	

	@Override
	public ArrayList<AddUsers> getAddUsers() {
		
		BufferedReader in = null;
		try {
			this.checkFile();
			in = new BufferedReader(new FileReader(addUserFile));		
			ArrayList<AddUsers> addUsers = new ArrayList<AddUsers>();
			
			String line = in.readLine();
			while(line != null)
    			{
				String [] columns = line.split(FIELD_SEP);
				
				String userId =  columns [0];
				String userName = columns [1];
				
				String userType = columns [2];
				long userPhone  = Long.parseLong(columns [3]);
				
				String userPass = columns [4];
// Below Line Added here				
				int userMaxBooks = Integer.parseInt(columns [5]);
			
				AddUsers ad = new AddUsers(userId, userName);
				ad.setUserType(userType);
				ad.setPhone(userPhone);
				ad.setUserPass(userPass);
// Below Line Added here
				ad.setIssuedBooks(userMaxBooks);
			
				
				addUsers.add(ad);
				line = in.readLine();
			}
			return addUsers;	
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}	finally{
			this.close(in);
		}			
	}

	@Override
	public boolean addAddUser(AddUsers addUsers) {
	
		ArrayList<AddUsers> ad = this.getAddUsers();
		ad.add(addUsers);
		return this.saveAddUsers(ad);
	}

	@Override
	public boolean delUser(String uId) {
		
	AddUsers delUser = null;
		
		ArrayList<AddUsers> addUsers =this.getAddUsers();
		
		for(AddUsers ad : addUsers)
		{
			if(ad.getUserId().equals(uId))
				
				delUser =ad;
		}
		addUsers.remove(delUser);
		return this.saveAddUsers(addUsers);
	
	}
	
}
