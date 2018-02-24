/*  Cluedo - Sprint 1
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class creates the graphical interface of the program and initialises all of the pieces
 */
public class GUI extends JFrame {

    // These are the variables contained in the GUI - the board components and the pieces on the board
    private Counters counters;
    private Weapons weapons;
    private Rooms rooms;
    private String[] play =new String[6];
    private JPanel board;
    private JTextArea infoField;
    private JTextField userInput;
    private JScrollPane scrollPane;
    private BufferedImage boardImage;
    private int dieResult=0;
    private int PlayTurn=0;
    private int dieRoll=0;//tracker used to stop more than one roll call per turn
    private int turnTrack=0;
    private String currentPlayerName;

    // Squares that are marked 0 are inaccessible by the player (they are out of bounds)
    // Squares that are marked 1 are pathways that the player can walk on
    // Sqaures that are marked 2 are pathway squares that are adjacent to room entrances
    // Sqaures that are marked 3 are squares inside rooms
    // Sqaures that are marked 4 are room squares that are adjacent to room entrances
    // A number is turned negative when it is occupied
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

    /**
     * This method creates the graphical interface for the program
     */
    public GUI(Counters counters, Weapons weapons, Rooms rooms) {
        this.counters = counters;
        this.weapons = weapons;
        this.rooms = rooms;
        
        board = new JPanel();
        // We use BorderLayout to easily have multiple components in the same panel
        setLayout(new BorderLayout());
        setSize(835, 690);
        setTitle("Cluedo");
        // Places the frame in the centre of the screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // This loads the image "cluedo_board.jpg", or shows the user an error
        boardImage = null;
        try {
            boardImage = ImageIO.read(this.getClass().getResource("cluedo_board.jpg"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: cannot load board image.");
        }

        infoField = new JTextArea(10, 15);
        // I setEditable to false so that the user can't edit the text on the right-hand size
        infoField.setEditable(false);
        infoField.setLineWrap(true);
        // I place the infoField inside a scrollpane so that the textArea doesn't fill up
        scrollPane = new JScrollPane(infoField);

        DefaultCaret caret = (DefaultCaret)infoField.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        userInput = new JTextField();
        userInput.setText("Enter text here (type 'help' for help)");
        infoField.append("Commands: \nMove Player Piece\n - intial for direction of movement e.g U/D/L/R \n" +
                "\nEnd Turn\n - \"done\"\n\nQuit Game\n - \"quit\"\n\n Roll Dice\n - roll\n\n");
        // This method creates the Counter objects
        initialiseCounters(counters);
        // This method creates the Weapon objects
        initialiseWeapons(weapons);


        // Adds the different sections to the GUI
        addComponents();

        // Displays the frame to the user
        setVisible(true);

        for (Counter currentCounter : counters) {
            play[turnTrack] = currentCounter.getCounterName();
            turnTrack++;
        }

        currentPlayerName = play[0];
        turn();
        // This action occurs when the user types "enter" in the userInput field
        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Understands what the user enters and acts accordingly

                interpretInput(currentPlayerName);
        	}
        };
        userInput.addActionListener(action); //Sets a button(enter) to activate the above listener
    }
  

   
    /**
     * This method runs when we setVisible(true) and when we repaint()
     * It paints the images and counters onto the board
     */
    private void turn()
    {
        switch (play[PlayTurn]) {
            case "Scarlet":
                currentPlayerName = "Scarlet";
                infoField.append(currentPlayerName + " has started their turn\n");
                break;
            case "Mustard":
                currentPlayerName = "Mustard";
                infoField.append(currentPlayerName + " has started their turn\n");
                break;
            case "Peacock":
                currentPlayerName = "Peacock";
                infoField.append(currentPlayerName + " has started their turn\n");
                break;
            case "Plum":
                currentPlayerName = "Plum";
                infoField.append(currentPlayerName + " has started their turn\n");
                break;
            case "White":
                currentPlayerName = "White";
                infoField.append(currentPlayerName + " has started their turn\n");
                break;
            case "Green":
                currentPlayerName = "Green";
                infoField.append(currentPlayerName + " has started their turn\n");
                break;
        }

        Counter c = Counters.get(currentPlayerName);

        if (isRoom(c)) {
            listExits(c);
        }
    }

    public void paint(Graphics g) {
        // This line insures that the other components of the JFrame are visible
        super.paint(g);
        // We cast to Graphics2D to access more image drawing features
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(boardImage, 0, 25, this);

        drawCounters(g);
        drawWeapons(g);
    }

    /**
     * Draws the counters on top of the board in the correct locations
     */
    private void drawCounters(Graphics g) {
        for(Counter c : counters) {
            c.paintComponent(g);
        }
    }

    /**
     * Draws the weapons on top of the board in the correct locations
     */
    private void drawWeapons(Graphics g) {
        for(Weapon w : weapons) {
            w.paintComponent(g);
        }
    }

