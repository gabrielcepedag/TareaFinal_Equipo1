package logico;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

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
				//broadcast(factura);
				System.out.println(factura);
						//Aqui se debe guardar ese String como txt en carpeta respaldo.
				Date date = new Date();
				FileOutputStream facturaRespaldo = new FileOutputStream("src/Respaldo/Factura-"+new SimpleDateFormat("dd-MM-yyyy").format(date));
				//facturaRespaldo.createNewFile();
				escritura = null;
				
				try{
				    //escritura = new FileWriter(facturaRespaldo);
				    //escritura.write(factura);
				    byte[] strToBytes = factura.getBytes();
				    facturaRespaldo.write(strToBytes);
				    facturaRespaldo.close();
				   //escritura.close();
				    /*for (int i = 0; i < factura.length(); i++) {
				    	escritura.write(factura.charAt(i));
			            escritura.close();
				    }*/
				  
				}catch ( IOException e){
					e.printStackTrace();
				}				
			}catch(IOException ioe){
				Servidor.facturas.removeElement(this);
				System.out.println(nsfd.getInetAddress()+" se ha desconectado.");
				//broadcast(nsfd.getInetAddress()+"> se ha desconectado");
				break;
			}
		}
	}
	
	/*
	public void broadcast(String mensaje){
		synchronized (Servidor.facturas){
			Enumeration e = Servidor.facturas.elements();
		    while (e.hasMoreElements()){
		    	Flujo f = (Flujo) e.nextElement();
		    	try{
		    		synchronized(f.escritura){
		    		f.escritura.writeUTF(mensaje);
		    		f.escritura.flush();
		    		}
			    }
				catch(IOException ioe){
					System.out.println("Error: "+ioe);
				}
		    }
		}
	}
	*/
}
