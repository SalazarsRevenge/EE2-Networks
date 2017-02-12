/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.*;
import java.net.*;

/*
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
*/

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
		int	recvPort;

		// Get the parameters from command line
		if (args.length < 1) {
			System.err.println("Arguments required: recv port");
			System.exit(-1);
		}
		recvPort = Integer.parseInt(args[0]);
		//System.out.println(recvPort);
		UDPServer myServer = new UDPServer(recvPort);

		myServer.run();
	}

	private void run() {
		int pacSize;
		byte[] pacData;
		DatagramPacket 	pac;

		// TO-DO: Receive the messages and process them by calling processMessage(...).
		//        Use a timeout (e.g. 30 secs) to ensure the program doesn't block forever

		try{
			pacSize = 1000;
			pacData = new byte[pacSize];
			while(true){
				pac = new DatagramPacket(pacData, pacSize);
				recvSoc.receive(pac);
				System.out.println("Message: " + new String(pac.getData()));
				DatagramPacket reply = new DatagramPacket(pac.getData(), pac.getLength(), pac.getAddress(), pac.getPort());
				recvSoc.send(reply);

			}

		}catch(SocketException e){System.out.println("Socket: " +e.getMessage());
		}catch(IOException e){System.out.println("IO: "+e.getMessage());
		}

	}

	public void processMessage(String data) {

		MessageInfo msg = null;

		// TO-DO: Use the data to construct a new MessageInfo object

		// TO-DO: On receipt of first message, initialise the receive buffer

		// TO-DO: Log receipt of the message

		// TO-DO: If this is the last expected message, then identify
		//        any missing messages

	}


	public UDPServer(int rp) {
		// TO-DO: Initialise UDP socket for receiving data
		try {
			recvSoc = new DatagramSocket(rp);
		} catch (Exception e) {
			System.out.println("could not create datagramsocket");
		}
		// Done Initialisation
		System.out.println("UDPServer ready");
	}

}
