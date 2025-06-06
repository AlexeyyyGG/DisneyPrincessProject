package disneyprincess.commands;

import disneyprincess.utils.Result;

public interface Command {
    Result execute(String[] args);
}
