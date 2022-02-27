package data_layer;

import business_layer.*;

public interface AddUserWriter {
	
	boolean addAddUser(AddUsers addUsers);
	boolean delUser(String uId);

}
