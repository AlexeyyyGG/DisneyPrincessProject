package disneyprincess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PrincessFileReader {
  public static List<Princesses> readPrincessesFromFile(String disneyPrincesses) {
    List<Princesses> princessList = new ArrayList<>();
    Path path = Path.of(disneyPrincesses);

    try {
      List<String> lines = Files.readAllLines(path);

      for (String line : lines) {
        String[] parts = line.split("\\|");
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1];
        int age = Integer.parseInt(parts[2].trim());
        String hairColor = parts[3];
        String eyeColor = parts[4];
        Princesses princesses = new Princesses(id, name, age, hairColor, eyeColor);
        princessList.add(princesses);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return princessList;
  }
}
