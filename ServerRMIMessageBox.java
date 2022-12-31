package rs.ac.bg.etf.kdp.neo.rmi;

import java.rmi.*;
import java.rmi.server.*;

import rs.ac.bg.etf.kdp.neo.*;

//Serverska strana postanskog sanduceta
public class ServerRMIMessageBox<T> extends UnicastRemoteObject implements RMIMessageBox<T> {

	private static final long serialVersionUID = 1L;

	MessageBox<T> buffer;
	
	protected ServerRMIMessageBox(MessageBox<T> buffer) throws RemoteException {
		super();
		this.buffer = buffer; 
	}


	@Override
	public void put(Message<T> m, Priority priority, long timeToLive) throws RemoteException {
		buffer.put(m, priority, timeToLive);
	}

	@Override
	public Message<T> get(long timeToWait, Status status) throws RemoteException {
		return buffer.get(timeToWait, status);
	}

}
