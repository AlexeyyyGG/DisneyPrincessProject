package disneyprincess.model;

public enum HairColor {
    BLACK,
    BLONDE,
    PLATINUM_BLONDE,
    STRAWBERRY_BLONDE,
    RED,
    BROWN;
    private static final String NULL_COLOR = "Hair color cannot be null";
    private static final String INVALID_COLOR = "Inappropriate hair color: %s";

    public static HairColor fromString(String color) {
        if (color == null) {
            throw new IllegalArgumentException(NULL_COLOR);
        }
        for (HairColor displayColor : HairColor.values()) {
            if (displayColor.name().equalsIgnoreCase(color.trim())) {
                return displayColor;
            }
        }
        throw new IllegalArgumentException(String.format(INVALID_COLOR, color));
    }
}