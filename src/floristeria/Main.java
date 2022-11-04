package floristeria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		// Crear floristeria:
		Floristeria floristeria1 = new Floristeria("Flower");

		// Crear stock:

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

		// Crear tickets:
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
			case 1:
				anadirEnStock(floristeria1);
				break;
			case 2:
				eliminarEnStock(floristeria1);
				break;
			case 3:
				System.out.println(verStock(floristeria1));
				System.out.println(floristeria1.getStock());
				break;
			case 4:
				System.out.println(verValorStock(floristeria1));
				break;
			case 5:
				anadirEnTicket(floristeria1, tickets);
				break;
			case 6:
				listaTickets(tickets);
				break;
			case 7:
				System.out.println(ventasGanancias(tickets));
				break;
			case 0:
				System.out.println("Gracias por utilizar la aplicaci�n.");
				exit = true;
				break;
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

	public static String anadirEnStock(Floristeria floristeria1) throws IOException {
		String string = "";
		System.out.println("Elige el producto:" + "\n" + "1. arbol" + "\n" + "2. flor" + "\n" + "3. decoraci�n");
		int producto = sc.nextInt();
		if (producto == 1) {
			System.out.println("Introduce la altura del arbol:");
			double altura = sc.nextDouble();
			System.out.println("Introduce el precio del arbol:");
			double precioA = sc.nextDouble();
			Arbol arbol2 = new Arbol(precioA, altura);
			floristeria1.addArbol(arbol2);
			GestionArchivo.FileWriterProductos(floristeria1, true);
			string.equals("El producto est� a�adido correctamente.");
		} else if (producto == 2) {
			System.out.println("Introduce el color de la flor:");
			String color = sc.next();
			System.out.println("Introduce el precio de la flor:");
			double precioF = sc.nextDouble();
			Flor flor2 = new Flor(precioF, color);
			floristeria1.addFlor(flor2);
			string.equals("El producto est� a�adido correctamente.");
			GestionArchivo.FileWriterProductos(floristeria1, true);
		} else if (producto == 3) {
			System.out.println("Introduce el precio de la decoraci�n:");
			double precioD = sc.nextDouble();
			String material = "";
			boolean out = false;
			while (!out) {
				System.out.println("Elige material de la decoraci�n:" + "\n" + "1. plastico" + "\n" + "2. madera");
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
			string.equals("El producto est� a�adido correctamente.");
		}

		return string;

	}

	public static String eliminarEnStock(Floristeria floristeria1) throws IOException {
		String string = "";
		System.out.println(floristeria1.getStock());
		System.out.println("Introduce el id del producto que quieres eliminar:");
		int IdProducto = sc.nextInt();
		int p = floristeria1.buscarProducto(IdProducto);
		floristeria1.remove(p);
		string.equals("El producto ha sido eliminado");
		GestionArchivo.FileWriterProductos(floristeria1, false);
		return string;
	}

	public static String verStock(Floristeria floristeria1) {
		return floristeria1.cantidadStock();
	}

	public static String verValorStock(Floristeria floristeria1) {
		String string = "El valor total del Stock de la tienda " + floristeria1.getNombre() + " es "
				+ floristeria1.valorTotal() + " euros.";
		return string;
	}

	public static Ticket crearTicket() {

		Ticket ticket = new Ticket();

		return ticket;
	}

	public static void anadirEnTicket(Floristeria floristeria1, ArrayList<Ticket> tickets) throws IOException {
		int opcion = 0;
		int idProduct = 0;
		int idTicket = 0;
		int p = 0;

		try {
			do {
				System.out.println("\nIndica una de las siguientes opciones" + "\n1. Crear nuevo ticket"
						+ "\n2. Añadir producto a un ticket existente" + "\n0. Salir");
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					// Creamos el ticket
					Ticket ticket1 = crearTicket();
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
					break;

				case 2:
					// Mostramos los tickets que tenemos
					tickets.stream().forEach(System.out::println);
					System.out.println("Introduzca el id del ticket: ");
					idTicket = sc.nextInt();
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
					break;
				}

			} while (opcion != 0);
		} catch (InputMismatchException e) {
			System.out.println("Por favor, elige una de las opci�nes:\n");
		}
	}

	public static void listaTickets(ArrayList<Ticket> tickets) {
		tickets.stream().forEach(System.out::println);
	}

	public static double ventasGanancias(ArrayList<Ticket> tickets) {
		double suma = 0;
		for (Ticket t : tickets) {
			suma += t.precioTotal();
		}
		return suma;
	}

	public static int buscarTicket(int t, ArrayList<Ticket> tickets) {
		int i = -1;
		for (Ticket ticket : tickets) {
			if (ticket.getId() == t) {
				i = tickets.indexOf(ticket);
			}
		}
		return i;
	}

}
