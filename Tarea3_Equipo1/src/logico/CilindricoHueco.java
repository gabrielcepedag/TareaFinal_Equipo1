package logico;

import java.io.Serializable;

public class CilindricoHueco extends Cilindrico implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int radioInterior;
	
	public CilindricoHueco(String id, String nombre, float precioBase, float precioUnitario, int radioCilindrico, int longitud, int radioInterior) {
		super(id, nombre, precioBase, precioUnitario, radioCilindrico, longitud);
		this.radioInterior = radioInterior;
	}

	public int getRadioInterior() {
		return radioInterior;
	}

	public void setRadioInterior(int radioInterior) {
		this.radioInterior = radioInterior;
	}
	
	public float volumen() {
		return longitud * areaBase();
	}

	public float areaBase() {
		return (int)(Math.PI * (Math.pow(radioCilindrico, 2)-Math.pow(radioInterior, 2)));
	}
}
