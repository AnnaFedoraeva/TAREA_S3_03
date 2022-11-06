package floristeria;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int opcionMenu;

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
			System.out.println("""
					
					Indique qué quiere hacer
					1. Añadir producto
					2. Eliminar producto
					3. Mostrar las cantidades de stock
					4. Mostrar valor total del stock
					5. Añadir productos a un ticket de compra
					6. Mostrar tickets de compra antiguos
					7. Mostrar valor total de todas las ventas
					0. Salir""");

			opcionMenu = sc.nextInt();

			switch (opcionMenu) {
			case 1 -> anadirEnStock(floristeria1);
			case 2 -> eliminarEnStock(floristeria1);
			case 3 -> {
				System.out.println(verStock(floristeria1));
				System.out.println(floristeria1.getStock());
			}
			case 4 -> verValorStock(floristeria1);
			case 5 -> anadirEnTicket(floristeria1, tickets);
			case 6 -> listaTickets(tickets);
			case 7 -> System.out.println(ventasGanancias(tickets));
			case 0 -> System.out.println("Gracias por utilizar la aplicación.");
			}

		} while (opcionMenu != 0);

	}

	public static void anadirEnStock(Floristeria floristeria1) {
		int producto;
		double altura;
		double precioA;
		double precioF;
		double precioD;
		String material = "";

		System.out.println("""

				Elige el producto:
				1. Árbol
				2. Flor
				3. Decoración""");
		producto = sc.nextInt();

		switch (producto) {
		case 1 -> {
			System.out.print("\nIntroduce la altura del arbol: ");
			altura = sc.nextDouble();
			System.out.print("Introduce el precio del arbol: ");
			precioA = sc.nextDouble();
			Arbol arbol2 = new Arbol(precioA, altura);
			floristeria1.addArbol(arbol2);
			GestionArchivo.FileWriterProductos(floristeria1, true);
			System.out.print("\nSe ha añadido el producto correctamente\n");
			System.out.print("***********************\n");
		}

		case 2 -> {
			System.out.print("\nIntroduce el color de la flor: ");
			String color = sc.next();
			System.out.print("Introduce el precio de la flor: ");
			precioF = sc.nextDouble();
			Flor flor2 = new Flor(precioF, color);
			floristeria1.addFlor(flor2);
			GestionArchivo.FileWriterProductos(floristeria1, true);
			System.out.print("\nSe ha añadido el producto correctamente\n");
			System.out.print("***********************\n");
		}

		case 3 -> {
			System.out.print("\nIntroduce el precio de la decoración: ");
			precioD = sc.nextDouble();
			boolean out = false;
			while (!out) {
				System.out.println("""
						Elige material de la decoración:
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
			System.out.print("\nSe ha añadido el producto correctamente\n");
			System.out.print("***********************\n");
		}
		}
	}

	public static void eliminarEnStock(Floristeria floristeria1) {
		int IdProducto;
		int p;

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
		return floristeria1.countProductos();
	}

	public static void verValorStock(Floristeria floristeria1) {
		System.out.println("\nEl valor total del Stock de la tienda " + floristeria1.getNombre() + " es "
				+ floristeria1.valorTotal() + " euros.");
		System.out.println("************************\n");
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
