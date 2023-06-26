package server.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import model.Customer;
import server.controller.CustomerData;

public class TCPCustomerServerExercise9 {

    public static void main(String[] args) {
        int portNo = 8018;

        CustomerData customerData = new CustomerData();

        System.out.println("\n\tExecuting TCPCustomerServerExercise9");

        try {
            System.out.println("\tWaiting for next request excercise 9");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server needs to continually run to listen for requests
            while (true) {
                // 3. Accept request from client
                Socket clientSocket = serverSocket.accept();

                // 4. Process request - create input stream to read request
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

                // Read request type from client
                String requestType = (String) inputStream.readObject();
                System.out.println("\tRequest Type: " + requestType);

                if (requestType.equals("GET_ALL_CUSTOMERS")) {
                    // Get all customers
                    List<Customer> customers = customerData.getAllCustomers();

                    // 5. Respond to client
                    ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    outputStream.writeObject(customers);
                    System.out.println("\tSending customers: " + customers);

                    // Close streams and socket
                    outputStream.close();
                }

                // Close streams and socket
                inputStream.close();
                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
