/*  Cluedo - Sprint 1
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Each counter represents a player on the board
 */
public class Counter extends JComponent implements BoardPiece {

    // We store these variables here to make it easier to move around the players on the board
    private int xLocation, yLocation;
    private Color c;
    private String name;
    public String getName() {
        return name;
    }

    Counter(String name, Color c, int x, int y) {
        this.name = name;
        this.c = c;
        xLocation = x;
        yLocation = y;
    }

    /**
     * This paints the component onto the board
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(c);
        Shape circle = new Ellipse2D.Double(xLocation, yLocation, 20, 20);
        g2.fill(circle);
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
     * Sets the colour of the counter
     * @param c the colour
     */
    public void setColor(Color c) {
        this.c = c;
    }

    /**
     * Returns the X co-ordinate of the counter
     * @return the X co-ordinate of the counter
     */
    public int getX() {
        return xLocation;
    }

    /**
     * Returns the Y co-ordinate of the counter
     * @return the Y co-ordinate of the counter
     */
    public int getY() {
        return yLocation;
    }

    /**
     * Moves the counter up the entered number of steps
     * @param steps the number of steps to be moved
     */
    public void moveUp(int steps) {
        setXY(xLocation, yLocation - (23 * steps));
    }

    /**
     * Moves the counter down the entered number of steps
     * @param steps the number of steps to be moved
     */
    public void moveDown(int steps) {
        setXY(xLocation, yLocation + (23 * steps));
    }

    /**
     * Moves the counter left the entered number of steps
     * @param steps the number of steps to be moved
     */
    public void moveLeft(int steps) {
        setXY(xLocation - (23 * steps), yLocation);
    }

    /**
     * Moves the counter right the entered number of steps
     * @param steps the number of steps to be moved
     */
    public void moveRight(int steps) {
        setXY(xLocation + (23 * steps), yLocation);
    }

    public boolean hasName(String name) {
        return this.name.toLowerCase().equals(name.toLowerCase().trim());
    }
}
