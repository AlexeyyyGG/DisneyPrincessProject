package commands;

import model.Princess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.PrincessRepository;

@Component
public class ListCommand implements Command {
    private final PrincessRepository repository;
    private static final String COLLECTION_EMPTY = "Collection is empty";

    @Autowired
    public ListCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getName() {
        return "list";
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