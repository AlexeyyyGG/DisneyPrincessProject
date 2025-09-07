package model;

import validator.PrincessValidator;

public class Princess {
    private int id;
    private String name;
    private int age;
    private HairColor hairColor;
    private EyeColor eyeColor;

    public Princess() {
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
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