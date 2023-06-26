package client.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import model.Customer;

public class TCPCustomerClient {

    public static void main(String[] args) {
        try {
            System.out.println("\tExecuting TCPCustomerClient");

            // Server information
            int serverPortNo = 8089;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // 1. Connect to the remote machine
            Socket socket = new Socket(serverAddress, serverPortNo);

            // Create stream to send request
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            // 2. Send request to the server
            int customerId = 5;
            outputStream.writeInt(customerId);
            outputStream.flush();
            System.out.println("\tRequesting customer id: " + customerId + "\n");

            // Create stream to receive response from the server
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // 3. Read response from the server - cast object
            Customer customer = (Customer) inputStream.readObject();

            // 4. Process response - display the object
            if (customer != null) {
                System.out.println("\tCustomer Information (From the server)");
                System.out.println("\tCustomer Id: " + customer.getCustomerId());
                System.out.println("\tName: " + customer.getCustomerName());
            } else {
                System.out.println("\tCustomer not found!");
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
