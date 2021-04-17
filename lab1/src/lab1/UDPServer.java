package lab1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UDPServer {
	public static void main ( String [] args ) {
		try {
		List<String> users_list = new ArrayList<String>();
		List<InetAddress> users_ipaddress = new ArrayList<InetAddress>();
		List<String> users_port = new ArrayList<String>();
		List<String> users_find = new ArrayList<String>();
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
			reply_msg = request.getAddress().toString();
			String send_msg = "";
			
			//addd user
			if(msg.charAt(0) == '+') {
				String add_user = msg.substring(1);
				for(int i=0;i<add_user.length();i++) {
					if(add_user.charAt(i)=='|') {
						add_user = msg.substring(1,i+1);
						users_port.add(msg.substring(i+2));
						break;
					}
				}
				users_find.add(Integer.toString(request.getPort()));
				users_list.add(add_user);
				users_ipaddress.add(request.getAddress());
				reply_msg = "+" + add_user + " Added";
			}
			//remove user
			if(msg.charAt(0) == '-') {
				String dlt_user = msg.substring(1);
				users_list.remove(dlt_user);
				users_ipaddress.remove(request.getAddress());
				users_port.remove(Integer.toString(request.getPort()));
				reply_msg = msg + " Removed";
			}
			//send list of users
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
			//sending messages
			if(msg.charAt(0) == '!') {
				String recv = msg.substring(1);
				String user = "";
				String msgg = "";
				for(int i = 0; i<recv.length();i++) {
					if(recv.charAt(i) == '|') {
						msgg = msg.substring(i+2);
						break;
					}
					user = user + recv.charAt(i);
				}
				int index = users_list.indexOf(user);
				InetAddress IpAddress = users_ipaddress.get(index);
				int Port = Integer.parseInt(users_port.get(index));
				reply_msg = msg + " Sent to: " + user; 
				String prt = Integer.toString(request.getPort());
				String usr = users_list.get(users_find.indexOf(prt));
				send_msg = " Message: " + msgg + " from: " + usr;
				
				byte [] replyym = send_msg.getBytes();
				
				DatagramPacket replym = new DatagramPacket ( replyym ,
				replyym.length , IpAddress , Port);
				aSocket.send ( replym );
			}
			byte [] replyy = reply_msg.getBytes();
			
			DatagramPacket reply = new DatagramPacket ( replyy ,
			replyy.length , request.getAddress() , request.getPort ());
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
