/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import common.MessageInfo;

public class UDPClient {

	/*
	public static void main(String[] args) {

		if (args.length == 0) {
		    System.out.println("shit");
		    return;
		}

		DatagramSocket aSocket = null;
		
		try { aSocket = new DatagramSocket();
			byte[] m = args[0].getBytes();
			InetAddress aHost = InetAddress.getByName(args[1]);
			int serverPort = 6789;
			DatagramPacket request = new DatagramPacket(m, args[0].length(), aHost, serverPort);
			aSocket.send(request);
			byte[] buffer = new byte[1024];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
	}}
	*/


	
	private DatagramSocket sendSoc;

	public static void main(String[] args) {
		InetAddress	serverAddr = null;
		int		recvPort;
		int 		countTo;
		String 		message;

		// Get the parameters
		if (args.length < 3) {
			System.err.println("Arguments required: server name/IP, recv port, message count");
			System.exit(-1);
		}

		try {
			serverAddr = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			System.out.println("Bad server address in UDPClient, " + args[0] + " caused an unknown host exception " + e);
			System.exit(-1);
		}
		recvPort = Integer.parseInt(args[1]);
		countTo = Integer.parseInt(args[2]);


		// TO-DO: Construct UDP client class and try to send messages
		DatagramSocket aSocket = null;
		
		try { aSocket = new DatagramSocket();
			//byte[] message = args[0].getBytes();
			//InetAddress aHost = InetAddress.getByName(args[1]);
			//int serverPort = 6789;
			DatagramPacket request = new DatagramPacket(message, message.length(), serverAddr, recvPort);
			aSocket.send(request);

			//byte[] buffer = new byte[1024];
			DatagramPacket reply = new DatagramPacket(message, message.length);
			aSocket.receive(reply);

			System.out.println("Reply: " + new String(reply.getData()));


		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());}

	}

	public UDPClient() {
		// TO-DO: Initialise the UDP socket for sending data
		//DatagramSocket sendSoc = null;				// we added this
		//try {							// we added this
		//	sendSoc = new DatagramSocket();			// we added this
		//}							// we added this
		

	}

	private void testLoop(InetAddress serverAddr, int recvPort, int countTo) {
		int				tries = 0;

		// TO-DO: Send the messages to the server
	}

	/*private void send(String payload, InetAddress destAddr, int destPort) {
		int			payloadSize;
		byte[]			pktData;
		DatagramPacket		pkt;

		// TO-DO: build the datagram packet and send it to the server
		//DatagramPacket pkt = new DatagramPacket(pktData, args[0].length(), destAddr, destPort);	// we added this
		

	}*/
	
}
