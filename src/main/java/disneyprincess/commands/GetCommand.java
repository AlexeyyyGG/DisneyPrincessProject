package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class GetCommand implements Command {
  private final PrincessRepository repository;

  public GetCommand(PrincessRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(String[] args) {
    if (args.length < 1) {
      System.out.println("You must specify the princess ID to delete");
      return;
    }

    int getID = Integer.parseInt(args[0]);
    Princess princess = repository.get(getID);

    if (princess == null) {
      System.out.println("There is no princess with this ID");
      return;
    }

    System.out.println(princess);
  }
}
