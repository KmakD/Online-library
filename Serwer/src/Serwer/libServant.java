package Serwer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import Interfejsy.Client;
import Interfejsy.ClientInterface;
import Interfejsy.ServerInterface;

public class libServant extends UnicastRemoteObject implements ServerInterface{
	Client clientlist;

	public libServant() throws RemoteException{
	}

	public boolean register(String nick, ClientInterface n) throws RemoteException {
		System.out.println("Server.register() " + nick);
		
		if(clientlist.add(nick, n)) {
			return true;
		}
		return false;
	}

	public boolean unregister(String nick) throws RemoteException {
		
		if (clientlist.dlt(nick)) {
			System.out.println("Server.unregister(): " + nick);
			return true;
		}
		return false;
	}
	
}
