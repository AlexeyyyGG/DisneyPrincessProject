package disneyprincess.commands;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.Utils;

public class GetCommand implements Command {
    private final PrincessRepository repository;
    private static final String NO_ID_SPECIFIED = "You must specify the princess ID";
    private static final String NO_PRINCESS_WITH_ID = "There are no princesses with this ID";

    public GetCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        if (args.length < 1) {
            return Result.failure(NO_ID_SPECIFIED);
        }
        try {
            int getId = Utils.parseId(args[0]);
            if (!repository.exist(getId)) {
                return Result.failure(NO_PRINCESS_WITH_ID);
            }
            Princess princess = repository.get(getId);
            String princessInfo = princess.toString();
            return Result.success(princessInfo);
        } catch (IllegalArgumentException e) {
            return Result.failure(e.getMessage());
        }
    }
}