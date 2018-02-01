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

       //all this puts up the image of the chip
        /* BufferedImage chip = ImageIO.read(new File("resources/chip.png"));
        JLabel chipImage = new JLabel(new ImageIcon(chip));
        Chip.add(chipImage);
        Dimension ChipSize =new Dimension(10,10);
        Chip.setPreferredSize(ChipSize);
        Chip.setBounds(100,100,ChipSize.width, ChipSize.height);
        board.add( Chip, new Integer(11));
        */
        
        JTextArea infoField = new JTextArea(5, 15);
        infoField.setEditable(false);
        infoField.setText("Command 1\nCommand 2\nCommand 3\nCommand 4\nCommand 5\nCommand 6\nCommand 7");
        JTextField userInput = new JTextField();
        JScrollPane scrollPane = new JScrollPane(infoField);
        panel.add(scrollPane, "East");
        panel.add(board, "Center");
        panel.add(userInput, "South");
        add(panel);
        setVisible(true);
        board.addMouseListener(this);

        System.out.println(infoField.getWidth());
        System.out.println(userInput.getHeight());
        
              
//        panel.setOpaque(false); // background of parent will be painted first
//        panel.setBackground( new Color(255, 0, 0, 20) );


        int xLocation = 292;
        int yLocation = 582;
    }

    public static void main(String a[]) throws IOException {
        new ShapeTest();
    }
    
//    public void paint(Graphics g){
//       g.drawRect(200, 100, 100, 200); // FOR RECT
//       setBackground(new Color(255, 0, 0, 127));
//    }



    public void paint(Graphics g){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("resources/cluedo_board.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int cellWidth = myPicture.getWidth(null);

        int cellHeight = myPicture.getHeight(null);


        int dy = (0 % 2) * cellHeight;
        g.drawImage(myPicture, 0, dy, cellWidth, dy+cellHeight, 0, 0, cellWidth, cellHeight, null);


        g.setColor(Color.red);
        g.fillOval(204, 576, 22, 22);

        g.setColor(Color.yellow);
        g.fillOval(45, 414, 22, 22);

        g.setColor(Color.BLUE);
        g.fillOval(572, 460, 22, 22);

        g.setColor(Color.cyan);
        g.fillOval(572, 162, 22, 22);

        g.setColor(Color.white);
        g.fillOval(249, 26, 22, 22);

        g.setColor(Color.green);
        g.fillOval(365, 26, 22, 22);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xLocation = e.getX();
        int yLocation = e.getY();
        System.out.println("(" + xLocation + ", " + yLocation + ")");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xLocation = e.getX();
        int yLocation = e.getY();
        System.out.println("(" + xLocation + ", " + yLocation + ")");
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