package disneyprincess.commands;

import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class UpdateCommand implements Command{
  private final int id;
  private final String name;
  private final int age;
  private final HairColor hairColor;
  private final EyeColor eyeColor;
  private final PrincessRepository repository;

  public UpdateCommand(int id, String name, int age, HairColor hairColor, EyeColor eyeColor,
      PrincessRepository repository) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.hairColor = hairColor;
    this.eyeColor = eyeColor;
    this.repository = repository;
  }


  @Override
  public void execute() {
    Princess updatePrincess = new Princess(id, name, age, hairColor, eyeColor);
    repository.update(updatePrincess);
    System.out.println("Princess update");
  }
}
