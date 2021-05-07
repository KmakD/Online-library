package zad2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class libServant extends UnicastRemoteObject implements libInterface{

	public libServant() throws RemoteException{
	}
	@Override
	public boolean Book() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Client() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
