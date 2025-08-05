package commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandDispatcher {
    private final CommandRegistry registry;
    private static final String ERROR_MESSAGE = "Error: ";
    private static final String UNKNOWN_COMMAND = "Unknown command";

    @Autowired
    public CommandDispatcher(CommandRegistry registry) {
        this.registry = registry;
    }

    public void dispatch(String commandName, String[] args) {
        Command command = registry.getCommand(commandName);
        if (command != null) {
            Result result = command.execute(args);
            if (result.isSuccess()) {
                System.out.println(result.getSuccessMessage());
            } else {
                System.out.println(ERROR_MESSAGE + result.getErrorMessage());
            }
        } else {
            System.out.println(UNKNOWN_COMMAND);
        }
    }
}
