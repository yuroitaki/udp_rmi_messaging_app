/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

import common.MessageInfo;

public class RMIClient {

    public static void main(String[] args) {

	// Check arguments for Server host and number of messages
	if (args.length < 2){
	    System.out.println("Needs 2 arguments: ServerHostName/IPAddress, TotalMessageCount");
	    System.exit(-1);
	}

	String urlServer = new String("rmi://" + args[0] + "/RMIServer");
	int numMessages = Integer.parseInt(args[1]);

	// TO-DO: Initialise Security Manager

	if(System.getSecurityManager() == null){
	    System.setSecurityManager(new SecurityManager());
	}
	       
	MessageInfo msg = null;

	// TO-DO: Bind to RMIServer
	try{
	    RMIServer msgServer = (RMIServer) Naming.lookup(urlServer);

	    // TO-DO: Attempt to send messages the specified number of times
	    
	    for(int i=0; i< numMessages; i++){
		msg = new MessageInfo(numMessages,i+1);
		msgServer.receiveMessage(msg);
	    }
	}
	catch(RemoteException e){
	    System.err.println("Exception:" +e);
	}
	catch(NotBoundException e){
	    System.err.println("Exception:" +e);
	}
	catch(MalformedURLException e){
	    System.err.println("Exception:" +e);
	}
    }
}
