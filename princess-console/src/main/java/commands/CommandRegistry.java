package commands;

import conveyor.ConveyorState;
import repository.PrincessRepository;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry(PrincessRepository repository, ConveyorState conveyorState) {
        commands.put("add", new AddCommand(repository));
        commands.put("delete", new DeleteCommand(repository));
        commands.put("get", new GetCommand(repository));
        commands.put("list", new ListCommand(repository));
        commands.put("update", new UpdateCommand(repository));
        commands.put("exit", new ExitCommand(conveyorState));
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }
}
