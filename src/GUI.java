import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI  extends JFrame /* implements MouseListener */ {

    public GUI() {
    }

    int xLocation, yLocation;

    public void generateGUI() throws IOException {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JPanel board = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setSize(1000, 800);
        frame.setTitle("Cluedo");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
        frame.add(panel);
        frame.setVisible(true);


        int xLocation = 292;
        int yLocation = 582;


//        board.addMouseListener(this);
    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        xLocation = e.getX();
//        yLocation = e.getY();
//        System.out.println("(" + xLocation + ", " + yLocation + ")");
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }

//    public void paint(Graphics g){
//        super.paint(g);
//        g.drawOval(xLocation,yLocation, 11*2,11*2);
//    }

}