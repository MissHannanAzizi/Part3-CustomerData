package server.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Customer;
import server.controller.CustomerData;

public class TCPCustomerServer {

    public static void main(String[] args) {
        int portNo = 8088;

        CustomerData customerData = new CustomerData();

        System.out.println("\n\tExecuting TCPCustomerServer");

        try {
            System.out.println("\tWaiting for next request");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server needs to continually run to listen for requests
            while (true) {
                // 3. Accept request from client
                Socket clientSocket = serverSocket.accept();

                // 4. Process request - create input stream to read request
                // Request - customer id:int
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

                // Read customer id from client
                int customerId = inputStream.readInt();
                System.out.println("\tRequest for customer id: " + customerId);

                // Get customer
                Customer customer = customerData.searchCustomerById(customerId);

                // 5. Respond to client
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                outputStream.writeObject(customer);
                System.out.println("\tSending customer: " + customer);

                // Close streams and socket
                inputStream.close();
                outputStream.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
