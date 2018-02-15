/*  Cluedo - Sprint 1
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class creates the graphical interface of the program and initialises all of the pieces
 */
public class GUI extends JFrame {

    // These are the variables contained in the GUI - the board components and the pieces on the board
    public Counter scarletCounter, mustardCounter, peacockCounter, plumCounter, greenCounter, whiteCounter;
    private Counters counters;
    private Weapons weapons;
    private Weapon Pistol, Rope, Wrench, Dagger, LeadPipe, CandleStick;
    private JPanel board;
    private JTextArea infoField;
    private JTextField userInput;
    private JScrollPane scrollPane;
    private BufferedImage boardImage;
    private int num=0;

    /**
     * This method creates the graphical interface for the program
     */
    public GUI(Counters counters, Weapons weapons) {
        this.counters = counters;
        this.weapons = weapons;

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

        userInput = new JTextField();
        userInput.setText("Enter text here (type 'help' for help)");
        infoField.append("Commands: \nMove Player Piece\n - move (colour/character) (direction) (steps) e.g Move Scarlet Up 4\n" +
                "Player Names:\n  -Scarlet/Red\n  -Plum/purple\n  -Mustard/yellow\n  -Peacock/blue\n  -White\n  -Green" +
                "\n\nMove Weapons\n - move (weapon name) (direction) (steps)\nWeapon Names:\n  -Dagger\n  -CandleStick\n " +
                " -Pistol\n  -Rope\n  -Wrench\n  -LeadPipe\n");
        // This method creates the Counter objects
        initialiseCounters(counters);
        // This method creates the Weapon objects
        initialiseWeapons(weapons);

        // Adds the different sections to the GUI
        addComponents();

        // Displays the frame to the user
        setVisible(true);

        // This action occurs when the user types "enter" in the userInput field
        Action action = new AbstractAction() {
       
           
            public void actionPerformed(ActionEvent e) {
                // Understands what the user enters and acts accordingly
            	//int die = rollDice();
            	
                interpretInput(5,"Scarlet");
            	
            	
            
            infoField.append("players turn end");
        	num=0;
        	}
        };
        userInput.addActionListener(action); //Sets a button(enter) to activate the above listener
    }

    /**
     * This method runs when we setVisible(true) and when we repaint()
     * It paints the images and counters onto the board
     */
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
    private void interpretInput(int count, String name) {
        String inputtedText = userInput.getText();//takes info from the field
        userInput.setText("");//wipes the field after
        boolean numCheck = true;
        infoField.append(">" + inputtedText + "\n");//puts it into the panel
        String[] splitStr = inputtedText.split("\\s+");// Splits the inputted string into an array based spaces
  
        if (inputtedText.toLowerCase().equals("help")) {
            helpCommand();
        }  else if(splitStr.length==2&&splitStr[0].toLowerCase().equals("move"))// If the first word is move in any format
        {
            
            if(numCheck==true)
            {
                moveCommand(splitStr, name);
                num++;
            }
        }
        else if(inputtedText.toLowerCase().equals("quit"))
{
	infoField.append("Thank you for playing! Goodbye");

	System.exit(0);
}
    
        else { 
        	infoField.append("\n Invalid command entered!\n");
        }
    }
   

   
	private void moveCommand(String[] splitStr, String colour) {
        BoardPiece temp = null;// Holds the name of the player counter chosen
       

        switch (splitStr[1].toLowerCase()) { // Checks the movement direction entered
            case "up":
                Counters.get(colour).moveUp(1);
                break;
            case "down":
            	Counters.get(colour).moveDown(1);
                break;
            case "left":
            	Counters.get(colour).moveLeft(1);
                break;
            case "right":
            	Counters.get(colour).moveRight(1);
                break;
            default:
                infoField.append("\nInvalid direction chosen\n");
        }

        repaint(); // Repaints the board with the new location of the pieces
    }

    /**
     * Help command for when "help" is inputted by the user
     */
    private void helpCommand() {
        infoField.append("Commands: \nMove Player Piece\n - move (colour/character) (direction) (steps)\n" +
                "Player Names:\n  -Scarlet/Red\n  -Plum/purple\n  -Mustard/yellow\n  -Peacock/blue\n  -White\n  -Green" +
                "\n\nMove Weapons\n - move (weapon name) (direction) (steps)\nWeapon Names:\n  -Dagger\n  -CandleStick\n " +
                " -Pistol\n  -Rope\n  -Wrench\n  -LeadPipe\n");
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
//        board.add(scarletCounter);
//
//        mustardCounter = new Counter("Mustard", Color.YELLOW, 44, 440);
//        board.add(mustardCounter);
//
//        peacockCounter = new Counter("Peacock", Color.BLUE, 572, 487);
//        board.add(peacockCounter);
//
//        plumCounter = new Counter("Plum", new Color(95, 24, 175), 572, 188);
//        board.add(plumCounter);
//
//        greenCounter = new Counter("Green", new Color(15, 188, 41), 250, 50);
//        board.add(greenCounter);
//
//        whiteCounter = new Counter("White", Color.WHITE, 365, 50);
//        board.add(whiteCounter);
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