
import java.io.*;
import java.net.*;

public class HttpWebClient {

    public static void main(String[] args) {
        String host = "example.com"; // You can replace this with any valid website
        int port = 80;

        try {
            // Create socket connection to the server
            Socket socket = new Socket(host, port);
            
            // Output stream to send HTTP GET request
            PrintWriter request = new PrintWriter(socket.getOutputStream(), true);
            request.println("GET / HTTP/1.1");
            request.println("Host: " + host);
            request.println("Connection: close");
            request.println(); // Blank line to end the request

            // Input stream to read the server response
            BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            System.out.println("---- Web Page Content ----");
            while ((line = response.readLine()) != null) {
                System.out.println(line);
            }

            // Close connections
            response.close();
            request.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
