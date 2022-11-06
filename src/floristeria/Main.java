package floristeria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		int opcionMenu = 0;

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

		do {
			System.out.println("Indique qué quiere hacer\n" + "1. Añadir producto\n" + "2. Eliminar producto\n"
					+ "3. Mostrar las cantidades de stock\n" + "4. Mostrar valor total del stock\n"
					+ "5. Añadir productos a un ticket de compra\n" + "6. Mostrar tickets de compra antiguos\n"
					+ "7. Mostrar valor total de todas las ventas\n" + "0. Salir");

			opcionMenu = sc.nextInt();

			switch (opcionMenu) {
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
				verValorStock(floristeria1);
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
				System.out.println("Gracias por utilizar la aplicación.");
				break;
			}

		} while (opcionMenu != 0);

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

	public static void eliminarEnStock(Floristeria floristeria1) throws IOException {
		int IdProducto = 0;
		int p = 0;

		System.out.println(floristeria1.getStock());
		System.out.print("Introduce el id del producto que quieres eliminar: ");
		IdProducto = sc.nextInt();
		p = floristeria1.buscarProducto(IdProducto);
		floristeria1.remove(p);
		System.out.println("El producto ha sido eliminado");
		System.out.println("************************\n");
		GestionArchivo.FileWriterProductos(floristeria1, false);

	}

	public static String verStock(Floristeria floristeria1) {
		return floristeria1.cantidadStock();
	}

	public static void verValorStock(Floristeria floristeria1) {
		System.out.println("\nEl valor total del Stock de la tienda " + floristeria1.getNombre() + " es "
				+ floristeria1.valorTotal() + " euros.");
		System.out.println("************************\n");
	}

	public static Ticket crearTicket() {

		Ticket ticket = new Ticket();

		return ticket;
	}

	public static void anadirEnTicket(Floristeria floristeria1, ArrayList<Ticket> tickets) throws IOException {
		int opcion = 0;
		int idProduct = 0;
		int idTicket = 0;
		int seguir = 0;
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
						System.out.println("\n¿Quieres añadir otro producto al ticket?" + "\n1. Sí" + "\n2. No");
						seguir = sc.nextInt();
					} while (seguir != 2);
					break;

				case 2:
					// Mostramos los tickets que tenemos
					tickets.stream().forEach(System.out::println);
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
						System.out.println("\n¿Quieres añadir otro producto al ticket?" + "\n1. Sí" + "\n2. No");
						seguir = sc.nextInt();
					} while (seguir != 2);
					break;
				}

			} while (opcion != 0);
		} catch (InputMismatchException e) {
			System.out.println("ERROR. Elije una opción correcta");
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
