package disneyprincess.commands;

import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class AddCommand implements Command {
  private final PrincessRepository repository;

  public AddCommand(PrincessRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(String[] args) {
    if (args.length < 5) {
      System.out.println("Not enough arguments to add");
      return;
    }

    int id = Integer.parseInt(args[0]);
    Princess existingPrincess = repository.get(id);

    if (existingPrincess != null) {
      System.out.println("A princess with the same ID already exists");
      return;
    }

    String name = args[1];
    int age = Integer.parseInt(args[2]);
    HairColor hairColor = HairColor.fromString(args[3]);
    EyeColor eyeColor = EyeColor.fromString(args[4]);
    Princess princess = new Princess(id, name, age, hairColor, eyeColor);
    repository.add(princess);
    System.out.println("Princess add");
  }
}

