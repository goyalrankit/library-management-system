package data_layer;

import java.util.ArrayList;

import business_layer.Librarian;
import presentation_layer.AddLibrarian;

public interface AddLibrarianReader{

	Librarian getLibrarian(String libID);

	ArrayList<Librarian> getLibrarians();

}