package Interfejsy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{
	boolean register(String nick, ClientInterface n) throws RemoteException;
	boolean unregister(String nick) throws RemoteException;
}
