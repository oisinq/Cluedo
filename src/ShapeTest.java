/**
 * This file is not currently used by the program - it's an attempt to use components for the counters instead of drawing onto the board
 */

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ShapeTest extends JFrame  implements MouseListener {

    Counter redCounter, yellowCounter, blueCounter, cyanCounter, greenCounter, whiteCounter;


    // This method creates the graphic interface for the program
    public ShapeTest() throws IOException {

 //       JLayeredPane panel = new JLayeredPane();
        JPanel board = new JPanel();

        // We use BorderLayout to easily have multiple components in the same panel
        setLayout(new BorderLayout());
        setSize(835, 690);
        setTitle("Cluedo");
        // Places the frame in the centre of the screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



//        BufferedImage myPicture = ImageIO.read(new File("resources/cluedo_board.jpg"));
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//        board.add(picLabel);



        JTextArea infoField = new JTextArea(10, 15);
        // I setEditable to false so that the user can't edit the text on the right-hand size
        infoField.setEditable(false);
        infoField.setLineWrap(true);
        // I place the infoField inside a scrollpane so that the textArea doesn't fill up
        JScrollPane scrollPane = new JScrollPane(infoField);

        JTextField userInput = new JTextField();
        userInput.setText("Enter messages here!");

        redCounter = new Counter();
        redCounter.setOpaque(false);
        board.add(redCounter);

        yellowCounter = new Counter();
        yellowCounter.setOpaque(false);
        board.add(yellowCounter);

        greenCounter = new Counter();
        greenCounter.setOpaque(false);
        board.add(greenCounter);

        cyanCounter = new Counter();
        cyanCounter.setOpaque(false);
        board.add(cyanCounter);

        whiteCounter = new Counter();
        whiteCounter.setOpaque(false);
        board.add(whiteCounter);

        blueCounter = new Counter();
        blueCounter.setOpaque(false);
        board.add(blueCounter);


        add(scrollPane, "East");
        add(userInput, "South");
        add(board, "Center");




        addMouseListener(this);

        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("resources/cluedo_board.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        g.drawImage(myPicture, 0, 22, null);

        redCounter.paintComponent(g, 204, 599, Color.RED);
        yellowCounter.paintComponent(g, 44, 437, Color.yellow);
        blueCounter.paintComponent(g, 572, 484, Color.blue);
        cyanCounter.paintComponent(g, 572, 185, Color.cyan);
        whiteCounter.paintComponent(g, 250, 48, Color.white);
        greenCounter.paintComponent(g, 365, 48, Color.green);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // These get the location of where the mouse was clicked and prints it
        int xLocation = e.getX();
        int yLocation = e.getY();
        System.out.println("(" + xLocation + ", " + yLocation + ")");
        // This moves each of the counters - this is just for testing
//        redY = redY - 23;
//        whiteY = whiteY + 23;
//        blueX -= 23;
//        yellowX += 23;
//        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    


    public static void main (String args[]) throws Exception {
        new ShapeTest();
    }
}