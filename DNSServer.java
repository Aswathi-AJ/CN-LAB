import java.net.*;

public class DNSServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            System.out.println("DNS Server is running...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String domain = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received request for: " + domain);

                String ip;
                try {
                    InetAddress address = InetAddress.getByName(domain);
                    ip = address.getHostAddress();
                } catch (UnknownHostException e) {
                    ip = "Domain not found";
                }

                sendBuffer = ip.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
