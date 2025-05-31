package disneyprincess;

import disneyprincess.controller.Controller;
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
    Controller controller = new Controller();
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
      CommandDispatcher.dispatch(command, reader.getArgs(), repository, controller);
    }
  }
}