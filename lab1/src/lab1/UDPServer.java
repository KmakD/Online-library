package lab1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UDPServer {
	public static void main ( String [] args ) {
		try {
		// args contain message content and server hostname
		DatagramSocket aSocket = new DatagramSocket (9879);
		byte [] buffer = new byte [1024];
		while ( true ) {
		DatagramPacket request = new DatagramPacket ( buffer , buffer . length );
		aSocket . receive ( request );
		DatagramPacket reply = new DatagramPacket ( request . getData () ,
		request . getLength () , request . getAddress () , request . getPort ());
		aSocket . send ( reply );
		//aSocket . close();
		}
		} catch ( SocketException ex) {
		Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE , null , ex );
		} catch ( IOException ex) {
		Logger . getLogger ( UDPServer . class . getName ()). log( Level . SEVERE , null , ex );
		}
		}
	
}
