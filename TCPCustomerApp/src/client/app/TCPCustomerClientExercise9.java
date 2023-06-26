package client.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import model.Customer;

public class TCPCustomerClientExercise9 {

    public static void main(String[] args) {
        try {
            System.out.println("\tExecuting TCPCustomerClientExercise9");

            // Server information
            int serverPortNo = 8018;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // 1. Connect to the remote machine
            Socket socket = new Socket(serverAddress, serverPortNo);

            // Create stream to send request
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            // 2. Send request to the server
            String requestType = "GET_ALL_CUSTOMERS";
            outputStream.writeObject(requestType);
            outputStream.flush();
            System.out.println("\tRequest Type: " + requestType + "\n");

            // Create stream to receive response from the server
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // 3. Read response from the server - cast object
            List<Customer> customers = (List<Customer>) inputStream.readObject();

            // 4. Process response - display the objects
            if (!customers.isEmpty()) {
                System.out.println("\tCustomer Information (From the server)");
                for (Customer customer : customers) {
                    System.out.println("\tCustomer ID: " + customer.getCustomerId());
                    System.out.println("\tName: " + customer.getCustomerName());
                    // Display other customer details if needed
                    System.out.println();
                }
            } else {
                System.out.println("\tNo customers found!");
            }

            // Close streams and socket
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
