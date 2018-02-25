/*  Cluedo - Sprint 2
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */

/**
 * This interface is used by both counters and weapons, since both components are very similar
 */
public interface BoardPiece {

    /**
     * Returns the X co-ordinate of the counter
     * @return the X co-ordinate of the counter
     */
    int getX();

    /**
     * Returns the Y co-ordinate of the counter
     * @return the Y co-ordinate of the counter
     */
    int getY();

    /**
     * Moves the counter up the entered number of steps
     * @param steps the number of steps to be moved
     */
    void moveUp(int steps);

    /**
     * Moves the counter down the entered number of steps
     * @param steps the number of steps to be moved
     */
    void moveDown(int steps);

    /**
     * Moves the counter left the entered number of steps
     * @param steps the number of steps to be moved
     */
    void moveLeft(int steps);

    /**
     * Moves the counter right the entered number of steps
     * @param steps the number of steps to be moved
     */
    void moveRight(int steps);

    /**
     * Sets the location of the counter
     * @param x the x co-ordinate of the location
     * @param y the y co-ordinate of the location
     */
   // void setXY(int x, int y);
}