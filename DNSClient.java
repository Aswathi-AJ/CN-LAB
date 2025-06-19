
import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter domain name: ");
            String domain = sc.nextLine();
            byte[] sendBuffer = domain.getBytes();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 9876);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            String ip = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Resolved IP Address: " + ip);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
