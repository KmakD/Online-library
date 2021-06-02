package Interfejsy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
	public void inform(String msg) throws RemoteException;
}
