import commands.CommandRegistry;
import conveyor.ConveyorCommand;
import conveyor.ConveyorState;
import repository.DatabaseConnection;
import repository.PrincessRepository;
import repository.PrincessRepositoryDB;
import commands.CommandDispatcher;
import readers.ConsoleReader;
import model.Princess;
import readers.PrincessFileReader;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        PrincessRepository repository = new PrincessRepositoryDB(connection);
        ConsoleReader reader = new ConsoleReader();
        ConveyorState conveyorState = new ConveyorState();
        CommandDispatcher dispatcher = new CommandDispatcher(
                new CommandRegistry(repository, conveyorState)
        );
        String filename = "disneyPrincesses";
        List<Princess> princessesList = PrincessFileReader.readPrincessesFromFile(filename);
        repository.addAll(princessesList);
        ConveyorCommand conveyor = new ConveyorCommand(reader, dispatcher, conveyorState);
        conveyor.startConveyor();
    }
}