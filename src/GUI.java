/*  Cluedo - Sprint 3
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

/**
 * This class creates the graphical interface of the program and initialises all of the pieces
 */
public class GUI extends JFrame {

    // These are the variables contained in the GUI - the board components and the pieces on the board
    private Counters counters;
    private Weapons weapons;
    private JPanel board;
    private JTextArea infoField;
    private JTextField userInput;
    private JScrollPane scrollPane;
    private BufferedImage boardImage;


    /**
     * This method creates the graphical interface for the program
     */
    GUI(Counters counters, Weapons weapons, Rooms rooms) {
        this.counters = counters;
        this.weapons = weapons;

        board = new JPanel();
        // We use BorderLayout to easily have multiple components in the same panel
        setLayout(new BorderLayout());
        setSize(982, 680);
        setTitle("Cluedo");
        setBackground(new Color(76, 133, 99));
        // Places the frame in the centre of the screen
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // This loads the image "cluedo_board.jpg", or shows the user an error
        boardImage = null;
        try {
            boardImage = ImageIO.read(this.getClass().getResource("cluedo_board.jpg"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: cannot load board image.");
        }

        infoField = new JTextArea(12, 42);
        // I setEditable to false so that the user can't edit the text on the right-hand size
        infoField.setEditable(false);
        infoField.setBackground(new Color(107, 106, 104));
        infoField.setForeground(Color.WHITE);
        infoField.setLineWrap(true);
        infoField.setWrapStyleWord(true);
        infoField.setMargin(new Insets(5, 5, 5, 5));
        // I place the infoField inside a scrollpane so that the textArea doesn't fill up
        scrollPane = new JScrollPane(infoField);

        DefaultCaret caret = (DefaultCaret) infoField.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        userInput = new JTextField();
        userInput.setText("Enter text here (type 'help' for help)");

        // This method creates the Counter objects
        initialiseCounters(counters);
        // This method creates the Weapon objects
        initialiseWeapons(weapons);

        infoField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        // Adds the different sections to the GUI
        addComponents();

        // Displays the frame to the user
        setVisible(true);

        Gameplay gp = new Gameplay(this, counters, rooms);
        // This action occurs when the user types "enter" in the userInput field
        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // Understands what the user enters and acts accordingly
                gp.interpretInput(gp.getCurrentPlayerName());
            }
        };
        userInput.addActionListener(action); //Sets a button(enter) to activate the above listener
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
        for (Counter c : counters) {
            c.paintComponent(g);
        }
    }

    /**
     * Draws the weapons on top of the board in the correct locations
     */
    private void drawWeapons(Graphics g) {
        for (Weapon w : weapons) {
            w.paintComponent(g);
        }
    }

    /**
     * Creates each weapon and adds it to the board
     */
    private void initialiseWeapons(Weapons weapons) {
        for (Weapon w : weapons) {
            board.add(w);
        }
    }

    /**
     * Creates each counter and adds it to the board
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

    /**
     * We use this method to add text to the infoField from outside this class
     */
    public void appendText(String input) {
        infoField.append(input + "\n");
    }

    /**
     * Returns the userInput component - we use this so we can access the entered text outside of this class
     */
    public JTextField getUserInput() {
        return userInput;
    }
}
