package commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandDispatcher {
    private final Map<String, Command> commands = new HashMap<>();
    private static final String ERROR_MESSAGE = "Error: ";
    private static final String UNKNOWN_COMMAND = "Unknown command";

    @Autowired
    public CommandDispatcher(List<Command> listCommand) {
        for (Command command : listCommand) {
            commands.put(command.getName(), command);
        }
    }

    public void dispatch(String commandName, String[] args) {
        Command command = commands.get(commandName);
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
