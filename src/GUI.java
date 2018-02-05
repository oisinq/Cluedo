import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GUI extends JFrame implements MouseListener {

   // These components store where the counters are drawn - only temporary variables until we add them to the player class
    int redY = 599;
    int whiteY = 47;
    int blueX = 572;
    int yellowX = 44;

    // This method creates the graphic interface for the program
    public GUI() throws IOException {

        JPanel panel = new JPanel();
        JLayeredPane board = new JLayeredPane();

        // We use BorderLayout to easily have multiple components in the same panel
        panel.setLayout(new BorderLayout());
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
        panel.add(scrollPane, "East");
        panel.add(board, "Center");
        panel.add(userInput, "South");
        add(panel);
//        Counter component = new Counter();
//        add(component);

        // This lets us know when the board is clicked
        board.addMouseListener(this);

        setVisible(true);

        
      //all this is text manipulation that will need to be moved
        @SuppressWarnings("serial")//come back to this maybe
		Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	 String inputtedText =userInput.getText();//takes info from the field
                 userInput.setText("");//wipes the field after 
            	// if(inputtedText.equals("/help")){
                  //	infoField.setText("help will go here!");
                  //}
            	 //else{
                 infoField.setText("> " + inputtedText);//puts it into the panel
            	 //}
                 userInput.setText("");//wipes the field after 
            }
        };
        userInput.addActionListener(action); //Sets a button(enter) to activate the above listener
    }


    public void paint(Graphics g){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("resources/cluedo_board.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        super.paintComponents(g);

        g.drawImage(myPicture, 0, 22, null);

        drawCounters(g);

        drawImage(g);
        
    }


    /**
     * This draws all of the pointers on the map
     */
    public void drawCounters(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(205, redY, 20, 20);

        g.setColor(Color.yellow);
        g.fillOval(yellowX, 437, 20, 20);

        g.setColor(Color.BLUE);
        g.fillOval(blueX, 483, 20, 20);

        g.setColor(Color.cyan);
        g.fillOval(blueX, 184, 20, 20);

        g.setColor(Color.white);
        g.fillOval(250, whiteY, 20, 20);

        g.setColor(Color.green);
        g.fillOval(365, whiteY, 20, 20);
    }

    /**
     * Draws an image onto the map - will be used for weapons
     */
    public void drawImage(Graphics g) {
        
        BufferedImage gun = null;
        BufferedImage rope = null;
        BufferedImage wrench=null;
        BufferedImage lead_Pipe= null;
        BufferedImage candlestick=null;
        BufferedImage dagger=null;
        
        try {
            dagger = ImageIO.read(new File("resources/dagger.png"));
            rope = ImageIO.read(new File("resources/rope.png"));
            wrench = ImageIO.read(new File("resources/wrench.png"));
            lead_Pipe =  ImageIO.read(new File("resources/lead_pipe.png"));
            gun = ImageIO.read(new File("resources/revolver.png"));
            candlestick = ImageIO.read(new File("resources/candlestick.png"));
        } catch (IOException e){

        }
        g.drawImage(dagger, 120,150,40,40, null);
        g.drawImage(rope, 500,120,40,40, null);
        g.drawImage(wrench, 172,303,40,40, null);
        g.drawImage(gun, 470,300,40,40, null);
        g.drawImage(lead_Pipe, 470,375,40,40, null);
        g.drawImage(candlestick, 250,500,40,40, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // These get the location of where the mouse was clicked and prints it
        int xLocation = e.getX();
        int yLocation = e.getY();
        System.out.println("(" + xLocation + ", " + yLocation + ")");
        // This moves each of the counters - this is just for testing
        redY = redY - 23;
        whiteY = whiteY + 23;
        blueX -= 23;
        yellowX += 23;
        repaint();
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
}