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
    private Counter scarletCounter, mustardCounter, peacockCounter, plumCounter, greenCounter, whiteCounter;
    private Weapon Pistol, Rope, Wrench, Dagger, LeadPipe, CandleStick;
    private JPanel board;
    private JTextArea infoField;
    private JTextField userInput;
    private JScrollPane scrollPane;
    private BufferedImage boardImage;


    /**
     * This method creates the graphical interface for the program
     */
    public GUI() {
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

        // This method creates the Counter objects
        initialiseCounters();
        // This method creates the Weapon objects
        initialiseWeapons();

        // This method places the weapons onto the screen in random locations
        WeaponLocationAssigner();

        // Adds the different sections to the GUI
        addComponents();

        // Displays the frame to the user
        setVisible(true);

        // This action occurs when the user types "enter" in the userInput field
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Understands what the user enters and acts accordingly
                interpretInput();
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
        scarletCounter.paintComponent(g);
        mustardCounter.paintComponent(g);
        peacockCounter.paintComponent(g);
        plumCounter.paintComponent(g);
        greenCounter.paintComponent(g);
        whiteCounter.paintComponent(g);
    }

    /**
     * Draws the weapons on top of the board in the correct locations
     */
    private void drawWeapons(Graphics g) {

        Pistol.paintComponent(g);
        Rope.paintComponent(g);
        Dagger.paintComponent(g);
        Wrench.paintComponent(g);
        CandleStick.paintComponent(g);
        LeadPipe.paintComponent(g);
    }

    /**
     * Randomly allocates a location to weapons on each game launch
     */
    private void WeaponLocationAssigner() {
        //array of valid locations of weapons each array will contain an XY coordinate
        int[][] wepLocation = new int[][]{
                {120, 150},
                {500, 120},
                {172, 303},
                {470, 300},
                {120, 500},
                {438, 550},
                {550, 410},
                {300, 100},
                {250, 500}};

        Random rnd = ThreadLocalRandom.current();//creates random numbers
        for (int i = wepLocation.length - 1; i > 0; i--)//run this loop for the length of the array
        {
            int index = rnd.nextInt(i + 1);
            int a = wepLocation[index][0];//a and b are placeholders for values in the original array
            int b = wepLocation[index][1];
            wepLocation[index][0] = wepLocation[i][0];//the original values get replaced with a random other value
            wepLocation[index][1] = wepLocation[i][1];
            wepLocation[i][0] = a;//the replaced values move to where their replacement had been
            wepLocation[i][1] = b;
        }
        //allocate the new random positions to the items
        LeadPipe.setXY(wepLocation[0][0], wepLocation[0][1]);
        CandleStick.setXY(wepLocation[1][0], wepLocation[1][1]);
        Wrench.setXY(wepLocation[2][0], wepLocation[2][1]);
        Dagger.setXY(wepLocation[3][0], wepLocation[3][1]);
        Rope.setXY(wepLocation[4][0], wepLocation[4][1]);
        Pistol.setXY(wepLocation[5][0], wepLocation[5][1]);

    }

    /**
     * Reads text from userInput and interprets text accordingly
     */
    private void interpretInput() {
        String inputtedText = userInput.getText();//takes info from the field
        userInput.setText("");//wipes the field after

        infoField.append(">" + inputtedText + "\n");//puts it into the panel
        String[] splitStr = inputtedText.split("\\s+");// Splits the inputted string into an array based spaces
        if (inputtedText.toLowerCase().equals("help")) {
            helpCommand();
        }  else if(splitStr.length==4&&splitStr[0].toLowerCase().equals("move"))// If the first word is move in any format
        {
            moveCommand(splitStr);
        }
        else { infoField.append("\n Invalid command entered!\n");}
    }

    private void moveCommand(String[] splitStr) {
        BoardPiece temp = null;// Holds the name of the player counter chosen
        switch (splitStr[1].toLowerCase()) {// Checks the counter or weapon chosen, changes it to lowercase
            case "scarlet":
            case "red":
                temp = scarletCounter;
                break;
            case "mustard":
            case "yellow":
                temp = mustardCounter;
                break;
            case "peacock":
            case "blue":
                temp = peacockCounter;
                break;
            case "plum":
            case "purple":
                temp = plumCounter;
                break;
            case "green":
                temp = greenCounter;
                break;
            case "white":
                temp = whiteCounter;
                break;
            case "dagger":
                temp = Dagger;
                break;
            case "candlestick":
                temp = CandleStick;
                break;
            case "pistol":
            case "gun":
            case "revolver":
                temp = Pistol;
                break;
            case "leadpipe":
                temp = LeadPipe;
                break;
            case "rope":
                temp = Rope;
                break;
            case "wrench":
                temp = Wrench;
                break;
            default:
                infoField.append("\nInvalid item chosen\n");
        }

        switch (splitStr[2].toLowerCase()) { // Checks the movement direction entered
            case "up":
                temp.moveUp(Integer.parseInt(splitStr[3]));
                break;
            case "down":
                temp.moveDown(Integer.parseInt(splitStr[3]));
                break;
            case "left":
                temp.moveLeft(Integer.parseInt(splitStr[3]));
                break;
            case "right":
                temp.moveRight(Integer.parseInt(splitStr[3]));
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
    private void initialiseWeapons() {
        //weapon objects are created below
        Pistol = new Weapon();
        Pistol.SetImageFile("/revolver.PNG");
        board.add(Pistol);

        Rope = new Weapon();
        Rope.SetImageFile("/rope.PNG");
        board.add(Rope);

        Dagger = new Weapon();
        Dagger.SetImageFile("/dagger.PNG");
        board.add(Dagger);

        Wrench = new Weapon();
        Wrench.SetImageFile("/wrench.PNG");
        board.add(Wrench);

        CandleStick = new Weapon();
        CandleStick.SetImageFile("/candlestick.PNG");
        board.add(CandleStick);

        LeadPipe = new Weapon();
        LeadPipe.SetImageFile("/lead_pipe.PNG");
        board.add(LeadPipe);
    }

    /**
     * Creates each counter and sets its location, and finally adds it to the board
     */
    private void initialiseCounters() {
        scarletCounter = new Counter();
        scarletCounter.setXY(204, 601);
        scarletCounter.setColor(Color.RED);
        board.add(scarletCounter);

        mustardCounter = new Counter();
        mustardCounter.setXY(44, 440);
        mustardCounter.setColor(Color.YELLOW);
        board.add(mustardCounter);

        peacockCounter = new Counter();
        peacockCounter.setXY(572, 487);
        peacockCounter.setColor(Color.BLUE);
        board.add(peacockCounter);

        plumCounter = new Counter();
        plumCounter.setXY(572, 188);
        plumCounter.setColor(new Color(95, 24, 175));
        board.add(plumCounter);

        greenCounter = new Counter();
        greenCounter.setXY(250, 50);
        greenCounter.setColor(new Color(15, 188, 41));
        board.add(greenCounter);

        whiteCounter = new Counter();
        whiteCounter.setXY(365, 50);
        whiteCounter.setColor(Color.WHITE);
        board.add(whiteCounter);
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