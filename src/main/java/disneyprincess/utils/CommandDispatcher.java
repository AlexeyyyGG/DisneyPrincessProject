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
      command.execute(args);
    }else {
    System.out.println("Unknown command");
    }
  }
}
