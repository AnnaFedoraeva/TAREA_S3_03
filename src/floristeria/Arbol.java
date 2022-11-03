package floristeria;

public class Arbol extends Producto{


	private double altura;
	
	public Arbol(double precio, double altura) {
		super(precio);
		this.altura = altura;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	@Override
	public String toString() {
		return "Producto id: " + super.getId() + ", precio: " + super.getPrecio() + " euros, " + " tipo de producto: "
				+ "" + getClass().getSimpleName() + ", altura: " + this.altura + "\n";
	}
	
	
	
	
}
