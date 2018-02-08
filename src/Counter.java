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
//        xLocation = x;
//        yLocation = y;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(c);
        Shape circle = new Ellipse2D.Double(xLocation, yLocation, 20, 20);
        g2.fill(circle);
    //    g2.fillOval(xLocation, yLocation, 20, 20);
   //     super.paintComponent(g);
    }

    public void paint(Graphics g) {

    }

    public void setXY(int x, int y) {
        xLocation = x;
        yLocation = y;
    }

    public void setColor(Color c) {
        this.c = c;
    }

}
