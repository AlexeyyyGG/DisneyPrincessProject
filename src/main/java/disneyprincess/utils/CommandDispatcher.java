package disneyprincess.utils;

import disneyprincess.commands.AddCommand;
import disneyprincess.commands.DeleteCommand;
import disneyprincess.commands.GetCommand;
import disneyprincess.commands.ListCommand;
import disneyprincess.commands.UpdateCommand;
import disneyprincess.repository.PrincessRepository;

public class CommandDispatcher {
  private final PrincessRepository repository;

  public CommandDispatcher(PrincessRepository repository) {
    this.repository = repository;
  }

  public void dispatch(String command, String[] args) {
    if (command == null) {
      System.out.println("Unknown command");
      return;
    }
    switch (command) {
      case "add":
        AddCommand addCommand = new AddCommand(repository);
        addCommand.execute(args);
        break;
      case "update":
        UpdateCommand updateCommand = new UpdateCommand(repository);
        updateCommand.execute(args);
        break;
      case "delete":
        DeleteCommand deleteCommand = new DeleteCommand(repository);
        deleteCommand.execute(args);
        break;
      case "get":
        GetCommand getCommand = new GetCommand(repository);
        getCommand.execute(args);
        break;
      case "list":
        ListCommand listCommand = new ListCommand(repository);
        listCommand.execute(args);
        break;
      case "exit":
        return;
      default:
        System.out.println("Unknown command");
    }
  }
}
