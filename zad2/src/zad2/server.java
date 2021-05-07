package zad2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class server {
	Registry reg;
	libServant servant;
	
	public static void main(String[] args) throws RemoteException {
		try{
			new server();
		}catch(RemoteException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	protected server() throws RemoteException {
		try {
			reg = LocateRegistry.createRegistry(1099);
			servant = new libServant();
			reg.rebind("sever", servant);
			System.out.println("Server Ready");
		}catch(RemoteException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
