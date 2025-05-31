package disneyprincess.commands;

import disneyprincess.repository.PrincessRepository;

public class ListCommand implements Command {
  private final PrincessRepository repository;

  public ListCommand(PrincessRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute() {
    repository.list();
  }
}
