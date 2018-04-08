/*  Cluedo - Sprint 4
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
    public boolean lostGame = false;
    private String characterName;
    private String userName;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> sharedCards;
    private int xLocation, yLocation;
    private int column, row, rollForOrder;
    private Color c;
    private Room currentRoom;
    private Notes notes;


    Counter(String userName, String characterName, Color c, int x, int y, int rollForOrder) {
        this.userName = userName;
        this.characterName = characterName;
        this.c = c;
        column = x;
        row = y;

        // 43, 50 is top of grid
        xLocation = 43 + (column * 23);
        yLocation = 50 + (row * 23);
        currentRoom = null;
        notes = new Notes();
        this.rollForOrder = rollForOrder;
        sharedCards = SpareCards.cards;
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
    public int getRollForOrder() {
        return rollForOrder;
    }

    /**
     * set the roll first to a higher value(only for use with the turn methods in gameplay)
     */
    public void setRollForOrder(int r) {
        rollForOrder = r;
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
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the name of the character represented by the counter
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Returns the name of the current room of the counter
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Checks if the current room the counter is in is "r"
     */
    public void setCurrentRoom(Room r) {
        currentRoom = r;
    }

    public void addCard(Card c) {
        cards.add(c);
    }

    public Boolean hasCardName(String CardName) {
        for (Card c : cards) {
            if (c.getName().equals(CardName)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * This reads the values in notes, and returns the details to a string
     * This can then be displayed in the infoField
     */
    public String getNotesString() {
        refreshNotes();
        StringBuilder s = new StringBuilder();
        int i = 0;

        String title = characterName + "'s Notes";
        s.append(title);
        for (Map.Entry<String, String> entry : notes.values.entrySet()) {
            if (i == 0) {
                s.append("\nPlayers:\n");
            } else if (i == 6) {
                s.append("\nWeapons:\n");
            } else if (i == 12) {
                s.append("\nRooms:\n");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            s.append(String.format("%-14s -> %3s\n", key.trim(), value.trim()));
            i++;
        }

        return s.toString();
    }

    public void addNotes(String cardName) {
        notes.addSeenCard(cardName);
    }

    private void refreshNotes() {
        notes.refresh(this, sharedCards);
    }
}
