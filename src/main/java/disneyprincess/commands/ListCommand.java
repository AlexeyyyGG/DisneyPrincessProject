package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class ListCommand implements Command {
    private final PrincessRepository princessRepository;
    private static final String COLLECTION_EMPTY = "Collection is empty";

    public ListCommand(PrincessRepository princessRepository) {
        this.princessRepository = princessRepository;
    }

    @Override
    public Result execute(String[] args) {
        if (princessRepository.list().isEmpty()) {
            return Result.failure(COLLECTION_EMPTY);
        }
        StringBuilder sb = new StringBuilder();
        for (Princess princess : princessRepository.list()) {
            sb.append(princess.toString()).append("\n");
        }
        return Result.success(sb.toString());
    }
}