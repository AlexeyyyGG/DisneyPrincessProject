package disneyprincess;

import disneyprincess.commands.CommandRegistry;
import disneyprincess.conveyor.ConveyorCommand;
import disneyprincess.conveyor.ConveyorState;
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