package Serwer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Interfejsy.ServerInterface;

public class libServant extends UnicastRemoteObject implements ServerInterface{
	
	public libServant() throws RemoteException{
	}
	
}
