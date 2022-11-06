package floristeria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class GestionArchivo {

    private static final String pathStock = "./floristeria_stock.txt";
    private static final String pathTicket = "./floristeria_tickets.txt";


    public static void FileWriterProductos(Floristeria floristeria1, boolean append) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(pathStock, append));
            for (int i = 0; i < floristeria1.getProductos().size(); i++) {
                writer.write(floristeria1.getProductos().get(i).toString());
                writer.newLine();
            }
            writer.flush();
            writer.close();
            System.out.println("Data Entered in to the file successfully");
        } catch (IOException e) {
            System.out.println("Error saving data to the file");
        }
    }

    public static void FileWriterTickets(ArrayList<Ticket> tickets, boolean append) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(pathTicket, append));
            for (Ticket ticket : tickets) {
                writer.write(ticket.toString());
                writer.newLine();
            }
            writer.flush();
            writer.close();
            System.out.println("Data Entered in to the file successfully");
        } catch (IOException e) {
            System.out.println("Error saving data to the file");
        }


    }

}

	