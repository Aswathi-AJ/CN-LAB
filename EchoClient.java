

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String server = "localhost";
        int port = 1345;

        try (Socket socket = new Socket(server, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to Echo Server. Type messages:");

            String message;
            while ((message = userInput.readLine()) != null) {
                out.println(message);
                String reply = in.readLine();
                System.out.println("Server replied: " + reply);
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
