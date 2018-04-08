/*  Cluedo - Sprint 4
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

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
    private int column, row;
    private BufferedImage Image;//this will hold the image of the weapon once its passed in
    private String name;
    private Room currentRoom;

    Weapon(String name, int x, int y, String imageLocation, Room room) {
        this.name = name;
        xLocation = x;
        yLocation = y;
        currentRoom = room;
        try {
            Image = ImageIO.read(this.getClass().getResource(imageLocation));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: cannot load board image.");
            e.printStackTrace();
        }
    }

    /**
     * This paints the image at a set size of 40 by 40 pixels
     * xLoxation and yLocation get filled in in the GUI class
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Image, xLocation, yLocation, 40, 40, null);
    }


    /**
     * Sets the (Row, Column) location of the counter - this also affects the xLocation/yLocation
     */
    private void setRowColumn(int r, int c) {
        row = r;
        column = c;
        xLocation = 43 + (column * 23);
        yLocation = 50 + (row * 23);
    }

    /**
     * Returns the X co-ordinate of the counter location
     */
    public int getX() {
        return xLocation;
    }

    /**
     * Returns the Y co-ordinate of the counter location
     */
    public int getY() {
        return yLocation;
    }

    /**
     * Moves the counter up the entered number of steps
     */
    public void moveUp(int steps) {
        setRowColumn(row - steps, column);
    }

    /**
     * Moves the counter down the entered number of steps
     */
    public void moveDown(int steps) {
        setRowColumn(row + steps, column);
    }

    /**
     * Moves the counter left the entered number of steps
     */
    public void moveLeft(int steps) {
        setRowColumn(row, column - steps);
    }

    /**
     * Moves the counter right the entered number of steps
     */
    public void moveRight(int steps) {
        setRowColumn(row, column + steps);
    }

    public String getName() {
        return name;
    }

    public boolean hasName(String name) {
        return this.name.toLowerCase().equals(name.toLowerCase().trim());
    }

    /**
     * Returns the name of the current room of the weapon
     */
    public Room getCurrRoom() {
        return currentRoom;
    }

    /**
     * Checks if the current room the weapon is in is "r"
     */
    public void setCurrentRoom(Room r) {

        if (r == Rooms.get("Kitchen")) {
            xLocation = 40;
            yLocation = 70;
        } else if (r == Rooms.get("Conservatory")) {
            xLocation = 554;
            yLocation = 70;
        } else if (r == Rooms.get("Dining Room")) {
            xLocation = 40;
            yLocation = 371;
        } else if (r == Rooms.get("Billiard Room")) {
            xLocation = 554;
            yLocation = 235;
        } else if (r == Rooms.get("Lounge")) {
            xLocation = 40;
            yLocation = 585;
        } else if (r == Rooms.get("Study")) {
            xLocation = 554;
            yLocation = 585;
        } else if (r == Rooms.get("Library")) {
            xLocation = 554;
            yLocation = 393;
        } else if (r == Rooms.get("Ball Room")) {
            xLocation = 365;
            yLocation = 99;
        } else if (r == Rooms.get("Hall")) {
            xLocation = 254;
            yLocation = 574;
        }


        currentRoom = r;

    }


}
