package rs.ac.bg.etf.kdp.neo.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import rs.ac.bg.etf.kdp.neo.*;

public class ClientMessageBox<T> implements MessageBox<T> {
	
	String host;
	int port;
	
	class IO{
		Socket server;
		ObjectInputStream ois;
		ObjectOutputStream oos;
	}
	
	public ClientMessageBox(int port ,String host) {
		this.host = host;
		this.port = port;
	}
	
	public IO connect() {
		try {
			IO t = new IO();
			t.server = new Socket(host,port);
			t.oos = new ObjectOutputStream(t.server.getOutputStream());
			t.ois = new ObjectInputStream(t.server.getInputStream());
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void disconnect(IO t) {
		try {
			t.server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void put(Message<T> m, Priority priority, long timeToLive) {
		try {
			IO t = connect();
			t.oos.writeObject("PUT");
			t.oos.writeObject(m);
			disconnect(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Message<T> get(long timeToWait, Status status) {
		try {
			IO t = connect();
			t.oos.writeObject("GET");
			Message<T> m = (Message<T>) t.ois.readObject();
			//ako se sa serverske strane nadje write object
			//sa kliejntse mora read object
			disconnect(t);
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
