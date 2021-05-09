package Klient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Interfejsy.ClientInterface;

public class ClientCallback extends UnicastRemoteObject implements ClientInterface{
	
	public ClientCallback() throws RemoteException{
		super();
	}
	
}
