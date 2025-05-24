package disneyprincess;

public class Princesses {

  private final int id;
  private final String name;
  private final int age;
  private final String hairColor;
  private final String eyeColor;

  public Princesses(int id, String name, int age, String hairColor, String eyeColor) {
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