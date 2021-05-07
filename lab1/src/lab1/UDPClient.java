package lab1;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;



public class UDPClient {
	
	static int Portt = 0;
	public static int sendPort(int a) {
		Portt = a;
		return Portt;
	}
	
	public static void main ( String [] args ) {
		
		int port = 0;

		Thread chat = new Thread(()->{
			int port2 = 0;
			try {
				while(true) {
					
					DatagramSocket apSocket = new DatagramSocket (port2);
					port2 = apSocket.getLocalPort();
					
					sendPort(port2);

					byte [] buffer = new byte [1024];
					DatagramPacket reply = new DatagramPacket ( buffer , buffer.length );	
					apSocket.setSoTimeout(3000);
					try {
						apSocket.receive ( reply );
						System.out.println (" Incoming : " + new String ( reply.getData(), reply.getOffset(), reply.getLength()));
					}catch(SocketTimeoutException e) {}
					apSocket.close();
					//Thread.sleep(4000);
					
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
			DatagramSocket aSocket = new DatagramSocket (port);
			port = aSocket.getLocalPort();
			aSocket.setSoTimeout(3000);
			String mesage = "";
			Scanner scan = new Scanner(System.in);

			mesage = scan.nextLine();
			System.out.println(" Sent : " + mesage);
			
			if(mesage.charAt(0)!='+') {	
				byte [] msg = mesage.getBytes();
				InetAddress aHost = InetAddress.getByName ("localhost");
				DatagramPacket request = new DatagramPacket (msg , msg.length , aHost , serverPort );
				aSocket.send ( request );
				
				byte [] buffer = new byte [1024];
				DatagramPacket reply = new DatagramPacket ( buffer , buffer.length );
				aSocket.receive ( reply );
				System.out.println (" Reply : " + new String ( reply.getData(), reply.getOffset(), reply.getLength()));
			}else if(mesage.charAt(0)=='+'){
				mesage = mesage + "|" + Portt; 
				byte [] msg = mesage.getBytes();
				InetAddress aHost = InetAddress.getByName ("localhost");
				DatagramPacket request = new DatagramPacket (msg , msg.length , aHost , serverPort );
				aSocket.send ( request );
				
				byte [] buffer = new byte [1024];
				DatagramPacket reply = new DatagramPacket ( buffer , buffer.length );
				aSocket.receive ( reply );
				System.out.println (" Reply : " + new String ( reply.getData(), reply.getOffset(), reply.getLength()));
			}
			
			aSocket.close();

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
