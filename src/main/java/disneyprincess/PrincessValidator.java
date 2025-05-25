package disneyprincess;

public class PrincessValidator {

  public static void validateId(int id) {
    if (id <= Princess.MIN_ID) {
      throw new IllegalArgumentException("Id не может быть меньше или равным " + Princess.MIN_ID);
    }
  }

  public static void validateName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Имя не может быть null");
    }
    int length = name.length();
    if (length < Princess.MIN_NAME_LENGTH || length > Princess.MAX_NAME_LENGTH) {
      throw new IllegalArgumentException(
          "Имя должно быть от " + Princess.MIN_NAME_LENGTH + " до " + Princess.MAX_NAME_LENGTH
              + " букв");
    }
  }

  public static void validateAge(int age) {
    if (age < Princess.MIN_AGE || age > Princess.MAX_AGE) {
      throw new IllegalArgumentException("Возраст должен быть от " + Princess.MIN_AGE + " до " +
          Princess.MAX_AGE + " лет");
    }
  }

  public static void validateHairColors(String hairColor) {
    if (hairColor == null || !Princess.HAIR_COLORS.contains(hairColor)) {
      throw new IllegalArgumentException("Недопустимый цвет волос: " + hairColor);
    }
  }

  public static void validateEyeColors(String eyeColor) {
    if (eyeColor == null || !Princess.EYE_COLORS.contains(eyeColor)) {
      throw new IllegalArgumentException("Недопустимый цвет глаз: " + eyeColor);
    }
  }
}
