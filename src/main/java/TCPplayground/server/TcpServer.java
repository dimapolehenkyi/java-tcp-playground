package TCPplayground.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpServer {

    public static void main(String[] args) {
        int port = 5050;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started and waiting for connection...");
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Accepted connection from " + socket.getInetAddress());
                try (
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
                )) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);

                        writer.write(line);
                        writer.newLine();
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
