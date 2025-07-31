package validator;

import model.Princess;

public class PrincessValidator {
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 30;
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 120;
    private static final String ID_INVALID_MESSAGE = "Id cannot be less than or equal to 0";
    private static final String NAME_NULL_MESSAGE = "Name cannot be null";
    private static final String NAME_LENGTH_MESSAGE =
            "Name must be from " + MIN_NAME_LENGTH + " to " + MAX_NAME_LENGTH
                    + " letters";
    private static final String AGE_RANGE_MESSAGE = "Must be between " + MIN_AGE + " and " +
            MAX_AGE + " years of age";

    public static void validatePrincess(Princess princess) {
        validateId(princess.getId());
        validateName(princess.getName());
        validateAge(princess.getAge());
    }

    public static void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(ID_INVALID_MESSAGE);
        }
    }

    public static void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(NAME_NULL_MESSAGE);
        }
        int length = name.length();
        if (length < MIN_NAME_LENGTH || length > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_MESSAGE);
        }
    }

    public static void validateAge(int age) {
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new IllegalArgumentException(AGE_RANGE_MESSAGE);
        }
    }
}
