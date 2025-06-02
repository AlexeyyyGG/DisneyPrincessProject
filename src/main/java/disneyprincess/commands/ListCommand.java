package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class ListCommand implements Command {
  private final PrincessRepository repository;

  public ListCommand(PrincessRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(String[] args) {
    for (Princess princess: repository.list()){
      System.out.println(princess);
    }
  }
}
