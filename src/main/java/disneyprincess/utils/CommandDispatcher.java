package disneyprincess.utils;

import disneyprincess.commands.Command;
import disneyprincess.commands.CommandRegistry;

public class CommandDispatcher {
    private final CommandRegistry registry;

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
                System.out.println("Error: " + result.getErrorMessage());
            }
        } else {
            System.out.println("Unknown command");
        }
    }
}
