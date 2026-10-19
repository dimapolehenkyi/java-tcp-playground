package TCPplayground.handlers;

import java.util.Arrays;

public class CommandHandler {

    private final String[] commands = {"echo", "help"};

    public String handleCommand(String command) {
        if (command == null || command.isBlank()) {
            return "";
        }

        String[] points = command.trim().split("\\s+");
        String[] message = Arrays.copyOfRange(points, 1, points.length);

        return switch (points[0]) {
            case "echo" -> message.length >= 1 ? echo(message) : "Usage: echo <message>";
            case "help" -> help();
            default -> "ERROR: Unknown command!";
        };
    }

    private String echo(String[] message) {
        return String.join(" ", message);
    }

    private String help() {
        return String.join(" | ", commands);
    }

}
