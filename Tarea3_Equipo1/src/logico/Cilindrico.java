package logico;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cilindrico extends Queso implements Serializable{
	protected int radioCilindrico;
	protected int longitud;
	
	public Cilindrico(String id, String nombre, float precioBase, float precioUnitario, int radioCilindrico, int longitud) {
		super(id, nombre, precioBase, precioUnitario);
		this.radioCilindrico = radioCilindrico;
		this.longitud = longitud;
	}

	public int getRadioCilindrico() {
		return radioCilindrico;
	}

	public void setRadioCilindrico(int radioCilindrico) {
		this.radioCilindrico = radioCilindrico;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	@Override
	public float volumen() {
		return longitud*areaBase();
	}
	
	public float areaBase() {
		return (int)(Math.PI* Math.pow(radioCilindrico, 2));
	}
}
