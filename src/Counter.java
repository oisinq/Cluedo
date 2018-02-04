import javax.swing.*;
import java.awt.*;

public class Counter extends JComponent {
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle box = new Rectangle(10, 20, 30, 40);
        g2.setColor(new Color(255, 0, 0, 100));
        g2.fill(box);
    }
}
