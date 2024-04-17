package com.example.finalpractice2;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class GuessWhoController implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Label message;

    @FXML
    private Label guessNo;

    @FXML
    private HBox playField;

    static Random rand;
    static String[] skins = {"lighter", "darker"};
    static String[] lips = {"red", "grey", "pink"};
    static String[] hairLengths = {"long", "short"};
    static String[] hairColors = {"red", "dark-brown", "light-brown", "dark-blonde", "light-blonde"};
    static String [] eyeColors = {"green", "brown", "grey", "blue", "purple"};
    static String[] accessories = {"glasses", "earrings", "nosering", "tattoo"};


    private ArrayList<Character> characters;
    private ArrayList<StackPane> stacks;
    private ArrayList<Button> buttons;
    private Image no;
    private int target;
    private int guesses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        no = new Image(Character.class.getResourceAsStream("images/no.png"));
        getSlots();
        buttons = new ArrayList<>();
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        rand = new Random();
        reset();
    }

    public void reset(){
        guesses = 0;
        guessNo.setText(String.valueOf(guesses));
        message.setText("Take A Guess... Or Click On A Character To Guess They're The Target");
        characters = new ArrayList<Character>();
        mainMenu();
        target = rand.nextInt(21);
        for (int i = 0; i < 21; i++){
            boolean unique;
            do {
                unique = true;
                Character newChar = new Character(skins[rand.nextInt(skins.length)],
                        lips[rand.nextInt(lips.length)],
                        hairLengths[rand.nextInt(hairLengths.length)],
                        hairColors[rand.nextInt(hairColors.length)],
                        eyeColors[rand.nextInt(eyeColors.length)],
                        accessories[rand.nextInt(accessories.length)]);
                for (Character character : characters){
                    if (newChar.equivalent(character)){
                        unique = false;
                    }
                }
                if (unique){
                    if (i == target){
                        newChar.setTarget();
                    }
                    characters.add(newChar);
                }
            } while (!unique);
            drawChar(i);
            int finalI = i;
            stacks.get(i).setOnMouseClicked(mouseEvent -> {
                finalGuess(finalI);
            });
        }
    }


    public void getSlots(){
        stacks = new ArrayList<StackPane>();
        for (int i = 0; i<playField.getChildren().size(); i++){
            VBox vbox = (VBox) playField.getChildren().get(i);
            for (int j = 0; j < vbox.getChildren().size(); j++){
                StackPane stack = (StackPane) vbox.getChildren().get(j);
                stacks.add(stack);
            }
        }
    }

    public void drawChar(int i){
        StackPane stack = (StackPane) stacks.get(i);
        for (int j = 0; j < stack.getChildren().size(); j++){
            ImageView layer = (ImageView) stack.getChildren().get(j);
            if (j != 5){
                layer.setImage(characters.get(i).images.get(j));
            }
            else {
                layer.setImage(null);
            }

        }
    }

    public void finalGuess(int i){
        for (Button button : buttons){
            button.setVisible(false);
        }
        button1.setVisible(true);
        button1.setText("ACCUSE!");
        button2.setVisible(true);
        button2.setText("Nevermind");
        button1.setOnMouseClicked(mouseEvent -> {
            for (StackPane stack : stacks){
                stack.setOnMouseClicked(mouseEvent1 -> {});
            }
            button2.setVisible(false);
            if (characters.get(i).checkWin()){
                message.setText("You Win!");
                for (StackPane stack : stacks){
                    if (!stacks.get(i).equals(stack)) {
                        ImageView topImg = (ImageView) stack.getChildren().get(5);
                        topImg.setImage(no);
                    }
                }
            }
            else {
                message.setText("You Lose!");
                int j = 0;
                for (Character character : characters){
                    if (character.checkWin()){
                        j = characters.indexOf(character);
                    }
                }
                for (StackPane stack : stacks){
                    if (!stacks.get(j).equals(stack)) {
                        ImageView topImg = (ImageView) stack.getChildren().get(5);
                        topImg.setImage(no);
                    }
                }
            }
            button1.setText("Reset");
            button1.setOnMouseClicked(mouseEvent2 -> {
                reset();
            });
        });
        button2.setOnMouseClicked(mouseEvent -> {
            mainMenu();
        });
    }

    public void mainMenu(){
        for (Button button : buttons){
            button.setVisible(true);
            button1.setText("Skin Color");
            button1.setOnMouseClicked(mouseEvent -> {
                skinMenu();
            });
            button2.setText("Lip Color");
            button2.setOnMouseClicked(mouseEvent -> {
                lipMenu();
            });
            button3.setText("Hair Length");
            button3.setOnMouseClicked(mouseEvent -> {
                hairLengthMenu();
            });
            button4.setText("Hair Color");
            button4.setOnMouseClicked(mouseEvent -> {
                hairColorMenu();
            });
            button5.setText("Eye Color");
            button5.setOnMouseClicked(mouseEvent -> {
                eyeMenu();
            });
            button6.setText("Accessory");
            button6.setOnMouseClicked(mouseEvent -> {
                accesoryMenu();
            });
        }
    }

    public void skinMenu(){
        for (Button button : buttons){
            button.setVisible(false);
        }
        button1.setVisible(true);
        button1.setText("Back");
        button1.setOnMouseClicked(mouseEvent -> {
            mainMenu();
        });
        button2.setVisible(true);
        button2.setText("Darker");
        button2.setOnMouseClicked(mouseEvent -> {
            checkAttribute("skin", "darker");
        });
        button3.setVisible(true);
        button3.setText("Lighter");
        button3.setOnMouseClicked(mouseEvent -> {
            checkAttribute("skin", "lighter");
        });
    }

    public void lipMenu(){
        for (Button button : buttons){
            button.setVisible(false);
        }
        button1.setVisible(true);
        button1.setText("Back");
        button1.setOnMouseClicked(mouseEvent -> {
            mainMenu();
        });
        button2.setVisible(true);
        button2.setText("Red");
        button2.setOnMouseClicked(mouseEvent -> {
            checkAttribute("lips", "red");
        });
        button3.setVisible(true);
        button3.setText("Pink");
        button3.setOnMouseClicked(mouseEvent -> {
            checkAttribute("lips", "pink");
        });
        button4.setVisible(true);
        button4.setText("Grey");
        button4.setOnMouseClicked(mouseEvent -> {
            checkAttribute("lips", "grey");
        });
    }

    public void hairLengthMenu(){
        for (Button button : buttons){
            button.setVisible(false);
        }
        button1.setVisible(true);
        button1.setText("Back");
        button1.setOnMouseClicked(mouseEvent -> {
            mainMenu();
        });
        button2.setVisible(true);
        button2.setText("Long");
        button2.setOnMouseClicked(mouseEvent -> {
            checkAttribute("hairLength", "long");
        });
        button3.setVisible(true);
        button3.setText("Short");
        button3.setOnMouseClicked(mouseEvent -> {
            checkAttribute("hairLength", "short");
        });
    }

    public void hairColorMenu(){
        for (Button button : buttons){
            button.setVisible(false);
        }
        button1.setVisible(true);
        button1.setText("Back");
        button1.setOnMouseClicked(mouseEvent -> {
            mainMenu();
        });
        button2.setVisible(true);
        button2.setText("Red");
        button2.setOnMouseClicked(mouseEvent -> {
            checkAttribute("hairColor", "red");
        });
        button3.setVisible(true);
        button3.setText("Light Brown");
        button3.setOnMouseClicked(mouseEvent -> {
            checkAttribute("hairColor", "light-brown");
        });
        button4.setVisible(true);
        button4.setText("Dark Brown");
        button4.setOnMouseClicked(mouseEvent -> {
            checkAttribute("hairColor", "dark-brown");
        });
        button5.setVisible(true);
        button5.setText("Light Blonde");
        button5.setOnMouseClicked(mouseEvent -> {
            checkAttribute("hairColor", "light-blonde");
        });
        button6.setVisible(true);
        button6.setText("Dark Blonde");
        button6.setOnMouseClicked(mouseEvent -> {
            checkAttribute("hairColor", "dark-blonde");
        });

    }

    public void eyeMenu(){
        for (Button button : buttons){
            button.setVisible(false);
        }
        button1.setVisible(true);
        button1.setText("Back");
        button1.setOnMouseClicked(mouseEvent -> {
            mainMenu();
        });
        button2.setVisible(true);
        button2.setText("Blue");
        button2.setOnMouseClicked(mouseEvent -> {
            checkAttribute("eyeColor", "blue");
        });
        button3.setVisible(true);
        button3.setText("Brown");
        button3.setOnMouseClicked(mouseEvent -> {
            checkAttribute("eyeColor", "brown");
        });
        button4.setVisible(true);
        button4.setText("Grey");
        button4.setOnMouseClicked(mouseEvent -> {
            checkAttribute("eyeColor", "grey");
        });
        button5.setVisible(true);
        button5.setText("Purple");
        button5.setOnMouseClicked(mouseEvent -> {
            checkAttribute("eyeColor", "purple");
        });
        button6.setVisible(true);
        button6.setText("Green");
        button6.setOnMouseClicked(mouseEvent -> {
            checkAttribute("eyeColor", "green");
        });
    }

    public void accesoryMenu(){
        for (Button button : buttons){
            button.setVisible(false);
        }
        button1.setVisible(true);
        button1.setText("Back");
        button1.setOnMouseClicked(mouseEvent -> {
            mainMenu();
        });
        button2.setVisible(true);
        button2.setText("Earrings");
        button2.setOnMouseClicked(mouseEvent -> {
            checkAttribute("accessory", "earrings");
        });
        button3.setVisible(true);
        button3.setText("Nose Ring");
        button3.setOnMouseClicked(mouseEvent -> {
            checkAttribute("accessory", "nosering");
        });
        button4.setVisible(true);
        button4.setText("Glasses");
        button4.setOnMouseClicked(mouseEvent -> {
            checkAttribute("accessory", "glasses");
        });
        button5.setVisible(true);
        button5.setText("Tattoo");
        button5.setOnMouseClicked(mouseEvent -> {
            checkAttribute("accessory", "tattoo");
        });
    }
    
    public void checkAttribute(String attribute, String value){
        guesses += 1;
        guessNo.setText(String.valueOf(guesses));
        message.setText(characters.get(target).checkMatch(attribute, value) ? "Yes, the target does have that!" : "No, the target doesn't have that.");
        for (Button button : buttons){
            button.setVisible(false);
        }
        button1.setVisible(true);
        button1.setText("Ok");
        button1.setOnMouseClicked(mouseEvent -> {
            mainMenu();
        });
        for (int i = 0; i< characters.size(); i++){
            if (!characters.get(i).out){
                if (characters.get(target).checkMatch(attribute, value) ? !characters.get(i).checkMatch(attribute, value) : characters.get(i).checkMatch(attribute, value)){
                    characters.get(i).getKickedOut();
                    ImageView img = (ImageView) stacks.get(i).getChildren().get(5);
                    img.setImage(no);
                }
            }
        }
    }
}
