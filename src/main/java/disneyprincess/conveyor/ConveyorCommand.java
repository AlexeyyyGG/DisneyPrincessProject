package disneyprincess.conveyor;

import disneyprincess.utils.CommandDispatcher;
import disneyprincess.utils.ConsoleReader;

public class ConveyorCommand {
    private final ConsoleReader reader;
    private final CommandDispatcher dispatcher;

    public ConveyorCommand(ConsoleReader reader, CommandDispatcher dispatcher) {
        this.reader = reader;
        this.dispatcher = dispatcher;
    }

    public void startConveyor() {
        while (true) {
            reader.read();
            String command = reader.getCommand();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }
            dispatcher.dispatch(command, reader.getArgs());
        }
    }
}
