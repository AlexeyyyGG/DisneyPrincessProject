package disneyprincess.commands;

import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class AddCommand implements Command{
  private final PrincessRepository repository;

  public AddCommand(PrincessRepository repository){
    this.repository = repository;

  }

  @Override
  public void execute(String[] args) {
    int id = Integer.parseInt(args[0]);
    String name = args[1];
    int age = Integer.parseInt(args[2]);
    HairColor hairColor = HairColor.fromString(args[3]);
    EyeColor eyeColor = EyeColor.fromString(args[4]);
    Princess princess = new Princess(id, name, age, hairColor, eyeColor);
    repository.add(princess);
    System.out.println("Princess add");
  }
}
