package lab1;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class UDPClient {
	public static void main ( String [] args ) {
		
		Thread chat = new Thread(()->{
			try {
				while(true) {
					DatagramSocket apSocket = new DatagramSocket ();
					
					byte [] buffer = new byte [1024];
					DatagramPacket reply = new DatagramPacket ( buffer , buffer.length );	
					apSocket.setSoTimeout(0);
					/*try {
						apSocket.receive ( reply );
						System.out.println (" Reply : " + new String ( reply.getData(), reply.getOffset(), reply.getLength()));
					}catch(SocketTimeoutException e) {}*/
					apSocket.receive ( reply );
					System.out.println (" Reply : " + new String ( reply.getData(), reply.getOffset(), reply.getLength()));
				
					apSocket.close();
				}

			} catch ( SocketException ex) {
			Logger . getLogger ( UDPClient . class . getName ()). log ( Level . SEVERE , null , ex );
			} catch ( UnknownHostException ex) {
			Logger . getLogger ( UDPClient . class . getName ()). log ( Level . SEVERE , null , ex );
			} catch ( IOException ex) {
			Logger . getLogger ( UDPClient . class . getName ()). log ( Level . SEVERE , null , ex );
			}
		});
		
		chat.start();
		
		try {
		while(true) {			
			int serverPort = 9876;
			DatagramSocket aSocket = new DatagramSocket ();
			aSocket.setSoTimeout(3000);
			String mesage = "";
			Scanner scan = new Scanner(System.in);
			if(scan.hasNext()) {
				mesage = scan.nextLine();
				System.out.println(" Sent : " + mesage);
				byte [] msg = mesage.getBytes();
				
				InetAddress aHost = InetAddress.getByName ("localhost");
				DatagramPacket request = new DatagramPacket (msg , msg.length , aHost , serverPort );
				aSocket.send ( request );
				
				byte [] buffer = new byte [1024];
				DatagramPacket reply = new DatagramPacket ( buffer , buffer.length );
				aSocket.receive ( reply );
				System.out.println (" Reply : " + new String ( reply.getData(), reply.getOffset(), reply.getLength()));
			}
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
