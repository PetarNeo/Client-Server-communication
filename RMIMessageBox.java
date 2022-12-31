package rs.ac.bg.etf.kdp.neo.rmi;

import java.rmi.*;

import rs.ac.bg.etf.kdp.neo.*;

public interface RMIMessageBox<T> extends Remote{
	
	public void put(Message<T> m, Priority priority,long timeToLive) throws RemoteException;
	
	public Message<T> get(long timeToWait,Status status) throws RemoteException;
}
