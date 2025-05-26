package disneyprincess.utils;

public class PrincessValidator {
  public static final int MIN_NAME_LENGTH = 1;
  public static final int MAX_NAME_LENGTH = 30;
  public static final int MIN_AGE = 0;
  public static final int MAX_AGE = 120;

  public static void validateId(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Id не может быть меньше или равным 0");
    }
  }

  public static void validateName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Имя не может быть null");
    }
    int length = name.length();
    if (length < MIN_NAME_LENGTH || length > MAX_NAME_LENGTH) {
      throw new IllegalArgumentException(
          "Имя должно быть от " + MIN_NAME_LENGTH + " до " + MAX_NAME_LENGTH
              + " букв");
    }
  }

  public static void validateAge(int age) {
    if (age < MIN_AGE || age > MAX_AGE) {
      throw new IllegalArgumentException("Возраст должен быть от " + MIN_AGE + " до " +
          MAX_AGE + " лет");
    }
  }

  public static void validateHairColors(String hairColor) {
    if (hairColor == null) {
      throw new IllegalArgumentException("Цвет волос не может быть null");
    }
    String normalized = hairColor.trim().toUpperCase();
    try {
      HairColor.valueOf(normalized);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Недопустимый цвет волос" + hairColor);
    }
  }

  public static void validateEyeColors(String eyeColor) {
    if (eyeColor == null) {
      throw new IllegalArgumentException("Цвет глаз не может быть null");
    }
    String normalized = eyeColor.trim().toUpperCase();
    try {
      EyeColor.valueOf(normalized);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Недопустимый цвет глаз: " + eyeColor);
    }
  }
}
