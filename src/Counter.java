import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Counter extends JComponent implements BoardPiece {

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

    public void moveUp(int steps) {
        setXY(xLocation, yLocation - (23 * steps));
    }

    public void moveDown(int steps) {
        setXY(xLocation, yLocation + (23 * steps));
    }

    public void moveLeft(int steps) {
        setXY(xLocation - (23 * steps), yLocation);
    }

    public void moveRight(int steps) {
        setXY(xLocation + (23 * steps), yLocation);
    }

}
