package rs.ac.bg.etf.kdp.neo.net;

import java.io.IOException;
import java.net.*;

import rs.ac.bg.etf.kdp.neo.ListMessageBox;
import rs.ac.bg.etf.kdp.neo.MessageBox;

public class Server {

	public static void main(String[] args) {
		ServerSocket server = null;
		MessageBox<Object> buffer = new ListMessageBox<>();
		try {
			int port = Integer.parseInt(args[0]);
			server = new ServerSocket(port);
			
			while(true) {
				Socket client = server.accept();
				//napravili smo kliejenta
				processRequest(buffer, client);
				//nece server da prihvata komunikaciju nego cemo 
				//to predati nekom drugom da sve radi;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void processRequest(MessageBox<Object> buffer, Socket client) {
		new WorkingThread(client,buffer).start();
	}

}
