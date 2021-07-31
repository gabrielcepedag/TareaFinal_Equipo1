package logico;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Flujo extends Thread{
	Socket nsfd = null;
	DataInputStream lectura;
	
	public Flujo(Socket socket) {
		nsfd = socket;
		try {
			lectura = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()));
		} catch (Exception ioe) {
			System.out.println("IOException(Flujo): "+ioe);
		}
	}
	
	public void run() {
		
		while (true) {
			try {
				String factura = lectura.readUTF();
				System.out.println(factura);
				
			} catch (Exception ioe) {
				System.out.println(nsfd.getInetAddress() + " se ha desconectado.");
				break;
			}
		}
	}

}
