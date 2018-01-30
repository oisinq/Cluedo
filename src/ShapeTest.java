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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ShapeTest extends JFrame{
    public ShapeTest() throws IOException {
        JPanel panel = new JPanel();
        JPanel board = new JPanel();
        panel.setLayout(new BorderLayout());
        setSize(1000, 800);
        setTitle("Cluedo");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BufferedImage myPicture = ImageIO.read(new File("resources/cluedo_board.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        board.add(picLabel);

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

//        panel.setOpaque(false); // background of parent will be painted first
//        panel.setBackground( new Color(255, 0, 0, 20) );


        int xLocation = 292;
        int yLocation = 582;
    }

    public static void main(String a[]) throws IOException {
        new ShapeTest();
    }

    public void paint(Graphics g){
        g.drawOval(40, 40, 60, 60); //FOR CIRCLE
        g.drawRect(80, 30, 200, 200); // FOR SQUARE
        g.drawRect(200, 100, 100, 200); // FOR RECT
        setBackground(new Color(255, 0, 0, 127));
    }
}