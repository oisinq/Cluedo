import javax.swing.*;
import java.awt.*;

public class Counter extends JComponent {

    int xLocation, yLocation;

    public void paintComponent(Graphics g, int x, int y, Color c) {
        xLocation = x;
        yLocation = y;
        g.setColor(c);
        g.fillOval(xLocation, yLocation, 20, 20);
    }

}
