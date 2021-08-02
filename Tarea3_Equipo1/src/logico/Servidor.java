package logico;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Servidor extends Thread{
	
	public static ServerSocket server;
	public static Socket socket;
	public static int puerto = 9000;
	public static Vector facturas = new Vector();

	public static void main (String args[])
	  {
		server = null;
	    try
	    {
	    	server = new ServerSocket(puerto);
	    	System.out.println("Servidor iniciado...");
	    }
	    catch (IOException ioe)
	    {
	      System.out.println("Comunicación rechazada."+ioe);
	      System.exit(1);
	    }

	    while (true)
	    {
	    	System.out.println("Esperando conexion...");
	      try
	      {
	        socket = server.accept();
	        System.out.println("Conexion aceptada de: "+socket.getInetAddress());
		    Flujo flujo = new Flujo(socket);
		    Thread t = new Thread(flujo);
	        t.start();
	      }
	      catch(IOException ioe)
	      {
	        System.out.println("Error: "+ioe);
	      }
	    }
	  }
}
