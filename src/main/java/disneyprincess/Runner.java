package disneyprincess;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.PrincessFileReader;
import java.util.List;

public class Runner {

  public static void main(String[] args) {
    PrincessRepository repository = new PrincessRepository();

    String filename = "disneyPrincesses";
    List<Princess> princessesList = PrincessFileReader.readPrincessesFromFile(filename);
    repository.addAll(princessesList);
  }
}
