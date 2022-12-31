package rs.ac.bg.etf.kdp.neo.net;

import java.io.*;
import java.net.*;

import rs.ac.bg.etf.kdp.neo.*;

public class WorkingThread extends Thread {

	MessageBox<Object> buffer;

	Socket client;
	ObjectInputStream ois;
	ObjectOutputStream oos;

	public WorkingThread(Socket client, MessageBox<Object> buffer) {
		this.client = client;
		this.buffer = buffer;
		try {
			ois = new ObjectInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void run() {
		try {
				String command = (String) ois.readObject();
				if ("PUT".equals(command)) {
					Message<Object> m = (Message<Object>) ois.readObject();
					buffer.put(m, null, 0);
				} else if ("GET".equals(command)) {
					Message<Object> m = buffer.get(0, null);
					oos.writeObject(m);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
