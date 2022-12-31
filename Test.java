package rs.ac.bg.etf.kdp.neo;

import rs.ac.bg.etf.kdp.neo.gui.*;
import rs.ac.bg.etf.kdp.neo.net.*;
import rs.ac.bg.etf.kdp.neo.rmi.ClientRMIMessageBox;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MessageBox<String> buffer = new ClientRMIMessageBox<String>(args[0],Integer.parseInt(args[1]));
		Put put = new Put(buffer);
		Get get = new Get(buffer);
	}

}
