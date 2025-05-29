package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class AddCommand implements Command{
  private final PrincessRepository repository;
  private final Princess princess;

  public AddCommand(PrincessRepository repository, Princess princess){
    this.repository = repository;
    this.princess = princess;
  }

  @Override
  public void execute() {
    repository.add(princess);
    System.out.println("Princess add");
  }
}
