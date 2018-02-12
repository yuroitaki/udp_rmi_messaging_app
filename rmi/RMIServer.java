/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import common.*;

public class RMIServer extends UnicastRemoteObject implements RMIServerI {

	private int totalMessages = -1;
	private int countMessage = 0;
	private boolean[] receivedMessages;

	public RMIServer() throws RemoteException {
		super();
	}

	public void receiveMessage(MessageInfo msg) throws RemoteException {

		// TO-DO: On receipt of first message, initialise the receive buffer
		if (totalMessages == -1) {
			totalMessages = msg.totalMessages;	
      	receivedMessages = new boolean[totalMessages];
		}

		// TO-DO: Log receipt of the message
		receivedMessages[msg.messageNum-1] = true;
		countMessage ++;

		// TO-DO: If this is the last expected message, then identify any missing messages
		// In RMI, every remote call will be executed provided that there is no connection error, 
		// then all the messages will be expected to be received.
		if (countMessage == totalMessages) { 
    	System.out.println("Total number of messages sent: " + totalMessages);
			System.out.println("Number of messages received: " + countMessage);
			totalMessages = -1;
			countMessage = 0;
		}
  }
    
  public static void main(String[] args) {
		
		RMIServer rmis = null;

		// TO-DO: Initialise Security Manager	
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}

		// TO-DO: Instantiate the server class and Bind to RMI registry
		try {
			String name = "RMIServer";
			rmis = new RMIServer();
			rebindServer(name, rmis);
			System.out.println("RMI Server bound");
		} catch (RemoteException e){
			System.err.println("Exception: " + e);
		}
	}

  protected static void rebindServer(String serverURL, RMIServer server) {

		// TO-DO: Start the registry
		int port = 4444;

		try {
 	    	Registry reg = LocateRegistry.createRegistry(port);

	    	// TO-DO: Now rebind the server to the registry
	    	reg.rebind(serverURL, server);
            
		} catch (RemoteException e) {
			System.err.println("Exception: " + e);
		}
  }
}
