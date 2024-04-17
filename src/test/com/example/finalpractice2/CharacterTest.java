package com.example.finalpractice2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

import static org.junit.Assert.*;

public class CharacterTest {
    static Random rand;
    public Character char1;
    static String[] skins = {"lighter", "darker"};
    static String[] lips = {"red", "grey", "pink"};
    static String[] hairLengths = {"long", "short"};
    static String[] hairColors = {"red", "dark-brown", "light-brown", "dark-blonde", "light-blonde"};
    static String [] eyeColors = {"green", "brown", "grey", "blue", "purple"};
    static String[] accessories = {"glasses", "earrings", "nosering", "tattoo"};

    @BeforeClass
    public static void setUp() throws Exception {
        rand = new Random();
    }

    @Before
    public void initialize(){
        char1 = new Character(skins[rand.nextInt(skins.length)],
                lips[rand.nextInt(lips.length)],
                hairLengths[rand.nextInt(hairLengths.length)],
                hairColors[rand.nextInt(hairColors.length)],
                eyeColors[rand.nextInt(eyeColors.length)],
                accessories[rand.nextInt(accessories.length)]);
    }

    @Test
    public void testSkin(){
        assertTrue(char1.checkMatch("skin", "lighter") || char1.checkMatch("skin", "darker"));
    }

    @Test
    public void setTarget() {
        assertFalse(char1.checkWin());
        char1.setTarget();
        assertTrue(char1.checkWin());

    }
}