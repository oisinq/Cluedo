/**
 * This file is not currently used by the program - it's an attempt to use components for the counters instead of drawing onto the board
 */

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ShapeTest extends JFrame {


    // This method creates the graphic interface for the program
    public ShapeTest() throws IOException {

        JLayeredPane panel = new JLayeredPane();
        JPanel board = new JPanel();

        // We use BorderLayout to easily have multiple components in the same panel
        panel.setLayout(new BorderLayout());
        setSize(835, 690);
        setTitle("Cluedo");
        // Places the frame in the centre of the screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BufferedImage myPicture = ImageIO.read(new File("resources/cluedo_board.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        board.add(picLabel, new Integer(0));

        JTextArea infoField = new JTextArea(10, 15);
        // I setEditable to false so that the user can't edit the text on the right-hand size
        infoField.setEditable(false);
        infoField.setLineWrap(true);
        // I place the infoField inside a scrollpane so that the textArea doesn't fill up
        JScrollPane scrollPane = new JScrollPane(infoField);

        JTextField userInput = new JTextField();
        userInput.setText("Enter messages here!");
        panel.add(scrollPane, "East", new Integer(1));
        panel.add(board, "Center", new Integer(1));
        panel.add(userInput, "South", new Integer(1));
        add(panel);
        Counter component = new Counter();
        component.setOpaque(false);
        add(component, "Center", new Integer(0));


        setVisible(true);
    }

    


    public static void main (String args[]) throws Exception {
        new ShapeTest();
    }
}