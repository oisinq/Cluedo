/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class allows users to input user name and pick which token they wish to play
 * it also decides which player goes first
 */
class GameSetup {

    int a = 0;

    private Counters Playable;
    private BufferedImage logo;


    GameSetup(Counters counters) {
        this.Playable = counters;
        playerCountSelect();
    }

    /**
     * Lets the player select the number of characters
     */
    private void playerCountSelect() {
        logo = null;
        try {
            logo = ImageIO.read(this.getClass().getResource("cluedo.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: cannot load board image.");
        }
        //the below two strings and jOptionPane are used to allow the user to pick how many users will play
        String[] playerCount = (new String[]{"2", "3", "4", "5", "6"});
        String userChoice = (String) JOptionPane.showInputDialog(null, "Select How Many Players You Want", "Cluedo", JOptionPane.QUESTION_MESSAGE, new ImageIcon(logo), playerCount, playerCount[0]);

        if (userChoice == null) {//if the user hits cancel the game is quit
            System.out.println("You have exited the game,Thanks For Playing!");
            System.exit(0);
        }

        //depending on what they pick this switch statement sends the correct number of players to character select
        switch (userChoice) {
            case "2":
                CharacterSelect(2);
                break;
            case "3":
                CharacterSelect(3);
                break;
            case "4":
                CharacterSelect(4);
                break;
            case "5":
                CharacterSelect(5);
                break;
            case "6":
                CharacterSelect(6);
                break;

        }
    }

    /**
     * Lets the player select which character to play as and enter their own name
     */
    private void CharacterSelect(int playerCount) {
        logo = null;
        try {
            logo = ImageIO.read(this.getClass().getResource("cluedo.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: cannot load board image.");
        }
        //this string stores all the possible characters
        String[] Characters = (new String[]{"Plum", "White", "Scarlet", "Green", "Mustard", "Peacock"});
        //this list will store the above string in list format , both are needed for the below loop
        List<String> CharacterList = new ArrayList<>(Arrays.asList(Characters));
        for (int i = 0; i < playerCount; i++) {
            String userName = (String) JOptionPane.showInputDialog(null, "Enter Your Username", "Cluedo", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(logo), null, "");
            if (userName == null) {//if the user cancels , exit the code
                System.out.println("You have exited the game,Thanks For Playing!");
                System.exit(0);

            }
            String userChoice = (String) JOptionPane.showInputDialog(null, "Player " + (i + 1) + " Please Select A Character", "Cluedo", JOptionPane.QUESTION_MESSAGE, new ImageIcon(logo), Characters, Characters[0]);
            if (userChoice == null) {//if the user cancels exit the code
                System.out.println("You have exited the game,Thanks For Playing!");
                System.exit(0);
            }
            CharacterList.remove(userChoice);//remove the player the user chose from the list
            Characters = CharacterList.toArray(new String[0]);//characters gets updated with the new list

            Playable.createCounter(userName, userChoice, 0);//send the username and their character choice to be created and placed on the board
        }

        int NPCCount = 0;
        while (NPCCount < Characters.length) {
            Playable.createNPC(null, Characters[NPCCount], 0);
            NPCCount++;
        }
    }
}