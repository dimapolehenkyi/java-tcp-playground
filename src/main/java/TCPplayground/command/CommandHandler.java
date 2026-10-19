package TCPplayground.command;

import TCPplayground.command.impl.EchoCommand;
import TCPplayground.command.impl.HelpCommand;

import java.util.*;

public class CommandHandler {

    private final Map<String, Command> commandMap = new HashMap<>();

    public CommandHandler() {
        registerCommand(new EchoCommand());

        List<String> commands = new ArrayList<>();
        for(Map.Entry<String, Command> entry : commandMap.entrySet()) {
            commands.add(entry.getKey());
        }
        commands.add("help");
        registerCommand(new HelpCommand(commands));
    }

    public String handleCommand(String command) {
        if (command == null || command.isBlank()) {
            return "";
        }

        String[] points = command.trim().split("\\s+");
        String commandName = points[0];
        String[] message = Arrays.copyOfRange(points, 1, points.length);

        Command cmd = commandMap.get(commandName);

        return cmd != null ? cmd.execute(message) : "Unknown Command!";
    }

    private void registerCommand(Command command) {
        commandMap.put(command.getName(), command);
    }

}
