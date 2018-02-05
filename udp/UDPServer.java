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

import common.MessageInfo;

public class UDPServer {

    private DatagramSocket recvSoc;
    private int totalMessages = -1;
    private int[] receivedMessages;
    private boolean close;

    private void run() {
	int				pacSize;
	byte[]			pacData;
	DatagramPacket 	pac;

	// TO-DO: Receive the messages and process them by calling processMessage(...).
	//        Use a timeout (e.g. 30 secs) to ensure the program doesn't block forever

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

	// Done Initialisation
	System.out.println("UDPServer ready");
    }

    public static void main(String args[]) {
	int	recvPort;

	// Get the parameters from command line
	if (args.length < 1) {
	    System.err.println("Arguments required: recv port");
	    System.exit(-1);
	}
	recvPort = Integer.parseInt(args[0]);

	// TO-DO: Construct Server object and start it by calling run().
    }

}
