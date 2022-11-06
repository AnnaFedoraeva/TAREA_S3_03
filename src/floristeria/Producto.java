package floristeria;

public abstract class Producto {

	private static int idProducto = 0;
	protected final int id;
	private final double precio;

	public Producto(double precio) {
		super();
		this.id = idProducto;
		idProducto++;
		this.precio = precio;

	}

	public int getId() {
		return id;
	}

	public double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Producto id: " + id + ", precio: " + precio + " euros, " + " tipo de producto: " + ""
				+ getClass().getSimpleName() + "\n";
	}

}
