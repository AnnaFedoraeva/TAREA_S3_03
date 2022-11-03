package floristeria;

import java.util.ArrayList;
import java.util.List;

public class Floristeria {

	private String nombre;
	private ArrayList<Producto> productos = new ArrayList<>();

	public Floristeria(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Floristeria(String nombre, ArrayList<Producto> productos) {
		super();
		this.nombre = nombre;
		this.productos = new ArrayList<>();
	}

	public void addArbol(Arbol arbol) {
		productos.add(arbol);

	}

	public void addFlor(Flor flor) {
		productos.add(flor);
	}

	public void addDeco(Decoración deco) {
		productos.add(deco);
	}

	public void remove(int id) {
		productos.remove(id);
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public int buscarProducto(int id) {
		int i = -1;
		for (Producto p : productos) {
			if (p.getId() == id) {
				i = productos.indexOf(p);
			}
		}
		return i;

	}

	public String getStock() {

		List<Arbol> arboles = new ArrayList<>();
		List<Flor> flores = new ArrayList<>();
		List<Decoración> decoraciónes = new ArrayList<>();

		for (Producto p : productos) {

			if (p instanceof Arbol)
				arboles.add((Arbol) p);
			if (p instanceof Flor)
				flores.add((Flor) p);
			if (p instanceof Decoración)
				decoraciónes.add((Decoración) p);

		}

		String result = "** Floristeria " + nombre + " **\n ARBOLES: \n";

		for (Arbol a : arboles) {
			result += a.toString();
		}

		result += "\n FLORES: \n";

		for (Flor f : flores) {
			result += f.toString();
		}

		result += "\n DECORACIONES: \n";

		for (Decoración d : decoraciónes) {
			result += d.toString();
		}

		return result;

	}


	public String cantidadStock() {

		List<Arbol> arboles = new ArrayList<>();
		List<Flor> flores = new ArrayList<>();
		List<Decoración> decoraciónes = new ArrayList<>();

		for (Producto p : productos) {

			if (p instanceof Arbol)
				arboles.add((Arbol) p);
			if (p instanceof Flor)
				flores.add((Flor) p);
			if (p instanceof Decoración)
				decoraciónes.add((Decoración) p);

		}

		String cantidades = "Floristeria " + nombre + " tiene: \n" + "arboles: " + arboles.size() + "\n" + "flores: "
				+ flores.size() + "\n" + "decoración: " + decoraciónes.size() + "\n";

		return cantidades;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double valorTotal() {
		double valorTotal = productos.stream().mapToDouble(Producto::getPrecio).sum();
		return valorTotal;
	}

	@Override
	public String toString() {
		return "Floristeria [nombre=" + nombre + ", productos=" + productos + "]";
	}

}
