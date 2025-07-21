package disneyprincess.utils;

import disneyprincess.model.Princess;
import disneyprincess.repository.PrincessRepository;

public class PrincessService {
    private final PrincessRepository repository;
    private static final String PRINCESS_ALREADY_EXISTS_MESSAGE = "Princess already exists";
    private static final String PRINCESS_NOT_FOUND_MESSAGE = "Princess not found";

    public PrincessService(PrincessRepository repository) {
        this.repository = repository;
    }

    public void addPrincess(Princess princess) {
        PrincessValidator.validatePrincess(princess);
        if (!repository.exist(princess.getId())) {
            repository.add(princess);
        } else {
            throw new IllegalArgumentException(PRINCESS_ALREADY_EXISTS_MESSAGE);
        }
    }

    public void updatePrincess(Princess princess) {
        PrincessValidator.validatePrincess(princess);
        if (repository.exist(princess.getId())) {
            repository.update(princess);
        } else {
            throw new IllegalArgumentException(PRINCESS_NOT_FOUND_MESSAGE);
        }
    }
}

