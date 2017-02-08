/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.*;
import java.net.*;
import java.util.Arrays;

/*
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
*/

import common.MessageInfo;

public class UDPClient {

	/*
	public static void main(String[] args) {

		DatagramSocket aSocket = null;
		int countTo = Integer.parseInt(args[2]);

	try{
		aSocket = new DatagramSocket();
		byte[] m = args[0].getBytes();
		InetAddress aHost = InetAddress.getByName(args[1]);
		//int serverPort = Integer.parseInt(args[2]);
		int serverPort = 6789;
		
		for(int i = 0; i < countTo; i++){
			DatagramPacket request = new DatagramPacket(m, args[0].length(), aHost, serverPort);
			aSocket.send(request);
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()) + "    message no " + (i+1));
		}

	}catch(SocketException e) {System.out.println("Socket: " + e.getMessage());
	}catch(IOException e){System.out.println("IO: " +e.getMessage());

	}}
	*/

//---------------------------------------------------------------------------------------------------------------------

	
	private DatagramSocket sendSoc;

	public static void main(String[] args) {
		InetAddress	serverAddr = null;
		int		recvPort;
		int 		countTo;
		String 		message;

		// Get the parameters
		if (args.length < 4) {
			System.err.println("Arguments required: server name/IP, recv port, message count");
			System.exit(-1);
		}

		try {
			serverAddr = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			System.out.println("Bad server address in UDPClient, " + args[0] + " caused an unknown host exception " + e);
			System.exit(-1);
		}

		//recvPort = Integer.parseInt(args[1]);
		recvPort = 6789;
		message = args[2];
		countTo = Integer.parseInt(args[3]);


		// TO-DO: Construct UDP client class and try to send messages
		UDPClient myClient = new UDPClient();

		try {
			myClient.testLoop(serverAddr, recvPort, countTo);
		
		}catch(SocketException e) {System.out.println("Socket: " + e.getMessage());
		}catch(IOException e){System.out.println("IO: " +e.getMessage());

	}}

	public UDPClient() {
		// TO-DO: Initialise the UDP socket for sending data
		DatagramSocket sendSocket = null;
		sendSocket = new DatagramSocket();
	}

	private void testLoop(InetAddress serverAddr, int recvPort, int countTo) {
		
		int tries = 0;

		// TO-DO: Send the messages to the server
		for(int i = 0; i < countTo; i++){

			myClient.send(args[2], serverAddr, recvPort);

			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			sendSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()) + "    message no " + (i+1));
		}

	}

	private void send(String payload, InetAddress destAddr, int destPort) {
		int payloadSize = payload.length();
		byte[] pktData;

		// TO-DO: build the datagram packet and send it to the server

		DatagramPacket pkt = new DatagramPacket(payload, payloadSize, destAddress, destPort);
		sendSocket.send(pkt);

	}
}
