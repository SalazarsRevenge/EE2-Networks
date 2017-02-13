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

		// Get the parameters:
		// internetaddress, receiveport, # of messages
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


		// Construct UDP client class and try to send messages
		UDPClient myClient = new UDPClient(recvPort);

		myClient.testLoop(serverAddr, recvPort, countTo);

	}
	


	public UDPClient(int recvPort) {
		// Initialise the UDP socket for sending data
		try {
			sendSoc = new DatagramSocket(recvPort);
		} catch (Exception e) {
			System.out.println("could not create datagramsocket");
		}
	}

	private void testLoop(InetAddress serverAddr, int recvPort, int countTo) {

		for(int tries = 0; tries < 1; tries++){
			// Send the messages to the server
			for(int i = 0; i < countTo; i++){

				String m = new String( (Integer.toString(countTo)) + ";" + (Integer.toString(i)) );
				this.send(m, serverAddr, recvPort);

				byte[] buffer = new byte[1000];
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				
				try {
					sendSoc.receive(reply);
					System.out.println("Reply: " + new String(reply.getData()) + "    message no " + (i+1));
				} catch (Exception e){
					System.out.println(e.toString());
					System.out.println("message not received");
				}
			}
		}

	}

	private void send(String payload, InetAddress destAddr, int destPort) {
		int payloadSize = payload.length();
		byte[] pktData = payload.getBytes();

		// TO-DO: build the datagram packet and send it to the server
		try {
			DatagramPacket pkt = new DatagramPacket(pktData, payloadSize, destAddr, destPort);
			sendSoc.send(pkt);
		} catch (Exception e){
			System.out.println(e.toString());
			System.out.println("message not sent");
		}
	}

}
