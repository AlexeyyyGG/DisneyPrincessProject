package disneyprincess;

import java.util.ArrayList;
import java.util.List;

public class PrincessRepository {
  private final List<Princesses> princesses;

  public PrincessRepository() {
    this.princesses = new ArrayList<>();
  }

  public void addPrincess(Princesses princess) {
    this.princesses.add(princess);
  }
}
