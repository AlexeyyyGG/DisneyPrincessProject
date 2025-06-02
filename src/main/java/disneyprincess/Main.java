package disneyprincess;

import disneyprincess.utils.CommandDispatcher;
import disneyprincess.utils.ConsolReader;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.PrincessFileReader;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    PrincessRepository repository = new PrincessRepository();
    ConsolReader reader = new ConsolReader();
    CommandDispatcher dispatcher = new CommandDispatcher(repository);
    String filename = "disneyPrincesses";
    List<Princess> princessesList = PrincessFileReader.readPrincessesFromFile(filename);
    repository.addAll(princessesList);

    while (true) {
      reader.read();
      String command = reader.getCommand();

      if (command.equalsIgnoreCase("exit")) {
        System.out.println("Exiting...");
        break;
      }
      dispatcher.dispatch(command, reader.getArgs());
    }
  }
}