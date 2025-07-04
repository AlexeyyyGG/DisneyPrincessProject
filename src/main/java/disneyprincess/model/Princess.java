package disneyprincess.model;

import disneyprincess.utils.PrincessValidator;

public class Princess {
    private final int id;
    private final String name;
    private final int age;
    private final HairColor hairColor;
    private final EyeColor eyeColor;

    public Princess(int id, String name, int age, HairColor hairColor, EyeColor eyeColor) {
        PrincessValidator.validateId(id);
        PrincessValidator.validateName(name);
        PrincessValidator.validateAge(age);
        this.id = id;
        this.name = name;
        this.age = age;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    @Override
    public String toString() {
        return "Princesses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hairColor='" + hairColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                '}';
    }
}