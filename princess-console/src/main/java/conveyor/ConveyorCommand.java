package conveyor;

import commands.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import readers.ConsoleReader;

@Component
public class ConveyorCommand {
    private final ConsoleReader reader;
    private final CommandDispatcher dispatcher;
    private final ConveyorState state;

    @Autowired
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
