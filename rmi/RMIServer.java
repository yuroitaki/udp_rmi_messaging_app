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
import java.rmi.server.Unreferenced;

import common.*;

public class RMIServer extends UnicastRemoteObject implements RMIServerI, Unreferenced {

    private int totalMessages = -1;
    private int countMessage = 0;
    private boolean[] receivedMessages;

    public RMIServer() throws RemoteException {
	super();
    }

    public void receiveMessage(MessageInfo msg) throws RemoteException {

	System.out.println("Message received");
	// TO-DO: On receipt of first message, initialise the receive buffer
	if(totalMessages == -1){
	    totalMessages = msg.totalMessages;
	    receivedMessages = new boolean[totalMessages];
	}
	// TO-DO: Log receipt of the message
	receivedMessages[msg.messageNum-1] = true;
	countMessage++;

	// TO-DO: If this is the last expected message, then identify
	//        any missing messages
	System.out.println(msg.messageNum);

    }

    public void unreferenced(){
	System.out.println("Total number of messages sent: " + totalMessages);
	System.out.println("Number of messages received: " + countMessage);
	if(countMessage != totalMessages){
	    boolean firstLost = false;
	    System.out.println("Messages lost: ");
	    for(int i=0; i<totalMessages; i++){
		if(firstLost == false && receivedMessages[i]==false){
		    System.out.println(i+1);
		    firstLost = true;
		} else if(receivedMessages[i]==false){
		    System.out.println(", " + i+1);
		}
	    }
	}
    }


    public static void main(String[] args) {

	RMIServer rmis = null;

	// TO-DO: Initialise Security Manager
	if(System.getSecurityManager() == null){
	    System.setSecurityManager(new SecurityManager());
	}

	// TO-DO: Instantiate the server class
	try {
	    String name = "RMIServer";
	    // rmis = new RMIServer();
	    RMIServerI rmi = new RMIServer();
	    //RMIServer stub = (RMIServer) UnicastRemoteObject.exportObject(server, 0);
	    //Naming.rebind(name, rmis);
	    rebindServer(name, rmi);
	    System.out.println("RMI Server bound");
			
	} catch (Exception e){
	    e.printStackTrace();
	}

	// TO-DO: Bind to RMI registry
		
    }

    protected static void rebindServer(String serverURL, RMIServerI server) {

	// TO-DO:
	// Start / find the registry (hint use LocateRegistry.createRegistry(...)
	// If we *know* the registry is running we could skip this (eg run rmiregistry in the start script)
	// int port = 4444;
	try {
 	    // Registry reg = LocateRegistry.createRegistry(port);
	    Registry reg = LocateRegistry.getRegistry();

	    // TO-DO:
	    // Now rebind the server to the registry (rebind replaces any existing servers bound to the serverURL)
	    // Note - Registry.rebind (as returned by createRegistry / getRegistry) does something similar but
	    // expects different things from the URL field.
	    reg.rebind(serverURL, server);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
