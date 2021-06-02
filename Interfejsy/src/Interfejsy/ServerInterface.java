package Interfejsy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{
	//boolean register(String nick, ClientInterface n) throws RemoteException;
	void register(String nick, ClientInterface n) throws RemoteException;
	boolean unregister(String nick) throws RemoteException;
	String list(String str) throws RemoteException;
	String borrow(String str, ClientInterface n) throws RemoteException;
	String retback(String str, ClientInterface n) throws RemoteException;
}
