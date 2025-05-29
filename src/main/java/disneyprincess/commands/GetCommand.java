package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class GetCommand implements Command {
  private final PrincessRepository repository;
  private final int id;

  public GetCommand(PrincessRepository repository, int id) {
    this.repository = repository;
    this.id = id;
  }

  @Override
  public void execute() {
    Princess princess = repository.get(id);
    System.out.println(princess);
  }
}
