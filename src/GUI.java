import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.BorderLayout;
import java.io.IOException;

public class GUI {

    public static void generateGUI() throws IOException {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setSize(700, 800);
        frame.setTitle("Cluedo");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BufferedImage myPicture = ImageIO.read(new File("resources/cluedo_board.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        JTextArea infoField = new JTextArea(5, 30);
        infoField.setEditable(false);
        infoField.setText("Command 1\nCommand 2\nCommand 3\nCommand 4\nCommand 5\nCommand 6\nCommand 7");

        JTextField userInput = new JTextField();
        JScrollPane scrollPane = new JScrollPane(infoField);

        panel.add(scrollPane, BorderLayout.NORTH);
        panel.add(picLabel, BorderLayout.CENTER);
        panel.add(userInput, BorderLayout.SOUTH);
        frame.add(panel);

        frame.setVisible(true);
    }

}
