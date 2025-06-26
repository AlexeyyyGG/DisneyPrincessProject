package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.Repository;

public class ListCommand implements Command {
    private final Repository repository;
    private static final String COLLECTION_EMPTY = "Collection is empty";

    public ListCommand(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        if (repository.list().isEmpty()) {
            return Result.failure(COLLECTION_EMPTY);
        }
        StringBuilder sb = new StringBuilder();
        for (Princess princess : repository.list()) {
            sb.append(princess.toString()).append("\n");
        }
        return Result.success(sb.toString());
    }
}