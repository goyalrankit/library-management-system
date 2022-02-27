package data_layer;

import java.util.ArrayList;

import business_layer.AddUsers;

public interface AddUserReader {

	AddUsers getAddUser(String text);
	ArrayList<AddUsers> getAddUsers();
	
}
