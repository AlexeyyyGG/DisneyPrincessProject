package disneyprincess.commands;

import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.InputValidator;
import disneyprincess.utils.Result;

public class AddCommand implements Command {
    private final PrincessRepository repository;

    public AddCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        try {
            if (args.length < 5) {
                return Result.failure("Not enough arguments to add");
            }
            int id = InputValidator.validateId(args[0]);
            if (repository.exist(id)) {
                return Result.failure("Princess with this ID already exists");
            }
            String name = InputValidator.validateName(args[1]);
            int age = InputValidator.validateAge(args[2]);
            HairColor hairColor = HairColor.fromString(args[3]);
            EyeColor eyeColor = EyeColor.fromString(args[4]);
            Princess princess = new Princess(id, name, age, hairColor, eyeColor);
            repository.add(princess);
            return Result.success("Princess add");
        } catch (IllegalArgumentException e) {
            return Result.failure(e.getMessage());
        } catch (Exception e) {
            return Result.failure("Unexpected error: " + e.getMessage());
        }
    }
}


