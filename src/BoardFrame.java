import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BoardFrame extends JPanel {

    private BufferedImage boardImage;

    public void BoardFrame() {
        try {
            boardImage = ImageIO.read(this.getClass().getResource("cluedo_board.jpg"));
        }
        catch (IOException ex) {
            System.out.println("Could not find the image file " + ex.toString());
        }
    }
    public void paintComponents(Graphics g) {

        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(boardImage, 0, 22,this);

    }
}
