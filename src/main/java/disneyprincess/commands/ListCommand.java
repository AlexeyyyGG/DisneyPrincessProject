package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.Result;

public class ListCommand implements Command {
    private final PrincessRepository repository;

    public ListCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        if (repository.list() == null || repository.list().isEmpty()) {
            return Result.failure("Collection is empty");
        }
        StringBuilder sb = new StringBuilder();
        for (Princess princess : repository.list()) {
            sb.append(princess.toString()).append("\n");
        }
        return Result.success(sb.toString());
    }
}