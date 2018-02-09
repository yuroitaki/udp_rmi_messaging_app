/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import common.MessageInfo;

public class UDPServer {

    private static final int PACKET_SIZE = 512;
    private DatagramSocket recvSoc;
    private int totalMessages = -1;
    private ArrayList<Integer> receivedMessages;
    private int oriTotalMessage = 0;

    private void run() {
	byte[] pacData = new byte[PACKET_SIZE];
	int pacSize = pacData.length;
	DatagramPacket pac = new DatagramPacket(pacData,pacSize);

	// TO-DO: Receive the messages and process them by calling processMessage(...).
	//        Use a timeout (e.g. 30 secs) to ensure the program doesn't block forever

	try{
	    recvSoc.setSoTimeout(30000);
	    
	    while(true){
		recvSoc.receive(pac);
		byte[] packetData = pac.getData();
		processMessage(packetData);
	    }
	}
	catch(SocketTimeoutException e){	    
	    System.out.println("Exception:" + e);
	    if(totalMessages!=oriTotalMessage-1){
		printLogReceipt(false);
	    }
    	}
	catch(IOException e){
	    System.err.println("Exception:" + e);
	}
    }
    
    public void processMessage(byte[] data) {

	MessageInfo msg = null;
	int messageCode = 0;
	
	// TO-DO: Use the data to construct a new MessageInfo object
	try{
	    ByteArrayInputStream byteInStream = new ByteArrayInputStream(data);
	    ObjectInputStream objInStream = new ObjectInputStream(byteInStream);
	    msg = (MessageInfo) objInStream.readObject();
	    oriTotalMessage = msg.totalMessages;
	    messageCode = msg.messageNum;
	}
	catch(IOException e){
	    System.err.println("Exception:" + e);
	}catch(ClassNotFoundException e){
	    System.err.println("Exception:" + e);
	}
	// TO-DO: On receipt of first message, initialise the receive buffer

	totalMessages++;
	
	if(totalMessages==0){
	    receivedMessages = new ArrayList<Integer>();
	}
	// TO-DO: Log receipt of the message

	receivedMessages.add(messageCode);
		
	// TO-DO: If this is the last expected message, then identify
	//        any missing messages
	
	if(totalMessages==oriTotalMessage-1){
	    printLogReceipt(true);
	}
    }

    private void printLogReceipt(boolean msg_stat){

	System.out.println("Number of messages sent = " + oriTotalMessage);
      	System.out.println("Number of messages received = " + receivedMessages.size());
      	
	if(!msg_stat){
	    ArrayList<Integer> lostMsg = new ArrayList<Integer>();
	    
	    for(int i=0; i< oriTotalMessage; i++){
		if(!findMsgCode(i+1)){
		    lostMsg.add(i+1);
		}
	    }
	    System.out.println("Number of messages lost = " + lostMsg.size() + '\n');
	    System.out.println("Messages lost = " + lostMsg.toString().replace("[","").replace("]",""));
	}
	System.exit(0);
    }

    private boolean findMsgCode(int msg){

	for(int i=0; i< receivedMessages.size(); i++){
	    if(msg==receivedMessages.get(i)){
		return true;
	    }
	}
	return false;
    }
    
    public UDPServer(int rp) {
	// TO-DO: Initialise UDP socket for receiving data
	try{
	    recvSoc = new DatagramSocket(rp);
	}
	catch(SocketException e){
	    System.err.println("Exception:" + e);
	}
	// Done Initialisation
	System.out.println("UDPServer ready");
    }

    public static void main(String args[]) {
	int recvPort;
	
	// Get the parameters from command line
	if (args.length < 1) {
	    System.err.println("Arguments required: recv port");
	    System.exit(-1);
	}
	recvPort = Integer.parseInt(args[0]);

	// TO-DO: Construct Server object and start it by calling run().

	UDPServer server = new UDPServer(recvPort);
	server.run();
    }

}
