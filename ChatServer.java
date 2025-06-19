import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        final int PORT = 5544;

        try (
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Client connected!");

            String clientMsg, serverReply;

            while ((clientMsg = in.readLine()) != null) {
                System.out.println("Client: " + clientMsg);

                System.out.print("You: ");
                serverReply = serverInput.readLine();
                out.println("Server: " + serverReply);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
