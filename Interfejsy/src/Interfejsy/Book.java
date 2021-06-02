package Interfejsy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {

	public static List<String> books = new ArrayList<String>();
	public static List<Boolean> availability = new ArrayList<Boolean>();
	public static List<ClientInterface> client = new ArrayList<ClientInterface>();
	public static List<List<ClientInterface>> queue = new ArrayList<List<ClientInterface>>();
	
	public static void addBook(String string, boolean dostep) {
		books.add(string);
		availability.add(dostep);
		client.add(null);
		queue.add(new ArrayList<ClientInterface>());
	}
	
}
