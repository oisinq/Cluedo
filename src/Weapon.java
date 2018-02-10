import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Weapons are randomly allocated in each room and are moved when used in a questioning
 */
public class Weapon extends JComponent implements BoardPiece {

    private int xLocation, yLocation;//the values for the image coordinates
    private BufferedImage Image;//this will hold the image of the weapon once its passed in

    /**
     * This paints the image at a set size of 40 by 40 pixels
     * xLoxation and yLocation get filled in in the GUI class
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
    	 g2.drawImage(Image, xLocation, yLocation,40,40,null);
    }

    /**
     * Sets an image file for the weapon
     * @param location the location of the weapon image
     */
    public void SetImageFile(String location){//this sets the file location of the image
        try {
            Image = ImageIO.read(this.getClass().getResource(location));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: cannot load board image.");
            e.printStackTrace();
        }
    }

    /**
     * Sets the location of the counter
     * @param x the x co-ordinate of the location
     * @param y the y co-ordinate of the location
     */
    public void setXY(int x, int y) {
        xLocation = x;
        yLocation = y;
    }

    /**
     * Returns the X co-ordinate of the weapon
     * @return the X co-ordinate of the weapon
     */
    public int getX() {
        return xLocation;
    }

    /**
     * Returns the Y co-ordinate of the weapon
     * @return the Y co-ordinate of the weapon
     */
    public int getY() {
        return yLocation;
    }

    /**
     * Moves the weapon up the entered number of steps
     * @param steps the number of steps to be moved
     */
    public void moveUp(int steps) {
        setXY(xLocation, yLocation - (23 * steps));
    }

    /**
     * Moves the weapon down the entered number of steps
     * @param steps the number of steps to be moved
     */
    public void moveDown(int steps) {
        setXY(xLocation, yLocation + (23 * steps));
    }

    /**
     * Moves the weapon left the entered number of steps
     * @param steps the number of steps to be moved
     */
    public void moveLeft(int steps) {
        setXY(xLocation - (23 * steps), yLocation);
    }

    /**
     * Moves the weapon right the entered number of steps
     * @param steps the number of steps to be moved
     */
    public void moveRight(int steps) {
        setXY(xLocation + (23 * steps), yLocation);
    }
}
