package floristeria;

public class Decoracion extends Producto {

	private String material;

	public Decoracion(double precio, String material) {
		super(precio);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		if (material.equalsIgnoreCase("madera") || material.equalsIgnoreCase("plastico")) {
			this.material = material;
		} else {
			System.out.println("Por favor, elige entre dos tipos de material: madera o plastico");
		}
	}

	@Override
	public String toString() {
		return "Producto id: " + super.getId() + ", precio: " + super.getPrecio() + " euros, " + " tipo de producto: "
				+ "" + getClass().getSimpleName() + ", material: " + this.material + "\n";
	}
}
