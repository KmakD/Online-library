package Interfejsy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Client implements Serializable {
	public Map<String, ClientInterface> people = new HashMap<>();
	
	public void add(String nick, ClientInterface n) {
		if(!people.containsKey(nick)) {
			people.put(nick, n);
		}
	}
	
	public boolean dlt(String nick) {
		if (people.remove(nick) != null) {
			return true;
		}
		return false;
	}
}
