/*  Cluedo - Sprint 2
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import java.util.ArrayList;

public class Gameplay {

    private GUI frame;
    private Counters counters;
    private Rooms rooms;
    private String[] play =new String[6];
    private int dieResult=0;
    private int PlayTurn=0;
    private int dieRoll=0;//tracker used to stop more than one roll call per turn
    private int turnTrack=0;
    private String currentPlayerName;

    private int[][] squareType = {
            {0,0,0,0,0,0,0,0,0,-1,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0},
            {3,3,3,3,3,3,0,1,1,1,3,3,3,3,1,1,1,0,3,3,3,3,3,3,0},
            {3,3,3,3,3,3,1,1,3,3,3,3,3,3,3,3,1,1,3,3,3,3,3,3,0},
            {3,3,3,3,3,3,1,1,3,3,3,3,3,3,3,3,1,1,3,3,3,3,3,3,0},
            {3,3,3,3,3,3,1,1,3,3,3,3,3,3,3,3,1,1,4,3,3,3,3,3,0},
            {3,3,3,3,3,3,1,2,4,3,3,3,3,3,3,4,2,1,2,3,3,3,3,0,0},
            {3,3,3,3,4,3,1,1,3,3,3,3,3,3,3,3,1,1,1,1,1,1,1,-1,0},
            {1,1,1,1,2,1,1,1,3,4,3,3,3,3,4,3,1,1,1,1,1,1,1,0,0},
            {0,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,3,3,3,3,3,3,0},
            {3,3,3,3,3,1,1,1,1,1,1,1,1,1,1,1,1,2,4,3,3,3,3,3,0},
            {3,3,3,3,3,3,3,3,1,1,3,3,3,3,3,1,1,1,3,3,3,3,3,3,0},
            {3,3,3,3,3,3,3,3,1,1,3,3,3,3,3,1,1,1,3,3,3,3,3,3,0},
            {3,3,3,3,3,3,3,4,2,1,3,3,3,3,3,1,1,1,3,3,3,3,4,3,0},
            {3,3,3,3,3,3,3,3,1,1,3,3,3,3,3,1,1,1,1,1,2,1,2,0,0},
            {3,3,3,3,3,3,3,3,1,1,3,3,3,3,3,1,1,1,3,3,4,3,3,0,0},
            {3,3,3,3,3,3,4,3,1,1,3,3,3,3,3,1,1,3,3,3,3,3,3,3,0},
            {0,1,1,1,1,1,2,1,1,1,3,3,4,3,3,1,2,4,3,3,3,3,3,3,0},
            {-1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,3,3,3,3,3,3,3,0},
            {0,1,1,1,1,1,2,1,1,3,3,4,4,3,3,1,1,1,3,3,3,3,3,0,0},
            {3,3,3,3,3,3,4,1,1,3,3,3,3,3,3,1,1,1,1,1,1,1,1,-1,0},
            {3,3,3,3,3,3,3,1,1,3,3,3,3,3,4,2,1,2,1,1,1,1,1,0,0},
            {3,3,3,3,3,3,3,1,1,3,3,3,3,3,3,1,1,4,3,3,3,3,3,3,0},
            {3,3,3,3,3,3,3,1,1,3,3,3,3,3,3,1,1,3,3,3,3,3,3,3,0},
            {3,3,3,3,3,3,3,1,1,3,3,3,3,3,3,1,1,3,3,3,3,3,3,3,0},
            {3,3,3,3,3,3,0,-1,0,0,0,0,0,0,0,0,1,0,3,3,3,3,3,3,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };

    Gameplay(GUI frame, Counters counters, Rooms rooms) {
        this.frame=frame;
        this.counters = counters;
        this.rooms=rooms;

        for (Counter currentCounter : this.counters) {
            play[turnTrack] = currentCounter.getCharacterName();
            turnTrack++;
        }
        currentPlayerName = play[0];
        helpCommand();
        turn();
    }

    /**
     * This method runs when we setVisible(true) and when we repaint()
     * It paints the images and counters onto the board
     */
    private void turn() {
        switch (play[PlayTurn]) {
            case "Scarlet":
                currentPlayerName = "Scarlet";
                frame.appendText(currentPlayerName + " has started their turn");
                break;
            case "Mustard":
                currentPlayerName = "Mustard";
                frame.appendText(currentPlayerName + " has started their turn");
                break;
            case "Peacock":
                currentPlayerName = "Peacock";
                frame.appendText(currentPlayerName + " has started their turn");
                break;
            case "Plum":
                currentPlayerName = "Plum";
                frame.appendText(currentPlayerName + " has started their turn");
                break;
            case "White":
                currentPlayerName = "White";
                frame.appendText(currentPlayerName + " has started their turn");
                break;
            case "Green":
                currentPlayerName = "Green";
                frame.appendText(currentPlayerName + " has started their turn");
                break;
        }

        Counter c = Counters.get(currentPlayerName);

        if (c != null && isRoom(c)) {
            listExits(c);
        }
    }

    /**
     * This checks if the counter is currently in a room or not
     */
    private boolean isRoom(Counter counter) {
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
            // If it's a secret passageway, the coordinates are (-1, -1) - if that's the case, we don't want to print the actual coordinates
            if (current.getRow() > -1) {
                frame.appendText("Entrance " + (i + 1) + ": Row " + current.getRow() + ", column " + current.getCol());
            } else {
                // If it's a secret entrance, we say where the secret entrance goes to
                frame.appendText("Entrance " + (i + 1) + " - secret passageway to ");
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

        for (Room r : rooms) {
            ArrayList<Coordinates> entrances = r.getEntrances();
            for (Coordinates entrance : entrances) {
                if ((coord.getCol() == entrance.getCol()) && (coord.getRow() == entrance.getRow())) {
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
        // This searches through the tokenSquares arraylist to find one that isn't occupied
        for (int i = 0; i < tokenSquares.size() && !found; i++) {
            // Since occupied squares are negative, we check if the corresponding value in squareType is positive
            if (squareType[tokenSquares.get(i).getRow()][tokenSquares.get(i).getCol()] > 0) {
                location = tokenSquares.get(i);
                found = true;
            }
        }

        c.setRowColumn(location.getRow(), location.getCol());
        squareType[location.getRow()][location.getCol()] *= -1;
        room.incremenetLastFilledToken();
        dieResult = 0;
    }

    /**
     * Checks if you can enter a certain room or not by looking at both the current square and the next square
     * @param counter the counter to check
     * @param direction the direction you want to remove
     * @return a boolean representing if you can enter the room or not
     */
    private boolean isEnterable(Counter counter, String direction) {
        int nextSquareType;
        int currentSquareType = squareType[counter.getRow()][counter.getColumn()];

        switch(direction) {
            case "u":
                nextSquareType = squareType[counter.getRow()-1][counter.getColumn()];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
            case "d":
                nextSquareType = squareType[counter.getRow()+1][counter.getColumn()];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
            case "l":
                nextSquareType = squareType[counter.getRow()][counter.getColumn()-1];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
            case "r":
                nextSquareType = squareType[counter.getRow()][counter.getColumn()+1];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Checks if the next square is a pathway
     * @param counter the colour of the token you want to move
     * @param direction the direction you want to move
     * @return a boolean representing if the next square is a pathway or not
     */
    private boolean isPathway(Counter counter, String direction) {
        int nextSquareType;
        int currentSquareType = squareType[counter.getRow()][counter.getColumn()];

        switch(direction) {
            case "u":
                nextSquareType = squareType[counter.getRow()-1][counter.getColumn()];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
            case "d":
                nextSquareType = squareType[counter.getRow()+1][counter.getColumn()];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
            case "l":
                nextSquareType = squareType[counter.getRow()][counter.getColumn()-1];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
            case "r":
                nextSquareType = squareType[counter.getRow()][counter.getColumn()+1];
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
     * Checks if the next square is a pathway
     * @param counter the colour of the token you want to move
     * @param direction the direction you want to move
     * @return a boolean representing if the next square is a pathway or not
     */
    private boolean isNotOccupied(Counter counter, String direction) {
        int nextSquareType;

        switch(direction) {
            case "u":
                nextSquareType = squareType[counter.getRow()-1][counter.getColumn()];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
            case "d":
                nextSquareType = squareType[counter.getRow()+1][counter.getColumn()];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
            case "l":
                nextSquareType = squareType[counter.getRow()][counter.getColumn()-1];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
            case "r":
                nextSquareType = squareType[counter.getRow()][counter.getColumn()+1];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
        }
        return true;
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
        while(i<length)
        {
            c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Reads text from userInput and interprets text accordingly
     */
    public void interpretInput( String name) {
        String inputtedText = frame.getUserInput().getText();//takes info from the field
        frame.getUserInput().setText("");//wipes the field after


        frame.appendText(">" + inputtedText);//puts it into the panel
        String splitStr = inputtedText.replaceAll("\\s+","");// Splits the inputted string into an array based spaces

        if (splitStr.toLowerCase().equals("help")) {
            helpCommand();
        }
        else if((splitStr.toLowerCase().equals("u") || splitStr.toLowerCase().equals("up")||splitStr.toLowerCase().equals("d") || splitStr.toLowerCase().equals("down")||splitStr.toLowerCase().equals("l") || splitStr.toLowerCase().equals("left")||splitStr.toLowerCase().equals("r") || splitStr.toLowerCase().equals("right"))) { // If the first word is move or m in any format
            if(dieResult>0) {
                if(moveCommand(splitStr, name)) {
                    dieResult=dieResult-1;
                }
            }
            else {
                frame.appendText("You have used all your movement.");
            }
        }
        else if(splitStr.toLowerCase().equals("quit")) {
            frame.appendText("Thank you for playing! Goodbye");
            System.exit(0);
        }

        else if(splitStr.toLowerCase().equals("roll")) {
            if (dieRoll==0) {
                Dice die = new Dice();
                dieResult=die.roll();
                frame.appendText("You rolled a "+ dieResult);
                //TODO This lets you move (basically) unlimitedly - for testing purposes only
                // dieResult = 1000;
                dieRoll++;
            }
            else {
                frame.appendText("You have already rolled this turn!");
            }
        }

        else if(splitStr.toLowerCase().equals("done")) {
            dieResult=0;
            dieRoll=0;
            frame.appendText(currentPlayerName + "'s turn has ended!");
            PlayTurn=(PlayTurn+1)%turnTrack;

            turn();
            // Goes to the next players move
        } else if(checkInteger(splitStr) && dieResult > 0) {
            Counter c = Counters.get(currentPlayerName);
            if (c == null) {
                throw new RuntimeException("Counter does not exist");
            }

            Room r = c.getCurrentRoom();

            squareType[c.getRow()][c.getColumn()] *= -1;

            // Checks which room is selected
            switch (splitStr.toLowerCase()) {
                case "1":
                    c.setRowColumn(r.getEntrances().get(0).getRow(), r.getEntrances().get(0).getCol());
                    break;
                case "2":
                    c.setRowColumn(r.getEntrances().get(1).getRow(), r.getEntrances().get(1).getCol());
                    break;
                case "3":
                    try {
                        c.setRowColumn(r.getEntrances().get(2).getRow(), r.getEntrances().get(2).getCol());
                    } catch(IndexOutOfBoundsException e) {
                        frame.appendText("Select a valid entrance!");
                    }
                    break;
                case "4":
                    try {
                        c.setRowColumn(r.getEntrances().get(3).getRow(), r.getEntrances().get(3).getCol());
                    } catch(IndexOutOfBoundsException e) {
                        frame.appendText("Select a valid entrance!");
                    }
                    break;

            }
            // If the co-ordinates are negative, then I know it's a secret passageway - I can then swap the room
            if (c.getRow() == -1) {
                String ugh = c.getCurrentRoom().getRoomName();
                switch (ugh) {
                    case "Conservatory":
                        c.setCurrentRoom(Rooms.get("Lounge"));
                        break;
                    case "Lounge":
                        c.setCurrentRoom(Rooms.get("Conservatory"));
                        break;
                    case "Kitchen":
                        c.setCurrentRoom(Rooms.get("Study"));
                        break;
                    case "Study":
                        c.setCurrentRoom(Rooms.get("Kitchen"));
                        break;
                }
                // After swapping the room, the counter needs to be moved to the centrepoint
                moveToRoomCentre(c);
            } else {
                int col = c.getColumn();
                int row = c.getRow();
                if(squareType[row - 1][col] == 2) {
                    c.setRowColumn(row - 1, col);
                }
                if(squareType[row + 1][col] == 2) {
                    c.setRowColumn(row + 1, col);
                }
                if(squareType[row][col - 1] == 2) {
                    c.setRowColumn(row, col - 1);
                }
                if(squareType[row][col + 1] == 2) {
                    c.setRowColumn(row, col + 1);
                }}

            squareType[c.getRow()][c.getColumn()] *= -1;
            frame.repaint();
            dieResult--;
        }

        else {
            frame.appendText("Invalid command entered!");
        }

    }

    /**
     * This method lets the user make standard moves (e.g. up/down/left/right
     */
    private boolean moveCommand(String splitStr, String colour) {
        // We can only move if the next square is either a pathway or is a room square adjacent to an entrance
        boolean moved=true;

        Counter counter = Counters.get(colour);
        if (counter == null) {
            throw new RuntimeException();
        }

        switch (splitStr.toLowerCase()) { // Checks the movement direction entered
            case "up":
            case "u":
                if ((isPathway(counter, "u") || isEnterable(counter, "u")) && isNotOccupied(counter, "u") && counter.getRow()>0) {
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    counter.moveUp(1);
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                }
                else {
                    moved=false;
                }
                break;
            case "down":
            case "d":
                if ((isPathway(counter, "d") || isEnterable(counter, "d")) && isNotOccupied(counter, "d")) {
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    counter.moveDown(1);
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                }
                else {
                    moved=false;
                }
                break;
            case "left":
            case "l":
                if ((isPathway(counter, "l") || isEnterable(counter, "l")) && isNotOccupied(counter, "l") && counter.getColumn()>0) {
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    counter.moveLeft(1);
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                }
                else {
                    moved=false;
                }
                break;
            case "right":
            case "r":
                if ((isPathway(counter, "r") || isEnterable(counter, "r")) && isNotOccupied(counter, "r")) {
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                    counter.moveRight(1);
                    squareType[counter.getRow()][counter.getColumn()] *= -1;
                }
                else {
                    moved=false;
                }
                break;
            default:
                frame.appendText("Invalid direction chosen");
                moved=false;
        }
        if(!moved) {
            frame.appendText("Error. Incorrect Movement!");
        }

        // If the counter is now in a room, it finds what room the counter is in and moves it to the centre of that room
        if(isRoom(counter)) {
            findCurrentRoom(counter);
            moveToRoomCentre(counter);
        }

        frame.repaint(); // Repaints the board with the new location of the pieces
        return moved;
    }

    /**
     * Displays a help message in the infoField
     */
    public void helpCommand() {
        frame.appendText("Commands:\nMove Player Piece:\n - letter corresponding to direction of movement e.g u/d/l/r \n" +
                "\nEnd Turn\n - \"done\"\n\nQuit Game\n - \"quit\"\n\n Roll Dice\n - roll\n");
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }
}