package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.InputValidator;
import disneyprincess.utils.Result;

public class GetCommand implements Command {
    private final PrincessRepository repository;

    public GetCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        try {
            if (args.length < 1) {
                return Result.failure("You must specify the princess ID");
            }
            int getId = InputValidator.validateId(args[0]);
            if (!repository.exist(getId)) {
                return Result.failure("There are no princesses with this ID");
            }
            Princess princess = repository.get(getId);
            String princessInfo = princess.toString();
            return Result.success(princessInfo);
        } catch (IllegalArgumentException e) {
            return Result.failure(e.getMessage());
        } catch (Exception e) {
            return Result.failure("Unexpected error: " + e.getMessage());
        }
    }
}