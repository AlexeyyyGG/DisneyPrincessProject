package disneyprincess;

import java.util.List;

public class Runner {
  public static void main(String[] args) {
    PrincessRepository repository = new PrincessRepository();

    String filename = "disneyPrincesses";
    List<Princesses> princessesList = PrincessFileReader.readPrincessesFromFile(filename);

    for (Princesses princess : princessesList) {
      repository.addPrincess(princess);
    }
  }
}
