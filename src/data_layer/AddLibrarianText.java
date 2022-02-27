package data_layer;

import java.io.*;
import java.util.ArrayList;
import business_layer.Librarian;

public class AddLibrarianText implements  AddLibrarianDAO {

	private File librarianFile = null;

	public AddLibrarianText() {
		librarianFile = new File(FILENAME_TEXT);
	}

	private void checkFile() throws IOException {
		if (!librarianFile.exists()) {
			librarianFile.createNewFile();
		}
	}
	
	public boolean deleteLibrarian(String libId)
	{
		Librarian target = null;
		
		ArrayList<Librarian> librarian=this.getLibrarians();
		for (Librarian l:librarian)
		{
			if(l.getID().equals(libId))	
			{
				 target=l;
				
			}	
					
	    }
		librarian.remove(target);
		return this.saveLibrarian(librarian);
			
   }

	private boolean saveLibrarian(ArrayList<Librarian> librarians) {

		PrintWriter out = null;

		try {

			this.checkFile();
			out = new PrintWriter(new BufferedWriter(new FileWriter(librarianFile)));

			for (Librarian l : librarians) {
				out.print(l.getID()+FIELD_SEP);
				out.print(l.getName() + FIELD_SEP);
				out.println(l.getPassword());

			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		finally {
			this.close(out);
		}

		return true;
	}

	private void close(Closeable stream) {
		try {
		if (stream != null) 
				stream.close();
			} 
		catch (IOException e)
		{
				e.printStackTrace();
		}
	}
	

	@Override
	public ArrayList<Librarian> getLibrarians() {
		BufferedReader in = null;
		try {
			this.checkFile();
			in = new BufferedReader(new FileReader(librarianFile));
			ArrayList<Librarian> librarians = new ArrayList<Librarian>();
			String line = in.readLine();
			while (line != null) {
				String[] columns = line.split(FIELD_SEP);
				
				String libID=columns[0];
				String libName = columns[1];
				String password = columns[2];
				
				Librarian l = new Librarian(libID);
				//l.setID(libID);
				l.setName(libName);
				l.setPassword(password);
			
				librarians.add(l);
				line = in.readLine();
			}
			return librarians;
		}

		catch (IOException io) {
			io.printStackTrace();
			return null;
		} finally {
			this.close(in);
		}

	}
	
	@Override
	public boolean addLibrarian(Librarian addLibrarian) {
		ArrayList<Librarian> librarians = this.getLibrarians();
		librarians.add(addLibrarian);

		return this.saveLibrarian(librarians);
	}

	@Override
	public Librarian getLibrarian(String libID) {
		ArrayList<Librarian> addLibrarian = this.getLibrarians();

		for (Librarian l : addLibrarian) 
		{
			if (l.getID().equalsIgnoreCase(libID))
				return l;
		}
		return null;
	}

}