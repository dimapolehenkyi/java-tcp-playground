package TCPplayground.command;

public interface Command {

    String getName();

    String execute(String[] message);

}
