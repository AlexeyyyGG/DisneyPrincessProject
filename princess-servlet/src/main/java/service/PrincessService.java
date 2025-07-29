package service;

import model.Princess;
import repository.PrincessRepository;
import utils.PrincessValidator;
import java.util.List;

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

    public Princess getPrincess(int id) {
        if (repository.exist(id)) {
            return repository.get(id);
        } else {
            throw new IllegalArgumentException(PRINCESS_NOT_FOUND_MESSAGE);
        }
    }

    public List<Princess> getAllPrincess() {
        return repository.list();
    }

    public void deletePrincess(int id) {
        if (repository.exist(id)) {
            repository.delete(id);
        } else {
            throw new IllegalArgumentException(PRINCESS_NOT_FOUND_MESSAGE);
        }
    }
}

