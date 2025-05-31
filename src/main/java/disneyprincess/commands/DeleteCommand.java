package disneyprincess.commands;

import disneyprincess.repository.PrincessRepository;

public class DeleteCommand implements Command {
  private final PrincessRepository repository;
  private final int id;

  public DeleteCommand(PrincessRepository repository, int id) {
    this.repository = repository;
    this.id = id;
  }

  @Override
  public void execute() {
    repository.delete(id);
  }
}
