package Interfejsy;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class Client implements Serializable {
	public Map<String, ClientInterface> people = new HashMap<>();
	
	public boolean add(String nick, ClientInterface n) {
		System.out.println("Server.register() " + nick);
		
		if(!people.containsKey(nick)) {
			people.put(nick, n);
			return true;
		}
		return false;
	}
	
	public boolean dlt(String nick) {
		
		if (people.remove(nick) != null) {
			return true;
		}
		return false;
	}
}
