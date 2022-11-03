package floristeria;

public class Flor extends Producto{

	@Override
	public String toString() {
		return "Producto id: " + super.getId() + ", precio: " + super.getPrecio() + " euros, " + " tipo de producto: "
				+ "" + getClass().getSimpleName() + ", color: " + this.color + "\n";
	}

	private String color;

	public Flor(double precio, String color) {
		super(precio);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	
	
	
	

}
