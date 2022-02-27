package data_layer;

import business_layer.Librarian;


public interface AddLibrarianWriter {

	boolean addLibrarian(Librarian addLibrarian);
	boolean deleteLibrarian(String libID);

}