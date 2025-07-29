package conveyor;

import utils.CommandDispatcher;
import utils.ConsoleReader;

public class ConveyorCommand {
    private final ConsoleReader reader;
    private final CommandDispatcher dispatcher;
    private final ConveyorState state;

    public ConveyorCommand(
            ConsoleReader reader,
            CommandDispatcher dispatcher,
            ConveyorState state
    ) {
        this.reader = reader;
        this.dispatcher = dispatcher;
        this.state = state;
    }

    public void startConveyor() {
        while (state.isRunning()) {
            reader.read();
            String command = reader.getCommand();
            dispatcher.dispatch(command, reader.getArgs());
        }
    }
}
