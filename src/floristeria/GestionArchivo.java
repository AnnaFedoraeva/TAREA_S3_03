 package floristeria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public final class GestionArchivo {
	
	private static final String pathStock = "./floristeria_stock.txt";
	private static final String pathTicket = "./floristeria_tickets.txt";
	
	private GestionArchivo() {
		// TODO Auto-generated constructor stub 
        throw new java.lang.UnsupportedOperationException("Utility class and cannot be instantiated");
    }
	
	public static void FileWriterProductos(Floristeria floristeria1, boolean append) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(pathStock, append));
		for (int i = 0; i < floristeria1.getProductos().size(); i++) {
			writer.write(floristeria1.getProductos().get(i).toString());
			writer.newLine();
		}
		writer.flush();
		writer.close();
		System.out.println("Data Entered in to the file successfully");
	}

	public static void FileWriterTickets(ArrayList<Ticket> tickets, boolean append) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(pathTicket, append));
		for (int i = 0; i < tickets.size(); i++) {
			writer.write(tickets.get(i).toString());
			writer.newLine();
		}
		writer.flush();
		writer.close();
		System.out.println("Data Entered in to the file successfully");

	}

}

	