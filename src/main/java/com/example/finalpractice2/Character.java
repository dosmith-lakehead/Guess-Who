package com.example.finalpractice2;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Character {
    private String skin;
    private String lips;
    private String hairLength;
    private String hairColor;
    private String eyeColor;
    private String accessory;
    private boolean target;

    public boolean out;
    public ArrayList<Image> images;

    private void setSkin(String skin) {
        if (skin.equals("darker") || skin.equals("lighter")){
            this.skin = skin;
        }
        else {
            throw new IllegalArgumentException("Skin must be 'lighter' or 'darker'.");
        }
    }

    private void setHairLength(String hairLength) {
        if (hairLength.equals("long") || hairLength.equals("short")){
            this.hairLength = hairLength;
        }
        else {
            throw new IllegalArgumentException("Hair must be 'short' or 'long'.");
        }
    }

    private void setHairColor(String hairColor) {
        if (hairColor.equals("red") || hairColor.equals("light-brown") || hairColor.equals("light-blonde") || hairColor.equals("dark-brown") || hairColor.equals("dark-blonde")){
            this.hairColor = hairColor;
        }
        else {
            throw new IllegalArgumentException("Hair must be 'red', 'light-brown', 'light-blonde', 'dark-brown' or 'dark-blonde'");
        }
    }

    private void setEyeColor(String eyeColor) {
        if (eyeColor.equals("brown") || eyeColor.equals("green") || eyeColor.equals("blue") || eyeColor.equals("purple") || eyeColor.equals("grey")){
            this.eyeColor = eyeColor;
        }
        else {
            throw new IllegalArgumentException("Eye color must be 'brown', 'green', 'blue', 'purple' or 'grey'");
        }
    }

    private void setAccessory(String accessory) {
        if (accessory.equals("earrings") || accessory.equals("nosering") || accessory.equals("glasses") || accessory.equals("tattoo")){
            this.accessory = accessory;
        }
        else {
            throw new IllegalArgumentException("Valid accessories are 'earrings', 'nosering', 'glasses' or 'tattoo'.");
        }
    }

    private void setLips(String lips){
        if (lips.equals("red") || lips.equals("grey") || lips.equals("pink")){
            this.lips = lips;
        }
        else {
            throw new IllegalArgumentException("Lips can only be 'red', 'grey' or pink'.");
        }
    }

    public Character(String skin, String lips, String hairLength, String hairColor, String eyeColor, String accessory) {
        this.images = new ArrayList<Image>();
        this.out = false;
        setSkin(skin);
        setLips(lips);
        setHairLength(hairLength);
        setHairColor(hairColor);
        setEyeColor(eyeColor);
        setAccessory(accessory);
        this.images.add(new Image(Character.class.getResourceAsStream("images/skin/" + skin + ".png")));
        this.images.add(new Image(Character.class.getResourceAsStream("images/lips/" + lips + ".png")));
        this.images.add(new Image(Character.class.getResourceAsStream("images/eyes/" + eyeColor + ".png")));
        this.images.add(new Image(Character.class.getResourceAsStream("images/hair/" + hairLength + "/" + hairColor + ".png")));
        this.images.add(new Image(Character.class.getResourceAsStream("images/accessory/" + accessory + ".png")));
        this.target = false;
    }

    public void setTarget() {
        this.target = true;
    }

    public boolean checkMatch(String property, String value) {
        switch (property) {
            case "skin":
                return (this.skin.equals(value));
            case "lips":
                return (this.lips.equals(value));
            case "hairLength":
                return (this.hairLength.equals(value));
            case "hairColor":
                return (this.hairColor.equals(value));
            case "eyeColor":
                return (this.eyeColor.equals(value));
            case "accessory":
                return (this.accessory.equals(value));
            default:
                return false;
        }
    }

    public boolean equivalent(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Character compared = (Character) o;
        return (skin.equals(compared.skin) &&
                lips.equals(compared.lips) &&
                hairLength.equals(compared.hairLength) &&
                hairColor.equals(compared.hairColor) &&
                eyeColor.equals(compared.eyeColor) &&
                accessory.equals(compared.accessory));
    }

    public void getKickedOut(){
        this.out = true;
    }

    public boolean checkWin()
    {
        return this.target;

    }
}
