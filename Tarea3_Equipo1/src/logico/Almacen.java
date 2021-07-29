package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Almacen implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Queso> misQuesos;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Factura> misFacturas;
	private static Almacen almacen;

	public Almacen() {
		super();
		misQuesos = new ArrayList<Queso>();
		misClientes = new ArrayList<Cliente>();
		misFacturas = new ArrayList<Factura>();
	}
	
	public static Almacen getInstance() {
		if(almacen == null) {
			almacen = new Almacen();
		}
		return almacen;
	}
	
	public ArrayList<Queso> getMisQuesos() {
		return misQuesos;
	}
	public void setMisQuesos(ArrayList<Queso> misQuesos) {
		this.misQuesos = misQuesos;
	}
	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}
	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}
	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}
	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}
	
	public void agregarQueso(Queso queso) {
		misQuesos.add(queso);		
	}
	
	public void modificarQueso(int index, Queso queso) {
		misQuesos.set(index, queso);		
	}
	
	public void eliminarQueso(Queso queso) {
		misQuesos.remove(queso);
		
	}
	
	public void agregarCliente(Cliente cliente) {
		misClientes.add(cliente);		
	}
	
	public void modificarCliente(int index, Cliente cliente) {
		misClientes.set(index, cliente);
	}
	
	public void eliminarCliente(Cliente cliente) {
		misClientes.remove(cliente);
	}
	
	public void agregarFactura(Factura factura) {
		misFacturas.add(factura);		
	}
	
	public boolean crearFactura(Factura factura) {
		boolean realizado = false;
		if(factura != null && factura.getMisQuesos() != null){
			misFacturas.add(factura);
			realizado = true;
		}else {
			realizado = false;
		}
		return realizado;
	}
	
	public int cantQuesosCilindricos() {
		int cilindrico = 0;
		for (Queso queso : misQuesos) {
			if((queso instanceof Cilindrico) && !(queso  instanceof CilindricoHueco) && queso.getVendido() == false) {
				cilindrico++;
			}
		}
		return cilindrico;
	}
	public int cantQuesosCilindricosHueco() {
		int cilindricoH = 0;
		for (Queso queso : misQuesos) {
			if(queso instanceof CilindricoHueco && queso.getVendido() == false) {
				cilindricoH++;
			}
		}
		return cilindricoH;
	}
	public int cantQuesosRedondos() {
		int redondos = 0;
		for (Queso queso : misQuesos) {
			if(queso instanceof Esfera && queso.getVendido() == false) {
				redondos++;
			}
		}
		return redondos;
	}
	
	public float precioQuesoMayorVolumenFactura(String idFactura) {
		float precio = 0;
		Factura facturaAux = bucarFacturaById(idFactura);
		if(facturaAux != null) {
			float volumenMayor = facturaAux.getMisQuesos().get(0).volumen();
			precio = facturaAux.getMisQuesos().get(0).precioTotal();
			for (Queso queso : facturaAux.getMisQuesos()) {
				if (queso.volumen() > volumenMayor) {
					volumenMayor = queso.volumen();
					precio = queso.precioTotal();
				}
			}
		}
		return precio;
	}
	
	public Factura bucarFacturaById(String idFactura) {
		for (Factura factura : misFacturas) {
			if(factura.getId().equalsIgnoreCase(idFactura)) {
				return factura;
			}
		}
		return null;
	}

	public Queso buscarQuesoById(String codigo) {
		Queso aux = null;		
		for (Queso queso : misQuesos) {
			if(queso.getId().equalsIgnoreCase(codigo)) {
				aux = queso;
			}		
		}
		return aux;
	}

	public Cliente buscarClienteByCedula(String cedula) {
		Cliente aux = null;		
		for (Cliente cliente : misClientes) {
			if(cliente.getCedula().equalsIgnoreCase(cedula)) {
				aux = cliente;
			}		
		}
		return aux;
	}

	public int buscarIndexOfQueso(Queso selected) {
		int index = 0;
		for (Queso queso : misQuesos) {
			if(queso.equals(selected)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	public static Almacen getControl() {
		return almacen;
	}

	public static void setControl(Almacen control) {
		Almacen.almacen = control;
	}
}
