package floristeria;

import java.util.ArrayList;
import java.util.List;

public class Floristeria {

    private final String nombre;
    private final ArrayList<Producto> productos = new ArrayList<>();

    public Floristeria(String nombre) {
        super();
        this.nombre = nombre;
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

        StringBuilder result = new StringBuilder("** Floristeria " + nombre + " **\n ARBOLES: \n");

        for (Arbol a : arboles) {
            result.append(a.toString());
        }

        result.append("\n FLORES: \n");

        for (Flor f : flores) {
            result.append(f.toString());
        }

        result.append("\n DECORACIONES: \n");

        for (Decoracion d : decoraciones) {
            result.append(d.toString());
        }

        return result.toString();

    }

    public String countProductos() {
        int countArbol = 0;
        int countFlores = 0;
        int countDecoracion = 0;

        for (Producto producto : productos) {
            if (producto instanceof Arbol) {
                countArbol++;
            } else if (producto instanceof Flor) {
                countFlores++;
            } else {
                countDecoracion++;
            }
        }

        return "Floristeria " + nombre + " tiene: \n" + "arboles: " + countArbol + "\n" + "flores: "
                + countFlores + "\n" + "decoraci�n: " + countDecoracion + "\n";
    }

    public String getNombre() {
        return nombre;
    }

    public double valorTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    @Override
    public String toString() {
        return "Floristeria [nombre=" + nombre + ", productos=" + productos + "]";
    }

}
