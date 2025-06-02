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
    int getID = Integer.parseInt(args[0]);
    Princess princess = repository.get(getID);
    System.out.println(princess);
  }
}
