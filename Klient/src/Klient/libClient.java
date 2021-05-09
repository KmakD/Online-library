package Klient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Interfejsy.ClientInterface;
import Interfejsy.ServerInterface;

public class libClient{
	private Scanner userInput = new Scanner(System.in);
	ServerInterface remoteObject;
	ClientInterface callback;
	String username;

	public static void main(String[] args) {
		/*if (args.length < 1) {
			System.out.println("Usage: libClient <server host name>");
			System.exit(-1);
		}*/
		new libClient("1099");
	}
	
	public libClient(String hostname) {
		System.out.println("Enter client name: ");
		if (userInput.hasNextLine()) {
			username = userInput.nextLine();
		}
		Registry reg;
		try {
			reg = LocateRegistry.getRegistry(hostname);
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
	
	void list() {
		//listing books
	}
	
	void borrow() {
		//borrow book
	}
	
	void retback() {
		//return book
	}
	
	void loop() {
		while(true) {
			String line;
			System.out.println("[" + username + "] >" + "[l]ist of books, [b]orrow book, [r]eturn book [q]uit: ");
			System.out.println("Welcome to library");
			if(userInput.hasNextLine()) {
				//reading client requests
				line = userInput.nextLine();
				if (line.matches("[q]")) {
					System.out.println("Invalid command");
				}
				switch (line) {
				case "l":
					list();
					break;
				case "b":
					borrow();
					break;
				case "r":
					retback();
					break;
				case "q":
					return;
				}
			}
		}
	}
}
