/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.*;
import java.net.*;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;


import java.util.Arrays;

import common.MessageInfo;

public class UDPServer {

	/*
	public static void main(String ags[]){
	DatagramSocket aSocket = null;

	try{
		//aSocket = new DatagramSocket(Integer.parseInt(args[0]));
		aSocket = new DatagramSocket(6789);
		byte[] buffer = new byte[1000];

		while(true){
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(request);
			DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
			aSocket.send(reply);
		}

	}catch(SocketException e){System.out.println("Socket: " +e.getMessage());
	}catch(IOException e){System.out.println("IO: "+e.getMessage());
	}}
	*/

//------------------------------------------------------------------------------
	
	private DatagramSocket recvSoc;
	private int totalMessages = -1;
	private int[] receivedMessages;
	private boolean close;

	public static void main(String args[]) {
		int recvPort;

		// Get the recvPort from command line
		if (args.length < 1) {
			System.err.println("Arguments required: recv port");
			System.exit(-1);
		}
		recvPort = Integer.parseInt(args[0]);
		UDPServer myServer = new UDPServer(recvPort);

		try {
			myServer.run();
		} catch (SocketTimeoutException e) {}

	}

	private void run() throws SocketTimeoutException {
		int pacSize;
		byte[] pacData;
		DatagramPacket pac;

		// Receive the messages and process them by calling processMessage(...).
		// Use a timeout (30 secs) to ensure the program doesn't block forever

		System.out.println("Waiting...");

		while(!close){

			pacSize = 1024;
			pacData = new byte[pacSize];
			pac = new DatagramPacket(pacData, pacSize);

			try{
				recvSoc.setSoTimeout(30000);
				recvSoc.receive(pac);
					
			} catch (IOException e) {
				System.out.println("Time out. Closing server");
				System.exit(-1);
			}

			processMessage(new String(pac.getData()));

		}

	}

	public void processMessage(String data) {

		MessageInfo msg = null;
		System.out.println("message data: " + data);

		// Use the data to construct a new MessageInfo object

		try {
			msg = new MessageInfo(data);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	    
		// On receipt of first message, initialize the receive buffer
		if (receivedMessages == null) {
			totalMessages = msg.totalMessages;
			receivedMessages = new int[totalMessages];
		}
		
		// On receipt of first message, initialise the receive buffer

		if(receivedMessages == null) {
			totalMessages = msg.totalMessages;
			receivedMessages = new int[totalMessages];
		}

		// Log receipt of the message

		receivedMessages[msg.messageNum] = 1;

		// If this is the last expected message, then identify any missing messages

		if(msg.messageNum + 1 == msg.totalMessages){
			close = true;

			String s = "Lost packet numbers: ";
			int count = 0;

			for(int i = 0; i < totalMessages; i++){

				if(receivedMessages[i] != 1){
					count++;
					s = s + (i+1) + ", ";
				}

			}
			
			if (count == 0){
				s = s + "None";
			}

			System.out.println("LOG:");
			System.out.println("Number of messages received successfully: " + (msg.totalMessages - count));
			System.out.println("of a total of " + msg.totalMessages);
			System.out.println("Number of messages not received: " + count);
			System.out.println(s);
			System.out.println("END OF LOG.");

		}

	}


	public UDPServer(int rp) {

		// Initialise UDP socket for receiving data
		try {
			recvSoc = new DatagramSocket(rp);
		} catch (Exception e) {
			System.out.println("could not create datagramsocket");
			System.exit(-1);
		}
		// Done Initialisation
		close = false;
		System.out.println("UDPServer ready");
	}

}
