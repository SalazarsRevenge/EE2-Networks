/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.rmi.RMISecurityManager;

import common.*;

public class RMIServer extends UnicastRemoteObject implements RMIServerI {

	private int totalMessages = -1;
	private int[] receivedMessages;

	public RMIServer() throws RemoteException {}

	public static void main(String[] args) {

		RMIServer rmis = null;

		// Initialise Security Manager
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}

		// Instantiate the server class - what is this
		//RMIServer myServer = new RMIServer();

		// Bind to RMI registry
		try {
			// default registry port 1099
			LocateRegistry.createRegistry(1099);
			Naming.rebind("RMIServer", new RMIServer());
		} catch (RemoteException e) {
			System.out.println("Error initializing registry or binding server.");
			System.exit(-1);
		} catch (MalformedURLException e) {
			System.out.println("Could not bind server to defined registry as the URL was malformed.");
			System.exit(-1);
		}
		System.out.println("Waiting...");


	}

	public void receiveMessage(MessageInfo msg) throws RemoteException {

		// On receipt of first message, initialise the receive buffer
		if(receivedMessages == null){
			totalMessages = msg.totalMessages;
			receivedMessages = new int[msg.totalMessages];
		}

		// Log receipt of the message
		receivedMessages[msg.messageNum] = 1;

		// If this is the last expected message, then identify
		// any missing messages
		if(msg.messageNum + 1 == totalMessages){
			LOGprint();
		}

	}

	protected static void rebindServer(String serverURL, RMIServer server) {

		// TO-DO:
		// Start / find the registry (hint use LocateRegistry.createRegistry(...)
		// If we *know* the registry is running we could skip this (eg run rmiregistry in the start script)

		// TO-DO:
		// Now rebind the server to the registry (rebind replaces any existing servers bound to the serverURL)
		// Note - Registry.rebind (as returned by createRegistry / getRegistry) does something similar but
		// expects different things from the URL field.
	}

	private void LOGprint(){

		String s = "Lost messages: ";
		int count = 0;

		for(int i = 0; i < totalMessages; i++){
			if(receivedMessages[i] != 1){
				count++;
				s = s + " " + (i+1) + ", ";
			}
		}

		if(count == 0){
			s = s + "None";
		}

		System.out.println("LOG:");
		System.out.println("No of messages sent: " + totalMessages);
		System.out.println("No of messages received: " + (totalMessages - count));
		System.out.println("No of messages lost: " + count);
		System.out.println(s);
		System.out.println("LOG END");
		System.exit(0);

	}

}