    /**
     * Reads text from userInput and interprets text accordingly
     */
    private void interpretInput( String name) {
        String inputtedText = userInput.getText();//takes info from the field
        userInput.setText("");//wipes the field after
      
       
        infoField.append(">" + inputtedText + "\n");//puts it into the panel
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
            	infoField.append("\n You have used all your movement.\n");
            }
        }
        else if(splitStr.toLowerCase().equals("quit")) {
        	infoField.append("Thank you for playing! Goodbye");

        	System.exit(0);
        }

        else if(splitStr.toLowerCase().equals("roll")) {
        	if (dieRoll==0){
                Dice die = new Dice();
        	    dieResult=die.roll();
                infoField.append("\nYou rolled a "+ dieResult+"\n");
        	    //TODO This lets you move (basically) unlimitedly - for testing purposes only
                // dieResult = 1000;
        	    dieRoll++;
        	}
        	else {
        		infoField.append("You have already rolled this turn!");
        	}
        }
     
        else if(splitStr.toLowerCase().equals("done")) {
        	dieResult=0;
        	dieRoll=0;
        	infoField.append("\nPlayers turn has ended!\n");
        	PlayTurn=(PlayTurn+1)%turnTrack;
        	
        	 turn();
        	// Goes to the next players move
        } else if(checkInteger(splitStr) && dieResult > 0) {
            Counter c = Counters.get(currentPlayerName);
            if (c == null) {
                throw new RuntimeException("Counter does not exist");
            }

           Room r = c.getCurrentRoom();

            squareType[c.getGridY()][c.getGridX()] *= -1;

            switch (splitStr.toLowerCase()) {
                case "1":
                    c.setGridXY(r.getEntrances().get(0).getCol(), r.getEntrances().get(0).getRow());
                    break;
                case "2":
                    c.setGridXY(r.getEntrances().get(1).getCol(), r.getEntrances().get(1).getRow());
                    break;
                case "3":
                    c.setGridXY(r.getEntrances().get(2).getCol(), r.getEntrances().get(2).getRow());
                    break;
                case "4":
                    c.setGridXY(r.getEntrances().get(3).getCol(), r.getEntrances().get(3).getRow());
                    break;

            }
            if (c.getGridY() == -1) {
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
                moveToRoomCentre(Counters.get(currentPlayerName));
            } else {
            int xValue = c.getGridX();
            int yValue = c.getGridY();
            if(squareType[yValue - 1][xValue] == 2) {
                c.setGridXY(xValue, yValue - 1);
            }
            if(squareType[yValue + 1][xValue] == 2) {
                c.setGridXY(xValue, yValue + 1);
            }
            if(squareType[yValue][xValue - 1] == 2) {
                c.setGridXY(xValue - 1, yValue);
            }
            if(squareType[yValue][xValue + 1] == 2) {
                c.setGridXY(xValue + 1, yValue - 1);
            }}

            squareType[c.getGridY()][c.getGridX()] *= -1;
            repaint();
            dieResult--;
        }

        else { 
        	infoField.append("\n Invalid command entered!\n");
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
                if ((isPathway(counter, "u") || isEnterable(counter, "u")) && isNotOccupied(counter, "u") && counter.getGridY()>0) {
                    squareType[counter.getGridY()][counter.getGridX()] *= -1;
                    counter.moveUp(1);
                    squareType[counter.getGridY()][counter.getGridX()] *= -1;
                }
                else {
                	moved=false;
                }
                break;
            case "down":
            case "d":
                if ((isPathway(counter, "d") || isEnterable(counter, "d")) && isNotOccupied(counter, "d")) {
                    squareType[counter.getGridY()][counter.getGridX()] *= -1;
                    counter.moveDown(1);
                    squareType[counter.getGridY()][counter.getGridX()] *= -1;
                }
                else {
                	moved=false;
                }
                break;
            case "left":
            case "l":
                if ((isPathway(counter, "l") || isEnterable(counter, "l")) && isNotOccupied(counter, "l") && counter.getGridX()>0) {
                    squareType[counter.getGridY()][counter.getGridX()] *= -1;
                    counter.moveLeft(1);
                    squareType[counter.getGridY()][counter.getGridX()] *= -1;
                }
                else {
                	moved=false;
                }
                break;
            case "right":
            case "r":
                if ((isPathway(counter, "r") || isEnterable(counter, "r")) && isNotOccupied(counter, "r")) {
                    squareType[counter.getGridY()][counter.getGridX()] *= -1;
                    counter.moveRight(1);
                    squareType[counter.getGridY()][counter.getGridX()] *= -1;
                }
                else {
                	moved=false;
                }
                break;
            default:
                infoField.append("\nInvalid direction chosen\n");
                moved=false;
		}
        if(!moved) {
        	infoField.append("Error. Incorrect Movement!");
        }

        // If the counter is now in a room, it finds what room the counter is in and moves it to the centre of that room
        if(isRoom(counter)) {
            findCurrentRoom(counter);
            moveToRoomCentre(counter);
        }

        repaint(); // Repaints the board with the new location of the pieces
        return moved;
    }

    /**
     * This checks if the counter is currently in a room or not
     */
    private boolean isRoom(Counter counter) {
        return squareType[counter.getGridY()][counter.getGridX()] <= -3 || squareType[counter.getGridY()][counter.getGridX()] >= 3;
    }

    /**
     * This prints off the possible exits for a counter to leave a room through
     */
    private void listExits(Counter counter) {
        Room room = counter.getCurrentRoom();
        ArrayList<Coordinates> entrances = room.getEntrances();

        // Prints entrances
        infoField.append("Type the entrance number to leave through that entrance.\n");
        for (int i = 0; i < entrances.size(); i++) {
            Coordinates current = entrances.get(i);
            // If it's a secret passageway, the coordinates are (-1, -1) - if that's the case, we don't want to print the actual coordinates
            if (current.getRow() > -1) {
                infoField.append("Entrance " + (i + 1) + ": (" + current.getRow() + " , " + current.getCol() + ")\n");
            } else {
                // If it's a secret entrance, we say where the secret entrance goes to
                infoField.append("Entrance " + (i + 1) + " - secret passageway to ");
                switch (counter.getCurrentRoom().getRoomName()) {
                    case "Conservatory":
                        infoField.append("Lounge");
                        break;
                    case "Lounge":
                        infoField.append("Conservatory");
                        break;
                    case "Kitchen":
                        infoField.append("Study");
                        break;
                    case "Study":
                        infoField.append("Kitchen");
                        break;
                }
            }
        }
    }

    /**
     * This finds the current entrance that the user is placed at, and assigns the corresponding current room to the counter
     */
    private void findCurrentRoom(Counter counter) {
        Coordinates coord = new Coordinates(counter.getGridX(), counter.getGridY());
        squareType[counter.getGridY()][counter.getGridX()] *= -1;

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

        c.setGridXY(location.getCol(), location.getRow());
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
        int currentSquareType = squareType[counter.getGridY()][counter.getGridX()];

        switch(direction) {
            case "u":
                nextSquareType = squareType[counter.getGridY()-1][counter.getGridX()];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
            case "d":
                nextSquareType = squareType[counter.getGridY()+1][counter.getGridX()];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
            case "l":
                nextSquareType = squareType[counter.getGridY()][counter.getGridX()-1];
                if (nextSquareType == 4 && currentSquareType == -2) {
                    return true;
                }
                break;
            case "r":
                nextSquareType = squareType[counter.getGridY()][counter.getGridX()+1];
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
        int currentSquareType = squareType[counter.getGridY()][counter.getGridX()];

        switch(direction) {
            case "u":
                nextSquareType = squareType[counter.getGridY()-1][counter.getGridX()];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
            case "d":
                nextSquareType = squareType[counter.getGridY()+1][counter.getGridX()];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
            case "l":
                nextSquareType = squareType[counter.getGridY()][counter.getGridX()-1];
                if (currentSquareType == 4 && nextSquareType == 1) {
                    return false;
                } else if (nextSquareType == 2 || nextSquareType == 1) {
                    return true;
                }
                break;
            case "r":
                nextSquareType = squareType[counter.getGridY()][counter.getGridX()+1];
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
                nextSquareType = squareType[counter.getGridY()-1][counter.getGridX()];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
            case "d":
                nextSquareType = squareType[counter.getGridY()+1][counter.getGridX()];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
            case "l":
                nextSquareType = squareType[counter.getGridY()][counter.getGridX()-1];
                if (nextSquareType < 0) {
                    return false;
                }
                break;
            case "r":
                nextSquareType = squareType[counter.getGridY()][counter.getGridX()+1];
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
     * Displays a help message in the infoField
      */
    private void helpCommand() {
        infoField.append("Commands: \nMove Player Piece\n - move (direction)\n" +
                "\nQuit Game\n - quit"+"\nEnd turn\n - end");
    }

    /**
     * Creates the weapons, and finally adds it to the board
     */
    private void initialiseWeapons(Weapons weapons) {
        for (Weapon w : weapons) {
            board.add(w);
        }
    }

    /**
     * Creates each counter and sets its location, and finally adds it to the board
     */
    private void initialiseCounters(Counters counters) {
        for (Counter c : counters) {
            board.add(c);
        }

    }

    /**
     * Adds each section of the GUI to the board
     */
    private void addComponents() {
        add(scrollPane, "East");
        add(userInput, "South");
        add(board, "Center");
    }
}