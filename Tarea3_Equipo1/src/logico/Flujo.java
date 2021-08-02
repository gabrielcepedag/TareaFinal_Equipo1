package logico;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flujo extends Thread{
	Socket nsfd = null;
	DataInputStream lectura;
	FileWriter escritura;
	
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
			try{
				String factura = lectura.readUTF();
				System.out.println(factura);

				Date date = new Date();
				FileOutputStream facturaRespaldo = new FileOutputStream("src/Respaldo/Factura-"+new SimpleDateFormat("dd-MM-yyyy").format(date));
				escritura = null;
				
				try{ 
					byte[] strToBytes = factura.getBytes();
				    facturaRespaldo.write(strToBytes);
				    facturaRespaldo.close();
				  
				}catch ( IOException e){
					System.out.println("....");
					e.printStackTrace();
				}				
			}catch(IOException ioe){
				Servidor.facturas.removeElement(this);
				System.out.println(nsfd.getInetAddress()+" se ha desconectado.");
				break;
			}
		}
	}
}
