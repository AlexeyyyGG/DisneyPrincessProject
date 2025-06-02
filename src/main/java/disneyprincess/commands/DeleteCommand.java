package disneyprincess.commands;

import disneyprincess.repository.PrincessRepository;

public class DeleteCommand implements Command {
  private final PrincessRepository repository;

  public DeleteCommand(PrincessRepository repository) {
    this.repository = repository;

  }

  @Override
  public void execute(String[] args) {
    int deleteId = Integer.parseInt(args[0]);
    repository.delete(deleteId);
  }
}
