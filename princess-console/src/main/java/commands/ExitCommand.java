package commands;

import conveyor.ConveyorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExitCommand implements Command {
    private final ConveyorState conveyorState;
    private static final String EXIT = "EXITING...";

    @Autowired
    public ExitCommand(ConveyorState conveyorState) {
        this.conveyorState = conveyorState;
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public Result execute(String[] args) {
        conveyorState.stop();
        return Result.success(EXIT);
    }
}
