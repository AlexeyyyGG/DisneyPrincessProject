package disneyprincess;

import disneyprincess.commands.CommandRegistry;
import disneyprincess.conveyor.ConveyorCommand;
import disneyprincess.conveyor.ConveyorState;
import disneyprincess.repository.PrincessRepositoryDB;
import disneyprincess.utils.CommandDispatcher;
import disneyprincess.utils.ConsoleReader;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepositoryList;
import disneyprincess.utils.PrincessFileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrincessRepositoryList repository = new PrincessRepositoryList();
        PrincessRepositoryDB repositoryDB = new PrincessRepositoryDB();
        ConsoleReader reader = new ConsoleReader();
        ConveyorState conveyorState = new ConveyorState();
        CommandDispatcher dispatcher = new CommandDispatcher(
                new CommandRegistry(repository, conveyorState)
        );
        String filename = "disneyPrincesses";
        List<Princess> princessesList = PrincessFileReader.readPrincessesFromFile(filename);
        for (Princess princess : princessesList) {
            if(!repositoryDB.exist(princess.getId())) {
                repositoryDB.add(princess);
            }
        }
        ConveyorCommand conveyor = new ConveyorCommand(reader, dispatcher, conveyorState);
        conveyor.startConveyor();
    }
}