package commands;

import model.EyeColor;
import model.HairColor;
import model.Princess;
import repository.PrincessRepository;
import utils.Utils;

public class UpdateCommand implements Command {
    private final PrincessRepository repository;
    private static final String INSUFFICIENT_ARGS_UPDATE = "Insufficient number of arguments for update";
    private static final String PRINCESS_NOT_EXIST = "Princess with this ID does not exist";
    private static final String PRINCESS_UPDATED = "Princess updated";

    public UpdateCommand(PrincessRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result execute(String[] args) {
        if (args.length < 5) {
            return Result.failure(INSUFFICIENT_ARGS_UPDATE);
        }
        try {
            int updateId = Utils.parseId(args[0]);
            if (!repository.exist(updateId)) {
                return Result.failure(PRINCESS_NOT_EXIST);
            }
            String updateName = args[1];
            int updateAge = Utils.parseAge(args[2]);
            HairColor updateHairColor = HairColor.fromString(args[3]);
            EyeColor updateEyeColor = EyeColor.fromString(args[4]);
            Princess updatePrincess = new Princess(
                    updateId,
                    updateName,
                    updateAge,
                    updateHairColor,
                    updateEyeColor
            );
            repository.update(updatePrincess);
            return Result.success(PRINCESS_UPDATED);
        } catch (IllegalArgumentException e) {
            return Result.failure(e.getMessage());
        }
    }
}