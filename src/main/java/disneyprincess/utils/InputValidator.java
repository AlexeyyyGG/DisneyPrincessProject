package disneyprincess.utils;

public class InputValidator {
    public static int validateId(String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format: " + idStr);
        }
        return id;
    }

    public static String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        return name;
    }

    public static int validateAge(String ageStr) {
        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid age format: " + ageStr);
        }
        return age;
    }
}
