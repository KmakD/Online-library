package Klient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import Interfejsy.Book;
import Interfejsy.ClientInterface;
import Interfejsy.ServerInterface;

public class libClient{
	private Scanner userInput = new Scanner(System.in);
	ServerInterface remoteObject;
	ClientInterface callback;
	String username;
	//Book book; //= new Book("a",true);

	public static void main(String[] args) {
		/*if (args.length < 1) {
			System.out.println("Usage: libClient <server host name>");
			System.exit(-1);
		}*/
		new libClient("libServer");
	}
	
	public libClient(String hostname) {
		System.out.println("Enter client name: ");
		if (userInput.hasNextLine()) {
			username = userInput.nextLine();
		}
		Registry reg;
		try {
			//reg = LocateRegistry.getRegistry(hostname);
			reg = LocateRegistry.getRegistry();
			remoteObject = (ServerInterface) reg.lookup("libServer");
			callback = new ClientCallback();
			remoteObject.register(username, callback);
			loop();
			remoteObject.unregister(username);
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	void list(String str) throws RemoteException {
		//listing books
		String list_of_books = remoteObject.list(str);
		System.out.print(list_of_books);
	}
	
	void borrow(String str) throws RemoteException {
		//borrow book
		String ret_msg = remoteObject.borrow(str, callback);
		System.out.print(ret_msg);
	}
	
	void retback(String str) throws RemoteException {
		//return book
		String ret_msg = remoteObject.retback(str, callback);
		System.out.print(ret_msg);
	}
	
	void loop() throws RemoteException {
		while(true) {
			String line;
			System.out.println("[" + username + "] >" + "[l]ist of books, [b]orrow book, [r]eturn book [q]uit: ");
			System.out.println("Welcome to library");
			if(userInput.hasNextLine()) {
				//reading client requests
				line = userInput.nextLine();
				if (!line.matches("[lbrq]")) {
					System.out.println("Invalid command");
				}
				switch (line) {
				case "l":
					System.out.print("Wyszukaj: \n");
					line = userInput.nextLine();
					list(line);
					break;
				case "b":
					System.out.print("Wpisz nazwê ksi¹¿ki, któr¹ chcesz wypo¿yczyæ: \n");
					line = userInput.nextLine();
					borrow(line);
					break;
				case "r":
					System.out.print("Wpisz nazwê ksi¹¿ki, któr¹ chcesz oddaæ: \n");
					line = userInput.nextLine();
					retback(line);
					break;
				case "q":
					return;
				}
			}
		}
	}
}
