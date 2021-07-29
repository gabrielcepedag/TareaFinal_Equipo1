package logico;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.plaf.InputMapUIResource;

public class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Cliente miCliente;
	private ArrayList<Queso> misQuesos;
	public static int numFactura = 1;
	
	public Factura(String id, Cliente miCliente) {
		super();
		this.id = id;
		this.miCliente = miCliente;
		this.misQuesos = new ArrayList<Queso>();
		numFactura++;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cliente getMiCliente() {
		return miCliente;
	}

	public void setMiCliente(Cliente miCliente) {
		this.miCliente = miCliente;
	}

	public ArrayList<Queso> getMisQuesos() {
		return misQuesos;
	}

	public void setMisQuesos(ArrayList<Queso> misQuesos) {
		this.misQuesos = misQuesos;
	}

	public void agregarQueso(Queso queso) {
		misQuesos.add(queso);
		queso.setVendido(true);
	}
	
	public float precioFactura() {
		float suma = 0;
		for (Queso queso : misQuesos) {
			suma += queso.precioTotal();
		}
		return suma;
	}
}
