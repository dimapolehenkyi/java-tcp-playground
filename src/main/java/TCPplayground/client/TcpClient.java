package TCPplayground.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TcpClient {

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 5050);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))
        ) {
            System.out.println("Connected to " + socket.getInetAddress());
            System.out.println("Hello New Socket!");

            Scanner input = new Scanner(System.in);
            String line;
            while (!(line = input.nextLine()).equals("end")) {
                writer.write(line);
                writer.newLine();
                writer.flush();

                byte[] bytes = new byte[1024];
                int bytesRead = socket.getInputStream().read(bytes);
                if (bytesRead > 0) {
                    String message = new String(bytes, 0, bytesRead, StandardCharsets.UTF_8);
                    System.out.println("Server response -> " + message);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
