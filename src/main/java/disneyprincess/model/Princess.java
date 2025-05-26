package disneyprincess.model;

import disneyprincess.utils.PrincessValidator;

public class Princess {
  private final int id;
  private final String name;
  private final int age;
  private final String hairColor;
  private final String eyeColor;

  public Princess(int id, String name, int age, String hairColor, String eyeColor) {
    PrincessValidator.validateId(id);
    PrincessValidator.validateName(name);
    PrincessValidator.validateAge(age);
    PrincessValidator.validateHairColors(hairColor);
    PrincessValidator.validateEyeColors(eyeColor);

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