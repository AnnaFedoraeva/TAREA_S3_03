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

	public void addDeco(Decoracion deco) {
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
		List<Decoracion> decoraciones = new ArrayList<>();

		for (Producto p : productos) {

			if (p instanceof Arbol)
				arboles.add((Arbol) p);
			if (p instanceof Flor)
				flores.add((Flor) p);
			if (p instanceof Decoracion)
				decoraciones.add((Decoracion) p);

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

		for (Decoracion d : decoraciones) {
			result += d.toString();
		}

		return result;

	}

	public String countProductos() {
		int countArbol = 0;
		int countFlores = 0;
		int countDecoracion = 0;

		for (Producto producto : productos) {
			if (producto instanceof Arbol) {
				countArbol++;
			}
			else if (producto instanceof Flor) {
				countFlores++;
			}
			else {
				countDecoracion++;
			}
		}

		return "Floristeria " + nombre + " tiene: \n" + "arboles: " + countArbol + "\n" + "flores: "
				+ countFlores + "\n" + "decoraci�n: " + countDecoracion + "\n";
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
