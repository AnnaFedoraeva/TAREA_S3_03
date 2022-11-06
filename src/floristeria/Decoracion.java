package floristeria;

public class Decoracion extends Producto {

    private final String material;

    public Decoracion(double precio, String material) {
        super(precio);
        this.material = material;
    }

    @Override
    public String toString() {
        return "Producto id: " + super.getId() + ", precio: " + super.getPrecio() + " euros, " + " tipo de producto: "
                + "" + getClass().getSimpleName() + ", material: " + this.material + "\n";
    }
}
