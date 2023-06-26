package client.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPCustomerClientExercise5 {

    public static void main(String[] args) {
        try {
            System.out.println("\tExecuting TCPCustomerClientExercise5");

            // Server information
            int serverPortNo = 8089;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // 1. Connect to the remote machine
            Socket socket = new Socket(serverAddress, serverPortNo);

            // Create stream to send request
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            // 2. Send request to the server
            Scanner scanner = new Scanner(System.in);
            System.out.print("\tEnter customer name: ");
            String customerName = scanner.nextLine();
            outputStream.writeObject(customerName);
            outputStream.flush();
            System.out.println("\tRequesting customer name: " + customerName + "\n");

            // Create stream to receive response from the server
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // 3. Read response from the server
            String customerInfo = (String) inputStream.readObject();

            // 4. Process response - display the customer information
            System.out.println("\tReceived customer information:");
            System.out.println("\t" + customerInfo);

            // Close streams and socket
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
