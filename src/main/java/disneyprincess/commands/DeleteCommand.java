package disneyprincess.commands;

import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.Utils;

public class DeleteCommand implements Command {
    private final PrincessRepository repository;
    private static final String NO_ID_SPECIFIED = "You must specify the princess ID";
    private static final String NO_PRINCESS_WITH_ID = "There are no princesses with this ID";
    private static final String PRINCESS_DELETED = "Princess deleted";

    public DeleteCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        if (args.length < 1) {
            return Result.failure(NO_ID_SPECIFIED);
        }
        try {
            int deleteId = Utils.parseId(args[0]);
            if (!repository.exist(deleteId)) {
                return Result.failure(NO_PRINCESS_WITH_ID);
            }
            repository.delete(deleteId);
            return Result.success(PRINCESS_DELETED);
        } catch (IllegalArgumentException e) {
            return Result.failure(e.getMessage());
        }
    }
}
