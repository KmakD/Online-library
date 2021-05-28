package Serwer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Interfejsy.Book;
import Interfejsy.Client;
import Interfejsy.ClientInterface;
import Interfejsy.ServerInterface;


public class libServant extends UnicastRemoteObject implements ServerInterface{
	Client clientlist = new Client();

	public libServant() throws RemoteException{
		Book.addBook("FHJK autor1 ISBN", true);
		Book.addBook("FDAS autor2 ISBN", true);
		Book.addBook("ADS autor3 ISBN", true);
		Book.addBook("JGF autor1 ISBN", true);
	}


	public void register(String nick, ClientInterface n) throws RemoteException {
		System.out.println("Server.register() " + nick);		
		clientlist.add(nick, n);
	}

	public boolean unregister(String nick) throws RemoteException {	
		if (clientlist.dlt(nick)) {
			System.out.println("Server.unregister(): " + nick);
			return true;
		}
		return false;
	}

	public String list(String str) throws RemoteException {
		List<String> books = Book.books;
		List<Boolean> availability = Book.availability;
		String list_of_books = "List of books:\n";
		for (int i=0; i<books.size(); i++) {
			if(books.get(i).contains(str) || str=="") {
				//
				if(availability.get(i)) {
					list_of_books = list_of_books + books.get(i) + " | dostêpna\n";
				}else {
					list_of_books = list_of_books + books.get(i) + " | niedostêpna\n";
				}
			}
		}
		return list_of_books;
	}

	public String borrow(String str, ClientInterface n) throws RemoteException {
		List<String> books = Book.books;
		List<Boolean> availability = Book.availability;
		List<ClientInterface> client = Book.client;
		String ret_msg = "";
		for (int i=0; i<books.size(); i++) {
			if(books.get(i).contains(str)) {
				if(availability.get(i)) {
					availability.set(i, false);
					client.set(i, n);
					ret_msg = "Wyporzy¿ono:\n" + books.get(i) + "\n";
					break;
				}else {
					Book.queue.get(i).add(n);
					ret_msg = "Ksi¹¿ka niedostêpna. Dodano do kolejki wypo¿ycznania.\n";
					break;
				}	
			}else {
				ret_msg = "Niepoprawna nazwa ksi¹¿ki.\n";
			}
		}
		return ret_msg;
	}

	public String retback(String str, ClientInterface n) throws RemoteException {
		List<String> books = Book.books;
		List<Boolean> availability = Book.availability;
		List<ClientInterface> client = Book.client;
		String ret_msg = "";
		for (int i=0; i<books.size(); i++) {
			if(books.get(i).contains(str)) {
				if(client.get(i).equals(n)) {
					availability.set(i, true);
					fncCallback(books.get(i));
					ret_msg = "Oddano:\n" + books.get(i) + "\n";
					break;
				}else {
					ret_msg = "Nie wyporzyczy³eœ tej ksi¹¿ki\n";
					break;
				}	
			}else {
				ret_msg = "Niepoprawna nazwa ksi¹¿ki\n";
			}
		}
		return ret_msg;
	}


	private void fncCallback(String book) throws RemoteException {
		List<List<ClientInterface>> queue = Book.queue;
		int index = Book.books.indexOf(book);
		if(!queue.get(index).isEmpty()) {
			borrow(book, queue.get(index).get(0));
			String msg = "Wypo¿yczono: \n" + Book.books.get(index);
			queue.get(index).get(0).inform(msg);
			queue.get(index).remove(0);
		}
	}
	
}
