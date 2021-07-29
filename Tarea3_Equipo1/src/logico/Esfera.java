package logico;

import java.io.Serializable;

public class Esfera extends Queso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int radioEsfera;

	public Esfera(String id, String nombre, float precioBase, float precioUnitario, int radioEsfera) {
		super(id, nombre, precioBase, precioUnitario);
		this.radioEsfera = radioEsfera;
	}

	public int getRadioEsfera() {
		return radioEsfera;
	}

	public void setRadioEsfera(int radioEsfera) {
		this.radioEsfera = radioEsfera;
	}

	@Override
	public float volumen() {
		return (float) ((4/3) * Math.PI * Math.pow(radioEsfera, 3));
	}
}
