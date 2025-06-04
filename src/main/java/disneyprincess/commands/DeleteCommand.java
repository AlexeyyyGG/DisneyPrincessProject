package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class DeleteCommand implements Command {
  private final PrincessRepository repository;

  public DeleteCommand(PrincessRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(String[] args) {
    if (args.length < 1) {
      System.out.println("You must specify the princess ID to delete");
      return;
    }

    int deleteId = Integer.parseInt(args[0]);
    Princess princess = repository.get(deleteId);

    if (princess == null) {
      System.out.println("There are no princesses with this ID");
      return;
    }

    repository.delete(deleteId);
    System.out.println("Princess deleted");
  }
}
