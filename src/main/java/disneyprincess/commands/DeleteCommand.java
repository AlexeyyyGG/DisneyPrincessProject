package disneyprincess.commands;

import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.InputValidator;
import disneyprincess.utils.Result;

public class DeleteCommand implements Command {
    private final PrincessRepository repository;

    public DeleteCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        try {
            if (args.length < 1) {
                return Result.failure("You must specify the princess ID to delete");
            }
            int deleteId = InputValidator.validateId(args[0]);
            if (!repository.exist(deleteId)) {
                return Result.failure("There are no princesses with this ID");
            }
            repository.delete(deleteId);
            return Result.success("Princess deleted");
        } catch (IllegalArgumentException e) {
            return Result.failure(e.getMessage());
        } catch (Exception e) {
            return Result.failure("Unexpected error: " + e.getMessage());
        }
    }
}
