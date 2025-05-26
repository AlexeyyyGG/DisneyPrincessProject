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
}