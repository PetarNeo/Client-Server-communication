package rs.ac.bg.etf.kdp.neo.rmi;

import java.rmi.*;

import rs.ac.bg.etf.kdp.neo.*;

public class ClientRMIMessageBox<T> implements MessageBox<T> {
	//korisnik ima isto sanduce kao i pre
	
	
	RMIMessageBox<T> buffer;
	
	@SuppressWarnings("unchecked")
	public ClientRMIMessageBox(String server, int port) {
		try {
			//postavljanje rmi privilegije
			System.setSecurityManager(new RMISecurityManager());
			
			//url string
			String urlString = "rmi://" + server + ":" + port + "/validator";
			
			//dovlacenje udaljene reference
		
			Object obj = Naming.lookup(urlString);
			buffer = (RMIMessageBox<T>) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void put(Message<T> m, Priority priority, long timeToLive) {
		try {
			buffer.put(m, priority, timeToLive);
		} catch (RemoteException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public Message<T> get(long timeToWait, Status status) {
		try {
			return buffer.get(timeToWait, status);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
