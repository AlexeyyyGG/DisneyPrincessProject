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
    if (repository.list() == null || repository.list().isEmpty()){
      System.out.println("Collection is empty");
      return;
    }

    for (Princess princess: repository.list()){
      System.out.println(princess);
    }
  }
}
