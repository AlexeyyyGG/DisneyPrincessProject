package disneyprincess.utils;

public class PrincessValidator {
  public static final int MIN_NAME_LENGTH = 1;
  public static final int MAX_NAME_LENGTH = 30;
  public static final int MIN_AGE = 0;
  public static final int MAX_AGE = 120;

  public static void validateId(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Id cannot be less than or equal to 0");
    }
  }

  public static void validateName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    int length = name.length();
    if (length < MIN_NAME_LENGTH || length > MAX_NAME_LENGTH) {
      throw new IllegalArgumentException(
          "Name must be from " + MIN_NAME_LENGTH + " to " + MAX_NAME_LENGTH
              + " letters");
    }
  }

  public static void validateAge(int age) {
    if (age < MIN_AGE || age > MAX_AGE) {
      throw new IllegalArgumentException("Must be between " + MIN_AGE + " and " +
          MAX_AGE + " years of age");
    }
  }
}
