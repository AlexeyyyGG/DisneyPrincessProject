package disneyprincess.repository;

import disneyprincess.model.Princess;
import java.util.List;

public interface Repository {

    void add(Princess princess);

    void update(Princess updatePrincess);

    Princess get(int id);

    List<Princess> list();

    void delete(int id);

    boolean exist(int id);
}
