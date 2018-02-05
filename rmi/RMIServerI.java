/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.*;

public interface RMIServerI extends Remote {
	public void receiveMessage(MessageInfo msg) throws RemoteException;
}
