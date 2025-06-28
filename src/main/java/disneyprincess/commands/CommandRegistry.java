package disneyprincess.commands;

import disneyprincess.conveyor.ConveyorState;
import disneyprincess.repository.PrincessRepository;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry(PrincessRepository princessRepository, ConveyorState conveyorState) {
        commands.put("add", new AddCommand(princessRepository));
        commands.put("delete", new DeleteCommand(princessRepository));
        commands.put("get", new GetCommand(princessRepository));
        commands.put("list", new ListCommand(princessRepository));
        commands.put("update", new UpdateCommand(princessRepository));
        commands.put("exit", new ExitCommand(conveyorState));
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }
}
