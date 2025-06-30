package disneyprincess.commands;

import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.Utils;

public class AddCommand implements Command {
    private final PrincessRepository repository;

    private static final String PRINCESS_ADDED = "Princess add";
    private static final String NOT_ENOUGH_ARGUMENTS = "Not enough arguments to add";
    private static final String PRINCESS_ALREADY_EXISTS = "Princess with this ID already exists";

    public AddCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        if (args.length < 5) {
            return Result.failure(NOT_ENOUGH_ARGUMENTS);
        }
        try {
            int id = Utils.parseId(args[0]);
            if (repository.exist(id)) {
                return Result.failure(PRINCESS_ALREADY_EXISTS);
            }
            String name = args[1];
            int age = Utils.parseAge(args[2]);
            HairColor hairColor = HairColor.fromString(args[3]);
            EyeColor eyeColor = EyeColor.fromString(args[4]);
            Princess princess = new Princess(id, name, age, hairColor, eyeColor);
            repository.add(princess);
            return Result.success(PRINCESS_ADDED);
        } catch (IllegalArgumentException e) {
            return Result.failure(e.getMessage());
        }
    }
}


