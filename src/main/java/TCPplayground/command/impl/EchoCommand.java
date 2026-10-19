package TCPplayground.command.impl;

import TCPplayground.command.Command;

public class EchoCommand implements Command {

    @Override
    public String execute(String[] message) {
        return message.length >= 1 ? String.join(" ", message) : "error: echo <message>";
    }

    @Override
    public String getName() {
        return "echo";
    }
}
