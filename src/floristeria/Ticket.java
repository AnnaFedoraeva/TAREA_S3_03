package floristeria;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {

	private int id;
	private static int idTicket = 0;
	private double precioTotal;
	private List<Producto> productosVendidos = new ArrayList<>();
	private LocalDateTime fecha;

	public Ticket(List<Producto> productosVendidos) {
		super();
		this.id = idTicket;
		idTicket++;
		this.precioTotal = productosVendidos.stream().mapToDouble(Producto::getPrecio).sum();
		this.productosVendidos = new ArrayList<>();
		this.fecha = LocalDateTime.now();
	}

	public Ticket() {
		this.id = idTicket;
		idTicket++;
		this.precioTotal = productosVendidos.stream().mapToDouble(Producto::getPrecio).sum();
		this.fecha = LocalDateTime.now();

	}

	public double precioTotal() {
		double precio = productosVendidos.stream().mapToDouble(Producto::getPrecio).sum();
		return precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Producto> getProductosVendidos() {
		return productosVendidos;
	}

	public void setProductosVendidos(List<Producto> productosVendidos) {
		this.productosVendidos = productosVendidos;
	}

	public LocalDateTime getFecha() {
		return LocalDateTime.now();
	}

	public void addEnTicket(Producto p) {
		this.productosVendidos.add(p);
	}

	public String listarProductos() {
		String lista = "Lista de productos: " + " \n";
		for (Producto p : productosVendidos) {
			lista += p.toString() + " \n";
		}
		return lista;

	}

	@Override
	public String toString() {
		return "Ticket id: " + id + " \n" + "Precio Total: " + precioTotal() + " euros. " + " \n" + listarProductos()
				+ " \n" + "fecha: " + getFecha() + " \n";
	}
}
