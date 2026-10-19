package TCPplayground.command.impl;

import TCPplayground.command.Command;

import java.util.List;

public class HelpCommand implements Command {

    private final List<String> commands;

    public HelpCommand(List<String> commands) {
        this.commands = commands;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String execute(String[] message) {
        return String.join(" | ", commands);
    }
}
