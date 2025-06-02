package disneyprincess.commands;

import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class UpdateCommand implements Command {
  private final PrincessRepository repository;

  public UpdateCommand(PrincessRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(String[] args) {
    int updateId = Integer.parseInt(args[0]);
    String updateName = args[1];
    int updateAge = Integer.parseInt(args[2]);
    HairColor updateHairColor = HairColor.fromString(args[3]);
    EyeColor updateEyeColor = EyeColor.fromString(args[4]);
    Princess updatePrincess = new Princess(updateId, updateName, updateAge, updateHairColor,updateEyeColor);
    repository.update(updatePrincess);
  }
}
