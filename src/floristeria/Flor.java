package floristeria;

public class Flor extends Producto {

	private final String color;

	public Flor(double precio, String color) {
		super(precio);
		this.color = color;
	}

	@Override
	public String toString() {
		return "Producto id: " + super.getId() + ", precio: " + super.getPrecio() + " euros, " + " tipo de producto: "
				+ "" + getClass().getSimpleName() + ", color: " + this.color + "\n";
	}

}
