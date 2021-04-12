package lab1;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
	public static void main ( String [] args ) {
		try {
			/*if( args . length < 2) {
				System . out . println (" Usage : UDPClient <msg > <server host name >");
				System . exit ( -1);
		}*/
		while(true) {			
			int serverPort = 9876;
			DatagramSocket aSocket = new DatagramSocket ();
			aSocket.setSoTimeout(3000);
			byte [] msg = new byte [1024];
			String mesage = "";
			Scanner scan = new Scanner(System.in);
			mesage = scan.nextLine();
			System.out.println(" Sent : " + mesage);
			msg = mesage.getBytes();
			
			InetAddress aHost = InetAddress.getByName ("localhost");
			DatagramPacket request = new DatagramPacket (msg , msg.length , aHost , serverPort );
			aSocket.send ( request );
			byte [] buffer = new byte [1024];
			DatagramPacket reply = new DatagramPacket ( buffer , buffer.length );
			aSocket.receive ( reply );
			System.out.println (" Reply : " + new String ( reply.getData(), reply.getOffset(), reply.getLength()));
			aSocket.close ();
		}
		} catch ( SocketException ex) {
		Logger . getLogger ( UDPClient . class . getName ()). log ( Level . SEVERE , null , ex );
		} catch ( UnknownHostException ex) {
		Logger . getLogger ( UDPClient . class . getName ()). log ( Level . SEVERE , null , ex );
		} catch ( IOException ex) {
		Logger . getLogger ( UDPClient . class . getName ()). log ( Level . SEVERE , null , ex );
		}
		}

}
