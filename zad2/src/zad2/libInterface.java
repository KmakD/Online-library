package zad2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface libInterface extends Remote{
	boolean Book() throws RemoteException;
	boolean Client() throws RemoteException;
}
