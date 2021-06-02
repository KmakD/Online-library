package Serwer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class libServer{
	Registry reg;
	libServant servant;
	
	public static void main(String[] args) {
		try {
			new libServer();
		}catch (RemoteException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	protected libServer() throws RemoteException {
		try {
			reg = LocateRegistry.createRegistry(1099);
			servant = new libServant();
			reg.rebind("libServer", servant);
			System.out.println("Server Ready");
		}catch(RemoteException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
