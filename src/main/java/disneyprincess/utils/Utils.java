package disneyprincess.utils;

public class Utils {
    private static final String INVALID_ID_FORMAT = "Invalid ID format: %s";
    private static final String INVALID_AGE_FORMAT = "Invalid age format: %s";

    public static int parseId(String idStr) {
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(INVALID_ID_FORMAT, idStr));
        }
        return id;
    }

    public static int parseAge(String ageStr) {
        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(INVALID_AGE_FORMAT, ageStr));
        }
        return age;
    }
}
