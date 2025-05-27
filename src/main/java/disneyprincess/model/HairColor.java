package disneyprincess.model;

public enum HairColor {
  BLACK("Black"),
  BLONDE("Blonde"),
  PLATINUM_BLONDE("Platinum-blonde"),
  STRAWBERRY_BLONDE("Strawberry-blonde"),
  RED("Red"),
  BROWN("Brown");

  private final String displayHairColor;

  HairColor(String displayHairColor) {
    this.displayHairColor = displayHairColor;
  }

  private String getDisplayHairColor() {
    return displayHairColor;
  }

  public static HairColor fromString(String displayHairColor) {
    if (displayHairColor == null) {
      throw new IllegalArgumentException("Цвет волос не может быть null");
    }
    for (HairColor color : HairColor.values()) {
      if (color.getDisplayHairColor().equalsIgnoreCase(displayHairColor.trim())) {
        return color;
      }
    }
    throw new IllegalArgumentException("Недопустиый цвет волос " + displayHairColor);
  }
}