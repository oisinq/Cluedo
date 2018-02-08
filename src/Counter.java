import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Counter extends JComponent {

    int xLocation, yLocation;
    Color c;

 //   @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
       g2.setColor(c);
        Shape circle = new Ellipse2D.Double(xLocation, yLocation, 20, 20);
        g2.fill(circle);
    }

    public void setXY(int x, int y) {
        xLocation = x;
        yLocation = y;
    }

    public void setColor(Color c) {
        this.c = c;
    }

    public int getX() {
        return xLocation;
    }

    public int getY() {
        return yLocation;
    }

}
