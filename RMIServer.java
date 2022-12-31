package rs.ac.bg.etf.kdp.neo.rmi;

import java.rmi.*;
import java.rmi.registry.*;

import rs.ac.bg.etf.kdp.neo.*;

public class RMIServer {

	@SuppressWarnings("removal")
	public static void main(String[] args) {
		
		try {
			//smemo da pustimo nekog da prostupi nasoj virtuelnoj masini
			//rmi privilegija
			System.setSecurityManager(new RMISecurityManager());
			
			//podici registar u koji upusiujemo sta nudimo ostatku sveta
			String server = "192.168.1.7";
			int port = 4002;
			LocateRegistry.createRegistry(port);
			
			//pravljenje objekta
			MessageBox<Object> b = new ListMessageBox<Object>();
			RMIMessageBox<Object> buffer = new ServerRMIMessageBox<>(b);
			
			//pravljene stringa pomocu kog spercificramo sta je nas objekat
			String urlString = "rmi://" + server + ":" + port + "/validator";
			
			//povezivanje objekta sa ostatkom sveta
			Naming.rebind(urlString, buffer);
			//sada smo napravili da je nas objekat vidljiv;
			
			System.out.println("Server started " + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
