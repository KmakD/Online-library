package Serwer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import Interfejsy.ClientInterface;
import Interfejsy.ServerInterface;

public class libServant extends UnicastRemoteObject implements ServerInterface{

	private Map<String, ClientInterface> people = new HashMap<>();
	
	public libServant() throws RemoteException{
	}

	public boolean register(String nick, ClientInterface n) throws RemoteException {
		System.out.println("Server.register() " + nick);
		
		if(!people.containsKey(nick)) {
			people.put(nick, n);
			return true;
		}
		return false;
	}

	public boolean unregister(String nick) throws RemoteException {
		if (people.remove(nick) != null) {
			System.out.println("Server.unregister(): " + nick);
			return true;
		}
		return false;
	}
	
}
