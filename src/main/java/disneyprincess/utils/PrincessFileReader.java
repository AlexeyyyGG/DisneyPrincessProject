package disneyprincess.utils;

import disneyprincess.model.Princess;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PrincessFileReader {
  public static List<Princess> readPrincessesFromFile(String disneyPrincesses) {
    List<Princess> princessList = new ArrayList<>();
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
        Princess princesses = new Princess(id, name, age, hairColor, eyeColor);
        princessList.add(princesses);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return princessList;
  }
}
