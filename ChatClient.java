import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (
            Socket socket = new Socket("localhost", 5544);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to Server!");

            String userInput;
            while ((userInput = keyboard.readLine()) != null) {
                out.println(userInput); // Send message to server
                String response = in.readLine(); // Receive reply
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
