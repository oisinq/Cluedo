//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//
//public class ShapeTest extends JFrame{
//    public ShapeTest(){
//        setSize(400,400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    public static void main(String a[]){
//        new ShapeTest();
//    }
//
//    public void paint(Graphics g){
//        g.drawOval(40, 40, 60, 60); //FOR CIRCLE
//        g.drawRect(80, 30, 200, 200); // FOR SQUARE
//        g.drawRect(200, 100, 100, 200); // FOR RECT
//    }
//}

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

    int redY = 599;
    int whiteY = 47;
    int blueX = 572;
    int yellowX = 44;

    public GUI() throws IOException {
        JPanel panel = new JPanel();

        JLayeredPane board = new JLayeredPane();

        
        panel.setLayout(new BorderLayout());
        setSize(835, 690);
      //  Dimension boardSize =new Dimension( 700,60);
        setTitle("Cluedo");
        //setLocationRelativeTo(0,0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      //  board.setPreferredSize( boardSize );
        
        
        
        JTextArea infoField = new JTextArea(10, 15);
        infoField.setEditable(false);
        infoField.setLineWrap(true);
        infoField.setText("Command 1\nCommand 2\nCommand 3\nCommand 4\nCommand 5\nCommand 6\nCommand 7");
        JTextField userInput = new JTextField();
        userInput.setText("Enter messages here!");
        JScrollPane scrollPane = new JScrollPane(infoField);
        panel.add(scrollPane, "East");
        panel.add(board, "Center");
        panel.add(userInput, "South");
        add(panel);
        
        board.addMouseListener(this);
        
        System.out.println(infoField.getWidth());
        System.out.println(userInput.getHeight());
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
        userInput.addActionListener( action );//sets a button(enter) to activate the above listener
    }

    public static void main(String a[]) throws IOException {
        new GUI();
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
        g.drawImage(wrench, 120,150,40,40, null);
        g.drawImage(gun, 470,300,40,40, null);
        g.drawImage(lead_Pipe, 470,375,40,40, null);
        g.drawImage(candlestick, 250,500,40,40, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xLocation = e.getX();
        int yLocation = e.getY();
        System.out.println("(" + xLocation + ", " + yLocation + ")");
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