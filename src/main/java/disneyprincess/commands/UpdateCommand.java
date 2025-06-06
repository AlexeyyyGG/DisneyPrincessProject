package disneyprincess.commands;

import disneyprincess.model.EyeColor;
import disneyprincess.model.HairColor;
import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;
import disneyprincess.utils.InputValidator;
import disneyprincess.utils.Result;

public class UpdateCommand implements Command {
    private final PrincessRepository repository;

    public UpdateCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        try {
            if (args.length < 5) {
                return Result.failure("Insufficient number of arguments for update");
            }
            int updateId = InputValidator.validateId(args[0]);
            if (!repository.exist(updateId)) {
                return Result.failure("Princess with this ID does not exist");
            }
            String updateName = InputValidator.validateName(args[1]);
            int updateAge = InputValidator.validateAge(args[2]);
            HairColor updateHairColor = HairColor.fromString(args[3]);
            EyeColor updateEyeColor = EyeColor.fromString(args[4]);
            Princess updatePrincess = new Princess(updateId, updateName, updateAge, updateHairColor,
                    updateEyeColor);
            repository.update(updatePrincess);
            return Result.success("Princess updated");
        } catch (IllegalArgumentException e) {
            return Result.failure(e.getMessage());
        } catch (Exception e) {
            return Result.failure("Unexpected error: " + e.getMessage());
        }
    }
}