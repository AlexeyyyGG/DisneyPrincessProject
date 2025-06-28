package disneyprincess.commands;

import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.Utils;

public class DeleteCommand implements Command {
    private final PrincessRepository princessRepository;
    private static final String NO_ID_SPECIFIED = "You must specify the princess ID";
    private static final String NO_PRINCESS_WITH_ID = "There are no princesses with this ID";
    private static final String PRINCESS_DELETED = "Princess deleted";

    public DeleteCommand(PrincessRepository princessRepository) {
        this.princessRepository = princessRepository;
    }

    @Override
    public Result execute(String[] args) {
        if (args.length < 1) {
            return Result.failure(NO_ID_SPECIFIED);
        }
        try {
            int deleteId = Utils.parseId(args[0]);
            if (!princessRepository.exist(deleteId)) {
                return Result.failure(NO_PRINCESS_WITH_ID);
            }
            princessRepository.delete(deleteId);
            return Result.success(PRINCESS_DELETED);
        } catch (IllegalArgumentException e) {
            return Result.failure(e.getMessage());
        }
    }
}
