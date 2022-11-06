package floristeria;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Creamos la floristeria
        Floristeria floristeria1 = new Floristeria("Flower");

        // Creamos stock inicial y lo añadimos
        Arbol arbol1 = new Arbol(40, 1.11);
        Arbol arbol2 = new Arbol(45, 1.29);
        Arbol arbol3 = new Arbol(49, 1.34);
        Flor flor = new Flor(12, "rosa");
        Decoracion deco = new Decoracion(13, "plastico");

        floristeria1.addArbol(arbol1);
        floristeria1.addArbol(arbol2);
        floristeria1.addArbol(arbol3);
        floristeria1.addFlor(flor);
        floristeria1.addDeco(deco);

        // Creamos tickets iniciales y los añadimos
        ArrayList<Ticket> tickets = new ArrayList<>();

        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();

        ticket1.addEnTicket(floristeria1.getProductos().get(2));
        ticket2.addEnTicket(floristeria1.getProductos().get(0));

        tickets.add(ticket1);
        tickets.add(ticket2);

        boolean exit = false;

        do {
            switch (menu()) {
                case 1 -> anadirEnStock(floristeria1);
                case 2 -> eliminarEnStock(floristeria1);
                case 3 -> {
                    System.out.println(verStock(floristeria1));
                    System.out.println(floristeria1.getStock());
                }
                case 4 -> System.out.println(verValorStock(floristeria1));
                case 5 -> anadirEnTicket(floristeria1, tickets);
                case 6 -> listaTickets(tickets);
                case 7 -> System.out.println(ventasGanancias(tickets));
                case 0 -> {
                    System.out.println("Gracias por utilizar la aplicaci�n.");
                    exit = true;
                }
            }
        } while (!exit);
    }

    public static byte menu() {
        byte option;
        final byte MINIMO = 0;
        final byte MAXIMO = 7;

        do {
            System.out.println("\nMEN� PRINCIPAL");
            System.out.println("1. A�adir producto.");
            System.out.println("2. Eliminar producto.");
            System.out.println("3. Mostrar el stock con cantidades");
            System.out.println("4. Mostrar el valor total de la floristeria");
            System.out.println("5. A�adir productos en un ticket de compra");
            System.out.println("6. Mostrar los tickets de compra antiguos.");
            System.out.println("7. Mostrar el valor de todas las ventas.");
            System.out.println("0. Salir de aplicacion.\n");
            option = sc.nextByte();
            if (option < MINIMO || option > MAXIMO) {
                System.out.println("Escoge opcion valida");
            }
        } while (option < MINIMO || option > MAXIMO);
        return option;
    }

    public static void anadirEnStock(Floristeria floristeria1) {

        System.out.println("""
                Elige el producto:
                1. arbol
                2. flor
                3. decoraci�n""");
        int producto = sc.nextInt();
        if (producto == 1) {
            System.out.println("Introduce la altura del arbol:");
            double altura = sc.nextDouble();
            System.out.println("Introduce el precio del arbol:");
            double precioA = sc.nextDouble();
            Arbol arbol2 = new Arbol(precioA, altura);
            floristeria1.addArbol(arbol2);
            GestionArchivo.FileWriterProductos(floristeria1, true);

        } else if (producto == 2) {
            System.out.println("Introduce el color de la flor:");
            String color = sc.next();
            System.out.println("Introduce el precio de la flor:");
            double precioF = sc.nextDouble();
            Flor flor2 = new Flor(precioF, color);
            floristeria1.addFlor(flor2);

            GestionArchivo.FileWriterProductos(floristeria1, true);
        } else if (producto == 3) {
            System.out.println("Introduce el precio de la decoraci�n:");
            double precioD = sc.nextDouble();
            String material = "";
            boolean out = false;
            while (!out) {
                System.out.println("""
                        Elige material de la decoraci�n:
                        1. plastico
                        2. madera""");
                int n = sc.nextInt();
                if (n == 1) {
                    material = "plastico";
                    out = true;
                } else if (n == 2) {
                    material = "madera";
                    out = true;
                } else {
                    System.out.println("Por favor, elige entre dos tipos de material: madera o plastico");
                }
            }
            Decoracion deco2 = new Decoracion(precioD, material);
            floristeria1.addDeco(deco2);
            GestionArchivo.FileWriterProductos(floristeria1, true);

        }

    }

    public static void eliminarEnStock(Floristeria floristeria1) {

        System.out.println(floristeria1.getStock());
        System.out.println("Introduce el id del producto que quieres eliminar:");
        int IdProducto = sc.nextInt();
        int p = floristeria1.buscarProducto(IdProducto);
        floristeria1.remove(p);

        GestionArchivo.FileWriterProductos(floristeria1, false);

    }

    public static String verStock(Floristeria floristeria1) {
        return floristeria1.countProductos();
    }

    public static String verValorStock(Floristeria floristeria1) {
        return "El valor total del Stock de la tienda " + floristeria1.getNombre() + " es "
                + floristeria1.valorTotal() + " euros.";
    }

    public static Ticket crearTicket() {

        return new Ticket();
    }

    public static void anadirEnTicket(Floristeria floristeria1, ArrayList<Ticket> tickets) {
        int opcion;
        int idProduct;
        int idTicket;
        int seguir;
        int p;

        try {
            do {
                System.out.println("""

                        Indica una de las siguientes opciones
                        1. Crear nuevo ticket
                        2. Añadir producto a un ticket existente
                        0. Salir""");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1 -> {
                        // Creamos el ticket
                        Ticket ticket1 = crearTicket();
                        do {
                            // Mostramos el stock para poder añadir al ticket
                            System.out.println(floristeria1.getStock());
                            System.out.print("Introduzca el id del producto que deseas añadir: ");
                            idProduct = sc.nextInt();
                            p = floristeria1.buscarProducto(idProduct);
                            ticket1.addEnTicket(floristeria1.getProductos().get(p));
                            // Añadimos el producto al ticket
                            tickets.add(ticket1);
                            // Eliminamos el producto del stock actual
                            floristeria1.remove(p);
                            // Eliminamos el producto del txt de productos
                            GestionArchivo.FileWriterProductos(floristeria1, false);
                            // Añadimos el ticket al txt de tickets
                            GestionArchivo.FileWriterTickets(tickets, true);
                            System.out.println("""

                                    ¿Quieres añadir otro producto al ticket?
                                    1. Sí
                                    2. No""");
                            seguir = sc.nextInt();
                        } while (seguir != 2);
                    }
                    case 2 -> {
                        // Mostramos los tickets que tenemos
                        tickets.forEach(System.out::println);
                        System.out.print("Introduzca el id del ticket: ");
                        idTicket = sc.nextInt();
                        do {
                            // Mostramos el stock para poder añadir al ticket
                            System.out.println(floristeria1.getStock());
                            System.out.print("Introduzca el id del producto que deseas añadir: ");
                            idProduct = sc.nextInt();
                            p = floristeria1.buscarProducto(idProduct);
                            // Accedemos al ticket y añadimos el producto
                            tickets.get(idTicket).addEnTicket(floristeria1.getProductos().get(p));
                            // Eliminamos el producto del stock actual
                            floristeria1.remove(p);
                            // Eliminamos el producto del txt de productos
                            GestionArchivo.FileWriterProductos(floristeria1, false);
                            // Añadimos el ticket al txt de tickets
                            GestionArchivo.FileWriterTickets(tickets, false);
                            System.out.println("""

                                    ¿Quieres añadir otro producto al ticket?
                                    1. Sí
                                    2. No""");
                            seguir = sc.nextInt();
                        } while (seguir != 2);
                    }
                }

            } while (opcion != 0);
        } catch (InputMismatchException e) {
            System.out.println("ERROR. Elije una opción correcta");
        }
    }

    public static void listaTickets(ArrayList<Ticket> tickets) {
        tickets.forEach(System.out::println);
    }

    public static double ventasGanancias(ArrayList<Ticket> tickets) {
        double suma = 0;
        for (Ticket t : tickets) {
            suma += t.precioTotal();
        }
        return suma;
    }

}
