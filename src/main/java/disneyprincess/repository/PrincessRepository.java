package disneyprincess.repository;

import disneyprincess.model.Princess;
import java.util.List;

public interface PrincessRepository {
    void addAll(List<Princess> princesses);

    void add(Princess princess);

    void update(Princess princess);

    Princess get(int id);

    List<Princess> list();

    void delete(int id);

    boolean exist(int id);
}
