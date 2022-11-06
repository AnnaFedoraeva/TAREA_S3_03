package floristeria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private static int idTicket = 0;
    private final int id;
    private final List<Producto> productosVendidos = new ArrayList<>();

    public Ticket() {
        this.id = idTicket;
        idTicket++;

    }

    public double precioTotal() {
        return productosVendidos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public LocalDateTime getFecha() {
        return LocalDateTime.now();
    }

    public void addEnTicket(Producto p) {
        this.productosVendidos.add(p);
    }

    public String listarProductos() {
        StringBuilder lista = new StringBuilder("Lista de productos: " + " \n");
        for (Producto p : productosVendidos) {
            lista.append(p.toString()).append(" \n");
        }
        return lista.toString();

    }

    @Override
    public String toString() {
        return "Ticket id: " + id + " \n" + "Precio Total: " + precioTotal() + " euros. " + " \n" + listarProductos()
                + " \n" + "fecha: " + getFecha() + " \n";
    }
}
