package repository;

import model.Princess;
import java.util.ArrayList;
import java.util.List;

public class PrincessRepositoryList implements PrincessRepository {
    private final List<Princess> princesses = new ArrayList<>();

    @Override
    public void addAll(List<Princess> list) {
        this.princesses.addAll(list);
    }

    @Override
    public void add(Princess princess) {
        this.princesses.add(princess);
    }

    @Override
    public void update(Princess princess) {
        for (int i = 0; i < princesses.size(); i++) {
            if (princesses.get(i).getId() == princess.getId()) {
                princesses.set(i, princess);
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

    @Override
    public boolean exist(int id) {
        for (Princess princess : princesses) {
            if (princess.getId() == id) {
                return true;
            }
        }
        return false;
    }
}