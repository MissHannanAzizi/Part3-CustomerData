package server.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Customer;
import server.controller.CustomerData;

public class TCPCustomerServerExercise5 {

    public static void main(String[] args) {
        int portNo = 8089;

        System.out.println("\n\tExecuting TCPCustomerServerExercise5");

        CustomerData customerData = new CustomerData();

        try {
            System.out.println("\tWaiting for next request");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server needs to continually run to listen for requests
            while (true) {
                // 3. Accept request from client
                Socket clientSocket = serverSocket.accept();

                // 4. Process request - create input stream to read request
                // Request - customer name:string
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

                // Read customer name from client
                String customerName = (String) inputStream.readObject();
                System.out.println("\tRequest for customer name: " + customerName);

                // Get customer by name from CustomerData
                Customer customer = customerData.searchCustomerByName(customerName);

                // Prepare customer information
                String customerInfo;
                if (customer != null) {
                    customerInfo = "Customer Name: " + customer.getCustomerName() + ", Customer ID: "
                            + customer.getCustomerId();
                } else {
                    customerInfo = "Customer not found!";
                }

                // 5. Respond to client
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                outputStream.writeObject(customerInfo);
                System.out.println("\tSending customer: " + customerInfo);

                // Close streams and socket
                inputStream.close();
                outputStream.close();
                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
