package logico;

import java.io.Serializable;

public abstract class Queso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String nombre;
	protected float precioBase;
	protected float precioUnitario;
	protected boolean vendido;
	protected int cantEjemplares = 0;
	public static int numQueso = 1;
	
	public Queso(String id, String nombre, float precioBase, float precioUnitario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.precioUnitario = precioUnitario;
		this.cantEjemplares++;
		this.vendido = false;
		numQueso++;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public float getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}
	
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public float precioTotal() {
		return (precioBase + precioUnitario * volumen());
	}
	
	public void setCantEjemplares(int cantEjemplares) {
		if(cantEjemplares <= 0) {
			vendido = true;
			this.cantEjemplares = 0;
		}else if(cantEjemplares > 0){
			vendido = false;
			this.cantEjemplares++;
		}
	}
	public boolean getVendido() {
		return vendido;
	}

	public void setVendido(boolean estado) {
		this.vendido = estado;
	}

	public int getCantEjemplares() {
		return cantEjemplares;
	}

	public abstract float volumen();
}
