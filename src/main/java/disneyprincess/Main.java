package disneyprincess;

import disneyprincess.commands.CommandRegistry;
import disneyprincess.conveyor.ConveyorCommand;
import disneyprincess.conveyor.ConveyorState;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.repository.PrincessRepositoryDB;
import disneyprincess.utils.CommandDispatcher;
import disneyprincess.utils.ConsoleReader;
import disneyprincess.model.Princess;
import disneyprincess.utils.PrincessFileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/princesses_database";
        String user = "user";
        String password = "user_password";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PrincessRepository repository = new PrincessRepositoryDB(connection);
            ConsoleReader reader = new ConsoleReader();
            ConveyorState conveyorState = new ConveyorState();
            CommandDispatcher dispatcher = new CommandDispatcher(
                    new CommandRegistry(repository, conveyorState)
            );
            String filename = "disneyPrincesses";
            List<Princess> princessesList = PrincessFileReader.readPrincessesFromFile(filename);
            for (Princess princess : princessesList) {
                if (!repository.exist(princess.getId())) {
                    repository.add(princess);
                }
            }
            ConveyorCommand conveyor = new ConveyorCommand(reader, dispatcher, conveyorState);
            conveyor.startConveyor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}