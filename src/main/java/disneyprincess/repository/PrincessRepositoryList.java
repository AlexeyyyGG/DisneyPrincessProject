package disneyprincess.repository;

import disneyprincess.model.Princess;
import java.util.ArrayList;
import java.util.List;

public class PrincessRepositoryList implements Repository {
    private final List<Princess> princesses = new ArrayList<>();


    public void addAll(List<Princess> list) {
        this.princesses.addAll(list);
    }

    @Override
    public void add(Princess princess) {
        this.princesses.add(princess);
    }

    @Override
    public void update(Princess updatePrincess) {
        for (int i = 0; i < princesses.size(); i++) {
            if (princesses.get(i).getId() == updatePrincess.getId()) {
                princesses.set(i, updatePrincess);
                break;
            }
        }
    }

    @Override
    public Princess get(int id) {
        for (Princess princess : princesses) {
            if (princess.getId() == id) {
                return princess;
            }
        }
        return null;
    }

    @Override
    public List<Princess> list() {
        return new ArrayList<>(princesses);
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < princesses.size(); i++) {
            if (princesses.get(i).getId() == id) {
                princesses.remove(i);
                break;
            }
        }
    }

    public boolean exist(int id) {
        for (Princess princess : princesses) {
            if (princess.getId() == id) {
                return true;
            }
        }
        return false;
    }
}