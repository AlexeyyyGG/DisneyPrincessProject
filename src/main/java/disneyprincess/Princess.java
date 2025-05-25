package disneyprincess;

import java.util.Set;

public class Princess {

  public static final int MIN_ID = 1;
  public static final int MIN_NAME_LENGTH = 1;
  public static final int MAX_NAME_LENGTH = 30;
  public static final int MIN_AGE = 0;
  public static final int MAX_AGE = 120;
  public static final Set<String> HAIR_COLORS = Set.of("Black", "Blonde", "Platinum-blonde",
      "Strawberry-blonde", "Red", "Brown");
  public static final Set<String> EYE_COLORS = Set.of("Brown", "Blue", "Violet", "Hazel");
  private final int id;
  private final String name;
  private final int age;
  private final String hairColor;
  private final String eyeColor;

  public Princess(int id, String name, int age, String hairColor, String eyeColor) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.hairColor = hairColor;
    this.eyeColor = eyeColor;
  }

  @Override
  public String toString() {
    return "Princesses{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", hairColor='" + hairColor + '\'' +
        ", eyeColor='" + eyeColor + '\'' +
        '}';
  }
}