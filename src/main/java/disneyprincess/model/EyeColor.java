package disneyprincess.model;

public enum EyeColor {
  BROWN("Brown"),
  BLUE("Blue"),
  VIOLET("Violet"),
  HAZEL("Hazel");

  private final String displayEyeColor;

  EyeColor(String displayEyeColor) {
    this.displayEyeColor = displayEyeColor;
  }

  private String getDisplayEyeColor() {
    return displayEyeColor;
  }

  public static EyeColor fromString(String displayEyeColor) {
    if (displayEyeColor == null) {
      throw new IllegalArgumentException("Цвет глаз не может быть null");
    }
    for (EyeColor color : EyeColor.values()) {
      if (color.getDisplayEyeColor().equalsIgnoreCase(displayEyeColor.trim())) {
        return color;
      }
    }
    throw new IllegalArgumentException("Недопустимый цвет глаз: " + displayEyeColor);
  }
}


