package TCPplayground.server;

import TCPplayground.handlers.CommandHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpServer {

    private final CommandHandler handler = new CommandHandler();

    public static void main(String[] args) {
        TcpServer server = new TcpServer();
        server.start();
    }

    public void start() {
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
                        System.out.println("Received: " + line);

                        String response = handler.handleCommand(line);

                        writer.write(response);
                        writer.newLine();
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
