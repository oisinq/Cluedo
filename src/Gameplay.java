/*  Cluedo - Sprint 3
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class stores all of the gameplay components so far - e.g. turns, movement, accessing rooms and entrances
 */
public class Gameplay {

    private GUI frame;
    private Rooms rooms;
    private Weapons weapons;
    private String[] play = new String[6];
    private boolean questionTriggered = false;
    private boolean questioningMode = false;
    private Question question;
    private boolean accusationTriggered = false;
    private int passStart = 0;
    private Accusation accusation;
    private int dieResult = 0;
    private int playTurn = 0;
    private int dieRoll = 0; //tracker used to stop more than one roll call per turn
    private int numPlayers = 0;
    private int numContendersRemaining;
    private String currentPlayerName;
    private boolean questionAsked = false;
    private boolean enteredRoom;     // This boolean checks that a counter only enters a room once per turn
    private boolean gameOver = false;

    /* This array "squareType" stores what kind of square each square on the board is, which determines if it can be accessed by counters
    Squares that are marked 0 are inaccessible by the player (they are out of bounds)
    Squares that are marked 1 are pathways that the player can walk on
    Sqaures that are marked 2 are pathway squares that are adjacent to room entrances - this is needed so we can exit rooms easily
    Sqaures that are marked 3 are squares inside rooms - they cannot be accessed directly
    Sqaures that are marked 4 are room squares that are adjacent to room entrances
    All negative squares are occupied by another user (e.g. -1 is an occupied pathway square */
    private int[][] squareType = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {3, 3, 3, 3, 3, 3, 0, 1, 1, 1, 3, 3, 3, 3, 1, 1, 1, 0, 3, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 4, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 1, 2, 4, 3, 3, 3, 3, 3, 3, 4, 2, 1, 2, 3, 3, 3, 3, 0, 0},
        {3, 3, 3, 3, 4, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {1, 1, 1, 1, 2, 1, 1, 1, 3, 4, 3, 3, 3, 3, 4, 3, 1, 1, 1, 1, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 4, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 3, 4, 2, 1, 3, 3, 3, 3, 3, 1, 1, 1, 3, 3, 3, 3, 4, 3, 0},
        {3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 2, 1, 2, 0, 0},
        {3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 1, 1, 1, 3, 3, 4, 3, 3, 0, 0},
        {3, 3, 3, 3, 3, 3, 4, 3, 1, 1, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 0},
        {0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 3, 3, 4, 3, 3, 1, 2, 4, 3, 3, 3, 3, 3, 3, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 0},
        {0, 1, 1, 1, 1, 1, 2, 1, 1, 3, 3, 4, 4, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 0, 0},
        {3, 3, 3, 3, 3, 3, 4, 1, 1, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 4, 2, 1, 2, 1, 1, 1, 1, 1, 0, 0},
        {3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 1, 1, 4, 3, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 0},
        {3, 3, 3, 3, 3, 3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 3, 3, 3, 3, 3, 3, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    Gameplay(GUI frame, Counters counters, Rooms rooms, Weapons weapons, ArrayList<Counter> players) {
        this.frame = frame;
        this.rooms = rooms;
        this.weapons = weapons;
        int counter = 0;

        // This enters each player into an array that tracks who's turn it is, and the order of turns
        ArrayList<Counter> init = new ArrayList<>();
        for (Counter currentCounter : players) {
            init.add(currentCounter);
            counter++;
            numPlayers++;
        }

        // We go through the counters separately, because we want to set all counter squares to occupied - not just the players
        for (Counter c : counters) {
            //We set the starting position of each counter to -1 so we know it's occupied
            squareType[c.getRow()][c.getColumn()] *= -1;
        }


        Counter highRoller;
        highRoller = rollForOrder(init);

        String[] temp = new String[6];
        int j = 0;

        // The highest roller goes first
        temp[0] = highRoller.getCharacterName();
        // We add everyone else in after that (making sure not to have the highRoller in twice)
        for (int i = 1; i < counter; i++) {
            if (temp[0].equals(init.get(j).getCharacterName())) {
                i--;
            } else {
                temp[i] = init.get(j).getCharacterName();
            }
            j++;
        }

        int x = 0;
        // We copy temp to play
        for (String p : temp) {
            play[x++] = p;
        }
        Cards cards = new Cards();
        cards.Envelope();
        cards.CardHolder(play, 18 / numPlayers, numPlayers);
        // This is the current player

        currentPlayerName = play[0];
        numContendersRemaining = numPlayers;
        // We display the help command in the infoField and start the first turn
        turn();
    }

    /**
     * Checks if a string is an integer
     */

    private static boolean checkInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        char c;
        while (i < length) {
            c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * This lets a list of players roll, and returns the highest roller
     * It's recursive in cases where there is a tie for 1st
     */
    private Counter rollForOrder(ArrayList<Counter> players) {
        Dice die = new Dice();
        ArrayList<Counter> topPlayers = new ArrayList<>();

        int highest = 0, tiedFor = 0;
        // Goes through the players and lets each of them roll, and tracks the highest value
        for (Counter player : players) {
            player.setRollForOrder(die.roll() + die.roll());
            if (player.getRollForOrder() > highest) {
                highest = player.getRollForOrder();
            }
        }

        // Counts how many people have the highest roll, and adds them to the arraylist "players"
        for (Counter player : players) {
            if (player.getRollForOrder() == highest) {
                tiedFor++;
                topPlayers.add(player);
            }
        }

        // If multiple people are tied, we repeat the function with only the highest rollers
        if (tiedFor > 1) {
            return rollForOrder(topPlayers);
        } else {
            // Otherwise, we return the highest roller
            return topPlayers.get(0);
        }
    }

    /**
     * This method runs when we setVisible(true) and when we repaint()
     * It paints the images and counters onto the board
     */
    private void turn() {
        // This checks who's turn it currently is, and sets up the required variables for the turn
        switch (play[playTurn]) {
            case "Scarlet":
                currentPlayerName = "Scarlet";
                break;
            case "Mustard":
                currentPlayerName = "Mustard";
                break;
            case "Peacock":
                currentPlayerName = "Peacock";
                break;
            case "Plum":
                currentPlayerName = "Plum";
                break;
            case "White":
                currentPlayerName = "White";
                break;
            case "Green":
                currentPlayerName = "Green";
                break;
        }

        Counter c = Counters.get(currentPlayerName);

        if (c.lostGame) {
            playTurn = (playTurn + 1) % numPlayers;
            switch (play[playTurn]) {
                case "Scarlet":
                    currentPlayerName = "Scarlet";
                    break;
                case "Mustard":
                    currentPlayerName = "Mustard";
                    break;
                case "Peacock":
                    currentPlayerName = "Peacock";
                    break;
                case "Plum":
                    currentPlayerName = "Plum";
                    break;
                case "White":
                    currentPlayerName = "White";
                    break;
                case "Green":
                    currentPlayerName = "Green";
                    break;
            }

            c = Counters.get(currentPlayerName);
        }

        // We reset this boolean every turn
        enteredRoom = false;
        frame.resetInfoField();
        helpCommand();

        if (passStart == 0) {
            frame.appendText("Players will be playing in the order of:");
            for (String s : play) {
                if (s != null) {
                    frame.appendText(s + " ");
                }
            }
            frame.appendText("");
        }
        passStart = 1;
        questionAsked = false;
        frame.appendText(currentPlayerName + " (" + Counters.get(currentPlayerName).getUserName() + ") has started their turn\n");

        // If the counter is in a room, it should list the room exits
        if (c != null && isRoom(c)) {
            listExits(c);
        }
    }

    /**
     * This checks if the counter is currently in a room or not
     */
    private boolean isRoom(Counter counter) {
        // All room squares have a number value in squareType of >= 3 or <= -3
        return squareType[counter.getRow()][counter.getColumn()] <= -3 || squareType[counter.getRow()][counter.getColumn()] >= 3;
    }

    /**
     * This prints off the possible exits for a counter to leave a room through
     */
    private void listExits(Counter counter) {
        Room room = counter.getCurrentRoom();
        ArrayList<Coordinates> entrances = room.getEntrances();

        // Prints entrances
        frame.appendText("Type the entrance number to leave through that entrance.");
        for (int i = 0; i < entrances.size(); i++) {
            Coordinates current = entrances.get(i);
            // If it's a secret passageway, we set the coordinates to (-1, -1) to make things easier for ourselves - if that's the case, we don't want to print the actual coordinates
            if (current.getRow() > -1) {
                frame.appendText("Entrance " + (i + 1) + ": Row " + current.getRow() + ", column " + current.getColumn());
            } else {
                // If it's a secret entrance, we say where the secret entrance goes to
                frame.appendText("Entrance " + (i + 1) + ": Secret passageway to ");
                switch (counter.getCurrentRoom().getRoomName()) {
                    case "Conservatory":
                        frame.appendText("Lounge");
                        break;
                    case "Lounge":
                        frame.appendText("Conservatory");
                        break;
                    case "Kitchen":
                        frame.appendText("Study");
                        break;
                    case "Study":
                        frame.appendText("Kitchen");
                        break;
                }
            }
        }
    }

    /**
     * This finds the current entrance that the user is placed at, and assigns the corresponding current room to the counter
     */
    private void findCurrentRoom(Counter counter) {
        Coordinates coord = new Coordinates(counter.getColumn(), counter.getRow());
        squareType[counter.getRow()][counter.getColumn()] *= -1;

        // This searches through all of the rooms and entrances to find the current room
        for (Room r : rooms) {
            ArrayList<Coordinates> entrances = r.getEntrances();
            for (Coordinates entrance : entrances) {
                if ((coord.getColumn() == entrance.getColumn()) && (coord.getRow() == entrance.getRow())) {
                    counter.setCurrentRoom(r);
                }
            }
        }
    }

    /**
     * Moves the counter to the next available spot in the centre of the room
     */
    private void moveToRoomCentre(Counter c) {
        Room room = c.getCurrentRoom();
        Coordinates location = null;
        boolean found = false;

        ArrayList<Coordinates> tokenSquares = room.getTokenSquares();

        for (Coordinates tokenSquare : tokenSquares) {
            // Since occupied squares are negative, we check if the corresponding value in squareType is positive
            if (tokenSquare.getColumn() == c.getColumn() && tokenSquare.getRow() == c.getRow()) {
                System.out.println("Already on a token square!");
                return;
            }
        }

        // This searches through the tokenSquares arraylist to find one that isn't occupied
        for (int i = 0; i < tokenSquares.size() && !found; i++) {
            // Since occupied squares are negative, we check if the corresponding value in squareType is positive
            if (squareType[tokenSquares.get(i).getRow()][tokenSquares.get(i).getColumn()] > 0) {
                location = tokenSquares.get(i);
                found = true;
            }
        }
        c.setRowColumn(location.getRow(), location.getColumn());
        // This shows that the current square is occupied
        squareType[location.getRow()][location.getColumn()] *= -1;
        // If you've moved to the centre of a room, you can't move again, so we set the dieResult to 0
        dieResult = 0;
    }

    /**
     * Moves the weapon into the room
     */
    private void moveWeaponToRoom(Weapon w, Room r) {
        Room Room = w.getCurrRoom();//the room where the weapon is to begin with
        Boolean found = false;
        if (Room == r) {
            //Already in the correct room
            return;
        }
        for (Weapon hld : weapons) {
            if (hld.getCurrRoom().getRoomName().equals(r.getRoomName())) {
                Room tmpRoom = w.getCurrRoom();
                w.setCurrentRoom(r);
                hld.setCurrentRoom(tmpRoom);
                //room is already occupied
                found = true;
                break;
            }
        }
        if (!found) {
            //means nothing is in that room
            w.setCurrentRoom(r);
        }
    }


    /**
     * Checks if you can enter a certain room or not by looking at both the current square and the next square
     */
    private boolean isEnterable(Counter counter, String direction) {
        int nextSquareType;
        int currentSquareType = squareType[counter.getRow()][counter.getColumn()];

        // If a room is enterable, then you must be currently standing in an entrance and trying to enter a room with square type "4"
        // (This is the type given to a square across from an entrance)
        switch (direction) {
            case "u":
                nextSquareType = squareType[counter.getRow() - 1][counter.getColumn()];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
            case "d":
                nextSquareType = squareType[counter.getRow() + 1][counter.getColumn()];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
            case "l":
                nextSquareType = squareType[counter.getRow()][counter.getColumn() - 1];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
            case "r":
                nextSquareType = squareType[counter.getRow()][counter.getColumn() + 1];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Checks if the next square is a pathway
     */
    private boolean isPathway(Counter counter, String direction) {
        int nextSquareType;
        int currentSquareType = squareType[counter.getRow()][counter.getColumn()];

        // If you're currently standing in a room, return false
        // Otherwise, if your next square is a pathway, return true
        switch (direction) {
            case "u":
                nextSquareType = squareType[counter.getRow() - 1][counter.getColumn()];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
            case "d":
                nextSquareType = squareType[counter.getRow() + 1][counter.getColumn()];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
            case "l":
                nextSquareType = squareType[counter.getRow()][counter.getColumn() - 1];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
            case "r":
                nextSquareType = squareType[counter.getRow()][counter.getColumn() + 1];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Checks if the next square is occupied by another square
     */
    private boolean isNotOccupied(Counter counter, String direction) {
        int nextSquareType;

        // If the nextSquare is negative, that means it's occupied, so return false
        switch (direction) {
            case "u":
                nextSquareType = squareType[counter.getRow() - 1][counter.getColumn()];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
            case "d":
                nextSquareType = squareType[counter.getRow() + 1][counter.getColumn()];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
            case "l":
                nextSquareType = squareType[counter.getRow()][counter.getColumn() - 1];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
            case "r":
                nextSquareType = squareType[counter.getRow()][counter.getColumn() + 1];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
        }
        return true;
    }


    /**
     * Reads text from userInput and interprets text accordingly
     */
    public void interpretInput() {
        String inputtedText = frame.getUserInput().getText();//takes info from the field
        frame.getUserInput().setText("");//wipes the field after

        frame.appendText(">" + inputtedText);//puts it into the panel
        String splitStr = inputtedText.trim().replaceAll(" +", " ");
        String command = splitStr.toLowerCase();
        Counter player = Counters.get(currentPlayerName);

        if (command.equals("quit")) {
            frame.appendText("Thank you for playing! Goodbye");
            System.exit(0);
        }

        if (gameOver) {
            frame.appendText("The game is over!");
            return;
        }

        if (command.equals("help")) {
            helpCommand();
        } else if (command.equals("log")) {
            String log = frame.getLog();
            frame.appendText(log);
        } else if (command.equals("cheat")) {
            frame.appendText("The murder was committed by " + Envelope.getPerson().getName() + " in the " + Envelope.getRoom().getName() + " with the " + Envelope.getWeapon().getName());
        } else if (questioningMode) { // This if loop is triggered when a question has been defined and you're checking which players have cards
            if (question.questioning(command)) { // questioning() returns true when the questioning is completed
                questioningMode = false;
                dieResult = 0;
                questionAsked = true;
            }
        } else if (command.equals("done") && !questionTriggered) {
            dieResult = 0;
            dieRoll = 0;
            frame.appendText(currentPlayerName + "'s (" + Counters.get(currentPlayerName).getUserName() + ") turn has ended!\n");
            playTurn = (playTurn + 1) % numPlayers;
            // Goes to the next players move
            turn();
        } else if (questionTriggered) { // This if statement is used to build the question
            // defineQuestion returns false when the question is completely defined
            questionTriggered = question.defineQuestion(command);
            if (!questionTriggered) { // This is triggered if the question is completely defined, which starts the next step
                questioningMode = true;
                // If you're questioning a counter that isn't yourself, move it to the current room (as well as the weapon)
                if (question.getCounter() != Counters.get(currentPlayerName)) {
                    squareType[question.getCounter().getRow()][question.getCounter().getColumn()] *= -1;
                    moveToRoomCentre(question.getCounter());
                }
                moveWeaponToRoom(question.getWeapon(), question.getRoom());
                question.confirmHandoff();
                frame.repaint();
            }
        } else if (accusationTriggered) { // This if statement is used to build the accusation
            accusationTriggered = accusation.createAccusation(command);
            if (accusation.accusationCreated) {
                if (accusation.checkAccusation()) {
                    gameOver = true;
                } else {
                    Counters.get(currentPlayerName).lostGame = true;
                    numContendersRemaining--;
                    turn();

                    if (numContendersRemaining == 1) { // If there's only one player remaining, the last remaining player wins
                        turn();
                        frame.appendText("Congratulations, " + currentPlayerName + " (" + Counters.get(currentPlayerName).getUserName() + ") wins!");
                        BufferedImage youWin = null;
                        try {
                            youWin = ImageIO.read(this.getClass().getResource("youWin.png"));
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Error: cannot load win image.");
                        }

                        JOptionPane.showMessageDialog(null, "Congratulations, " + currentPlayerName + " (" + Counters.get(currentPlayerName).getUserName() + ") wins!","Cludeo", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(youWin));
                        gameOver = true;
                    } else { // Otherwise, we just remove the losing player from the game, and go on to the next player
                        dieResult = 0;
                        dieRoll = 0;
                        frame.appendText(currentPlayerName + "'s (" + Counters.get(currentPlayerName).getUserName() + ") turn has ended!\n");
                        playTurn = (playTurn + 1) % numPlayers;
                        // Goes to the next players move
                        turn();
                    }
                }
            }
        } else if (command.equals("accuse") && player.getCurrentRoom().getRoomName().equals("Cellar")) { // You can only make accusations in the cellar
            accusationTriggered = true;
            accusation = new Accusation(player, frame);
        } else if (command.equals("question") && player.getCurrentRoom() != null && !player.getCurrentRoom().getRoomName().equals("Cellar")) { // Questions can't be made in the cellar!
            if (questionAsked) {
                frame.appendText("You can only ask one question per turn!");
            } else {
                questionTriggered = true;
                question = new Question(player, frame, play);
            }
        } else if (command.equals("notes")) {
            Counter c = Counters.get(currentPlayerName);
            frame.appendText(c.getNotesString());
        }
        // Checks if the command is a movement direction
        else if ((command.equals("u") || command.equals("up") || command.equals("d") || command.equals("down") || command.equals("l") || command.equals("left") || command.equals("r") || command.equals("right"))) {
            if (dieResult > 0) {
                if (moveCommand(splitStr, currentPlayerName)) {
                    dieResult = dieResult - 1;
                }
            } else if (dieRoll == 0) {
                // If you haven't rolled yet, we display this message
                frame.appendText("You must roll before you move.");
            } else {
                frame.appendText("You have used all your movement.");
            }
        }
        // This rolls the dice
        else if (command.equals("roll")) {
            if (dieRoll == 0) {
                Dice die = new Dice();
                // There are two dice, so we roll twice
                dieResult = die.roll() + die.roll();
                frame.appendText("You rolled a " + dieResult);
                dieRoll++;
            } else {
                frame.appendText("You have already rolled this turn!");
            }
        } else if (checkInteger(command)) {
            if (dieRoll == 0) {
                frame.appendText("You must roll before you move.");
            } else if (dieResult > 0 && !enteredRoom && isRoom(Counters.get(currentPlayerName))) {
                selectEntrance(command);
            } else {
                frame.appendText("You cannot move here.");
            }
        } else {
            frame.appendText("Invalid command entered!");
        }
    }

    /**
     * Lets the user select an entrance and moves correspondingly
     */
    private void selectEntrance(String splitStr) {
        Counter c = Counters.get(currentPlayerName);
        if (c == null) {
            throw new RuntimeException("Counter does not exist");
        }

        Room r = c.getCurrentRoom();

        // We do this so the current square is no longer recorded as occupied
        squareType[c.getRow()][c.getColumn()] *= -1;
        boolean entranceSelected;
        boolean moved = false;
        boolean secretEntrance = false;

        // Checks which entrance is selected
        // If the selected entrance doesn't exist, we display an error
        switch (splitStr.toLowerCase()) {
            case "1":
                try {
                    c.setRowColumn(r.getEntrances().get(0).getRow(), r.getEntrances().get(0).getColumn());
                    entranceSelected = true;
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    frame.appendText("Select a valid entrance!");
                    entranceSelected = false;
                }
                break;
            case "2":
                try {
                    c.setRowColumn(r.getEntrances().get(1).getRow(), r.getEntrances().get(1).getColumn());
                    entranceSelected = true;
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    frame.appendText("Select a valid entrance!");
                    entranceSelected = false;
                }
                break;
            case "3":
                try {
                    c.setRowColumn(r.getEntrances().get(2).getRow(), r.getEntrances().get(2).getColumn());
                    entranceSelected = true;
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    frame.appendText("Select a valid entrance!");
                    entranceSelected = false;
                }
                break;
            case "4":
                try {
                    c.setRowColumn(r.getEntrances().get(3).getRow(), r.getEntrances().get(3).getColumn());
                    entranceSelected = true;
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    frame.appendText("Select a valid entrance!");
                    entranceSelected = false;
                }
                break;

            default:
                frame.appendText("Select a valid entrance!");
                entranceSelected = false;
                break;
        }
        // If the co-ordinates are negative, then I know it's a secret passageway - I can then swap the room
        if (c.getRow() == -1) {
            String room = c.getCurrentRoom().getRoomName();
            secretEntrance = true;
            switch (room) {
                case "Conservatory":
                    c.setCurrentRoom(Rooms.get("Lounge"));
                    moved = true;
                    break;
                case "Lounge":
                    c.setCurrentRoom(Rooms.get("Conservatory"));
                    moved = true;
                    break;
                case "Kitchen":
                    c.setCurrentRoom(Rooms.get("Study"));
                    moved = true;
                    break;
                case "Study":
                    c.setCurrentRoom(Rooms.get("Kitchen"));
                    moved = true;
                    break;
            }
            // After swapping the room, the counter needs to be moved to the centre-point
            moveToRoomCentre(c);
        } else if (entranceSelected) {
            // Otherwise if an entrance is selected, we need to find the proper square on the pathway to move to
            // This will have squareType = 2, since this number is reserved for pathway squares beside an entrance
            int col = c.getColumn();
            int row = c.getRow();
            if (squareType[row - 1][col] == 2) {
                c.setRowColumn(row - 1, col);
                moved = true;
            }
            if (squareType[row + 1][col] == 2) {
                c.setRowColumn(row + 1, col);
                moved = true;
            }
            if (squareType[row][col - 1] == 2) {
                c.setRowColumn(row, col - 1);
                moved = true;
            }
            if (squareType[row][col + 1] == 2) {
                c.setRowColumn(row, col + 1);
                moved = true;
            }
        }

        // If you've selected an entrance and successfully moved, we can repaint and set the new square to "occupied"
        if (entranceSelected && moved) {
            if (!secretEntrance) {
                squareType[c.getRow()][c.getColumn()] *= -1;
            }
            frame.repaint();
            dieResult--;
        } else {
            // If we haven't moved, then we return to the centre of the original room
            // We need to use a temporary variable to store the dieResult because moveToRoomCentre resets dieResult to 0
            int tempResult = dieResult;
            moveToRoomCentre(c);
            dieResult = tempResult;
            // We now set our current square to "occupied" and display an error
            squareType[c.getRow()][c.getColumn()] *= -1;
            frame.appendText("Entrance is blocked - please select another entrance.");
        }

        // If the current square is a pathway, then set the room to null
        if (squareType[c.getRow()][c.getColumn()] == -1 || squareType[c.getRow()][c.getColumn()] == -2) {
            c.setCurrentRoom(null);
        }
    }

    /**
     * This method lets the user make standard moves e.g. up/down/left/right
     */
    private boolean moveCommand(String splitStr, String colour) {
        // We can only move if the next square is either a pathway or is a room square adjacent to an entrance
        boolean moved = false;

        Counter counter = Counters.get(colour);
        if (counter == null) {
            throw new RuntimeException();
        }

        switch (splitStr.toLowerCase()) { // Checks the movement direction entered
            case "up":
            case "u":
                if (counter.getRow() > 0 && (isPathway(counter, "u") || isEnterable(counter, "u")) && isNotOccupied(counter, "u")) {
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    counter.moveUp(1);
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    moved = true;
                }
                break;
            case "down":
            case "d":
                if ((isPathway(counter, "d") || isEnterable(counter, "d")) && isNotOccupied(counter, "d")) {
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    counter.moveDown(1);
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    moved = true;
                }
                break;
            case "left":
            case "l":
                if (counter.getColumn() > 0 && (isPathway(counter, "l") || isEnterable(counter, "l")) && isNotOccupied(counter, "l")) {
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    counter.moveLeft(1);
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    moved = true;
                }
                break;
            case "right":
            case "r":
                if ((isPathway(counter, "r") || isEnterable(counter, "r")) && isNotOccupied(counter, "r")) {
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    counter.moveRight(1);
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    moved = true;
                }
                break;
            default:
                frame.appendText("Invalid direction chosen.");
                moved = false;
        }

        if (!moved) {
            frame.appendText("Error - unable to make this move.");
        }

        // If the counter is now in a room, it finds what room the counter is in and moves it to the centre of that room
        if (isRoom(counter) && moved) {
            int tmp;
            // We keep the dieResult because you may still be allowed to make a move and moveToRoomCentre resets dieResult to 0
            tmp = dieResult;
            findCurrentRoom(counter);
            moveToRoomCentre(counter);
            dieResult = tmp;
            enteredRoom = true;
            if (counter.getCurrentRoom() == Rooms.get("Cellar")) {
                frame.appendText("You have entered the " + counter.getCurrentRoom().getRoomName() + ".  You can move again in your next turn. You can now enter `accuse` to make an accusation.");
            } else {
                frame.appendText("You have entered the " + counter.getCurrentRoom().getRoomName() + ".  You can move again in your next turn. You can now enter `question` to ask a question.");
            }
        }

        frame.repaint(); // Repaints the board with the new location of the pieces
        return moved;
    }

    /**
     * Displays a help message in the infoField
     */
    private void helpCommand() {
        frame.appendText("Commands:\nMove Player Piece:\n - letter corresponding to direction of movement e.g u/d/l/r \n" +
            "\nEnd Turn\n - \"done\"\n\nQuit Game\n - \"quit\"\n\n Roll Dice\n - \"roll\"\n\nLook at Notes\n - \"notes\"\n");
    }
}