package commands;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    @Autowired
    public CommandRegistry(List<Command> listCommand) {
        for (Command command : listCommand) {
            commands.put(command.getName(), command);
        }
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }
}
