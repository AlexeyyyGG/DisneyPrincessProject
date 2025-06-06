package disneyprincess.model;

public enum EyeColor {
    BROWN,
    BLUE,
    VIOLET,
    HAZEL;
    private static final String NULL_COLOR = "Eye color cannot be null";
    private static final String INVALID_COLOR = "Unacceptable eye color ";

    public static EyeColor fromString(String color) {
        if (color == null) {
            throw new IllegalArgumentException(NULL_COLOR);
        }
        for (EyeColor dispayColor : EyeColor.values()) {
            if (dispayColor.name().equalsIgnoreCase(color.trim())) {
                return dispayColor;
            }
        }
        throw new IllegalArgumentException(INVALID_COLOR + color);
    }
}


