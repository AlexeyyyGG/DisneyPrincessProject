package disneyprincess;

import disneyprincess.commands.CommandRegistry;
import disneyprincess.utils.CommandDispatcher;
import disneyprincess.utils.ConsoleReader;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.PrincessFileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrincessRepository repository = new PrincessRepository();
        ConsoleReader reader = new ConsoleReader();
        CommandDispatcher dispatcher = new CommandDispatcher(new CommandRegistry(repository));
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