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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ShapeTest extends JFrame implements MouseListener {
    public ShapeTest() throws IOException {
        JPanel panel = new JPanel();
        //JPanel board = new JPanel();
        JLayeredPane board = new JLayeredPane();
        JPanel background = new JPanel();
        JPanel Chip = new JPanel();
        
        panel.setLayout(new BorderLayout());
        setSize(835, 670);
        Dimension boardSize =new Dimension( 700,640);
        setTitle("Cluedo");
        //setLocationRelativeTo(0,0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        board.setPreferredSize( boardSize );
        
        
        
        BufferedImage myPicture = ImageIO.read(new File("resources/cluedo_board.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        background.add(picLabel);
        background.setPreferredSize(boardSize);
        background.setBounds(0,0,boardSize.width, boardSize.height);
        board.add(background, new Integer(10));//change the  int number to move through layers , larger number= more to front

        // Original code to draw image over another image
        // We can use layering by setting a higher integer value when adding a label
        
        
        
        JTextArea infoField = new JTextArea(10, 15);
        infoField.setEditable(false);
        infoField.setText("Command 1\nCommand 2\nCommand 3\nCommand 4\nCommand 5\nCommand 6\nCommand 7");
        JTextField userInput = new JTextField();
        JScrollPane scrollPane = new JScrollPane(infoField);
        panel.add(scrollPane, "East");
        panel.add(board, "Center");
        panel.add(userInput, "South");
        add(panel);
        
        board.addMouseListener(this);
        
        System.out.println(infoField.getWidth());
        System.out.println(userInput.getHeight());
        setVisible(true);
    }

    public static void main(String a[]) throws IOException {
        new ShapeTest();
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
        g.fillOval(204, 598, 22, 22);

        g.setColor(Color.yellow);
        g.fillOval(45, 436, 22, 22);

        g.setColor(Color.BLUE);
        g.fillOval(572, 482, 22, 22);

        g.setColor(Color.cyan);
        g.fillOval(572, 184, 22, 22);

        g.setColor(Color.white);
        g.fillOval(249, 46, 22, 22);

        g.setColor(Color.green);
        g.fillOval(365, 46, 22, 22);
    }

    public void drawImage(Graphics g) {
        BufferedImage chip = null;
        try {
            chip = ImageIO.read(new File("resources/chip.png"));
        } catch (IOException e){

        }
        g.drawImage(chip, 50, 50, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xLocation = e.getX();
        int yLocation = e.getY();
        System.out.println("(" + xLocation + ", " + yLocation + ")");
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