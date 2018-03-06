/*  Cluedo - Sprint 2
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Map;

/**
 * Each counter represents a player on the board
 */
public class Counter extends JComponent implements BoardPiece {
	private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> sharedCards = new ArrayList<>();

    private int xLocation, yLocation;
    // 43, 50 is top of grid
    private int column, row,rollFirst;
    private Color c;
    private String userName;
    private String characterName;
    private Room currentRoom;
    private Notes notes;


    Counter(String userName, String characterName, Color c, int x, int y,int rollFirst) {
    	this.userName=userName;
        this.characterName = characterName;
        this.c = c;
        column = x;
        row = y;
        xLocation = 43 + (column * 23);
        yLocation = 50 + (row * 23);
        currentRoom = null;
        notes = new Notes();
        this.rollFirst=rollFirst;
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
     * Sets the (Row, Column) location of the counter - this also affects the xLocation/yLocation
     */
    public void setRowColumn(int r, int c) {
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
     * Returns the roll
     */
    public int getRollFirst() {
        return rollFirst;
    }
    /**
    * set the roll first to a higher value(only for use with the turn methods in gameplay)
    */
    public void setRollFirst(int r) {
    	
        rollFirst+=r;
    }
    /**
     * Returns the column of the counter
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the row of the counter
     */
    public int getRow() {
        return row;
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

    /**
     * Checks if the counter has the entered character name
     */
    public boolean hasCharacterName(String characterName) {
        return this.characterName.toLowerCase().equals(characterName.toLowerCase().trim());
    }

    /**
     * Gets the name originally entered by the user
     */
    public String getUserName(){
        return userName;
    }

    /**
     * Gets the name of the character represented by the counter
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Checks if the current room the counter is in is "r"
     */
    public void setCurrentRoom(Room r) {
        currentRoom = r;
    }

    /**
     * Returns the name of the current room of the counter
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addCard(Card c)
{
	cards.add(c);
}

    public ArrayList<Card> getCards() {
        return cards;
    }
    public void printCards() {
    	 for (Card currentCounter : cards) {
	         System.out.println(currentCounter);
	      }
    }
    public String getNotesString() {
        String s = characterName+":\n";
        for (Map.Entry<String, String> entry : notes.values.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            s += key + " -> " + value + "\n";
        }
        s += "\n";

        return s;
    }

    public void refreshNotes() {
        notes.refresh(this, sharedCards);
    }

}
