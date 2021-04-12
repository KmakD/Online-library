package lab1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UDPServer {
	public static void main ( String [] args ) {
		try {
		List<String> users_list = new ArrayList<String>();
		DatagramSocket aSocket = new DatagramSocket (9876);
		byte [] buffer = new byte [1024];
		while ( true ) {		
			DatagramPacket request = new DatagramPacket ( buffer , buffer . length );
			aSocket.receive ( request );
			byte [] message = request.getData();
			int lastgood = 0;
			for(int i = 0; i<message.length;i++) {
				if(message[i] == 0) {
					lastgood = i;
					break;
				}
			}
			String msg = new String(message, 0, lastgood);
		
			String reply_msg = "";
			
			if(msg.charAt(0) == '+') {
				String add_user = msg.substring(1);
				users_list.add(add_user);
				reply_msg = msg + " Added";
			}
			if(msg.charAt(0) == '-') {
				String dlt_user = msg.substring(1);
				users_list.remove(dlt_user);
				reply_msg = msg + " Removed";
			}
			if(msg.charAt(0) == '?') {
				reply_msg = "?";
					
				if(msg.length()==2) { 
					char check = msg.charAt(1);
					for(int i=0; i<users_list.size(); i++) {
						String element = users_list.get(i);
						if(element.charAt(0) == check) {
							reply_msg = reply_msg + " " + users_list.get(i);
						}
					}
				}else {
					String check = msg.substring(1);
					for(int i=0; i<users_list.size(); i++) {
						String element = users_list.get(i);
						if(element.contains(check)) {
							reply_msg = reply_msg + " " + users_list.get(i);
						}
					}
				}
			}
			byte [] replyy = reply_msg.getBytes();
			
			DatagramPacket reply = new DatagramPacket ( replyy ,
			replyy.length , request.getAddress () , request.getPort ());
			aSocket.send ( reply );
			
			for(int i = 0; i<message.length; i++) {
				message[i] = 0;
			}
		}
		} catch ( SocketException ex) {
		Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE , null , ex );
		} catch ( IOException ex) {
		Logger . getLogger ( UDPServer . class . getName ()). log( Level . SEVERE , null , ex );
		}
	}
}
