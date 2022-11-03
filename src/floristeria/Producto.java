package floristeria;

import java.io.Serializable;

public abstract class Producto {

	protected int id;
	private static int idProducto = 0;
	private double precio;

	public Producto(double precio) {
		super();
		this.id = idProducto;
		idProducto++;
		this.precio = precio;

	}

	@Override
	public String toString() {
		return "Producto id: " + id + ", precio: " + precio + " euros, " + " tipo de producto: " + ""
				+ getClass().getSimpleName() + "\n";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Producto() {
		super();
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
