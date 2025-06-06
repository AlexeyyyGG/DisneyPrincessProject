package disneyprincess.repository;

import disneyprincess.model.Princess;
import java.util.ArrayList;
import java.util.List;

public class PrincessRepository {
    private final List<Princess> princesses;

    public PrincessRepository() {
        this.princesses = new ArrayList<>();
    }

    public void addAll(List<Princess> list) {
        this.princesses.addAll(list);
    }

    public void add(Princess princess) {
        this.princesses.add(princess);
    }

    public void update(Princess updatePrincess) {
        for (int i = 0; i < princesses.size(); i++) {
            if (princesses.get(i).getId() == updatePrincess.getId()) {
                princesses.set(i, updatePrincess);
                break;
            }
        }
    }

    public Princess get(int id) {
        for (Princess princess : princesses) {
            if (princess.getId() == id) {
                return princess;
            }
        }
        return null;
    }

    public List<Princess> list() {
        return new ArrayList<>(princesses);
    }

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